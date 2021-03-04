package hk.ust.steve.signalcollector.map;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import hk.ust.steve.signalcollector.Consts;
import hk.ust.steve.signalcollector.utils.GeneralUtils;
import hk.ust.steve.signalcollector.utils.MathUtil;

public class MapView extends GestureView {
	private static final String TAG = "MapView";
	private Context mContext;

	public MapView(Context context) {
		super(context);
		mContext = context;
		init();
	}

	public MapView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mContext = context;
		init();
	}

	public MapView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		mContext = context;
		init();
	}

	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private Bitmap mapBitmap = null;
	private Matrix mapMatrix = null;
	private Matrix gestureMatrix = new Matrix();
	private Matrix focusMatrix = new Matrix();
	private Matrix combinedMatrix = new Matrix();

	private static final float initScale = 1f;
	private static final float initRotation = 0f;
	private static final float[] initTranslation = { 0f, 0f };
	private static final float idealDisplayLength = 50 * 13.3333f; // pixels of
																	// map
	private float heightScaleFromOrigin2Compressed = 1;
	private float widthScaleFromOrigin2Compressed = 1;
	private static final Matrix initMapMatrix = new Matrix();

	private final Object allAnimatePointsSynObj = new Object();
	private Map<UUID, AnimatePoint> allAnimatePoints = new HashMap<UUID, AnimatePoint>();
	private final Object allFixedPointsSynObj = new Object();
	private List<MapPoint2Draw> allFixedPoints = new ArrayList<MapPoint2Draw>();
	private final Object allLinesSynObj = new Object();
	private List<Line2Draw> allLines = new ArrayList<Line2Draw>();
	private UpdateAnimatePointsThread mUpdateAnimatePointsThread = new UpdateAnimatePointsThread();
	private FocusAnimationThread mFocusAnimationThread = new FocusAnimationThread();
	private static final Paint pointPaint = new Paint();
	private final Object fixedMarkSynObj = new Object();
	private Mark2Draw fixedMark = null;
	private final Object dynamicMarkSynObj = new Object();
	private Mark2Draw dynamicMark = null;
	private static boolean viewInitialized = false;
	private boolean showMarkLocation = false;
	
    private OnMapClickListener mOnMapClickListener = null;

	private void init() {
		pointPaint.setStyle(Paint.Style.FILL);
		pointPaint.setAntiAlias(true);

		initMapMatrix.setValues(new float[] { 0.008169312f, -3.9735353f, 1796.3358f, 3.9735353f, 0.008169312f,
				-4352.7036f, 0.0f, 0.0f, 1.0f });
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);

		rwLock.writeLock().lock();
		combinedMatrix = new Matrix(mapMatrix);
		if (mapBitmap != null && mapMatrix != null) {
			combinedMatrix.postConcat(focusMatrix);
			combinedMatrix.postConcat(gestureMatrix);
			canvas.drawBitmap(mapBitmap, combinedMatrix, null);
		} else {
			rwLock.writeLock().unlock();
			return;
		}

		synchronized (allLinesSynObj) {
			for (Line2Draw line : allLines) {
				drawLine(canvas, transferMapPoint2ViewPoint(line.beginPoint, combinedMatrix),
						transferMapPoint2ViewPoint(line.endPoint, combinedMatrix), line.paintColor);
			}
		}

		synchronized (allFixedPointsSynObj) {
			for (MapPoint2Draw point2Draw : allFixedPoints) {
				PointF point = transferMapPoint2ViewPoint(point2Draw.point, combinedMatrix);
				drawPoint(canvas, new MapPoint2Draw(point, point2Draw.paintColor));
			}
		}

		synchronized (fixedMarkSynObj) {
			if (fixedMark != null) {
				drawMark(canvas, fixedMark);
			}
		}
		
		synchronized (dynamicMarkSynObj) {
			if (dynamicMark != null) {
				drawMark(canvas, new Mark2Draw(dynamicMark.drawable, 
						transferMapPoint2ViewPoint(dynamicMark.location, combinedMatrix), 
						dynamicMark.width, dynamicMark.height));
			}
		}

		synchronized (allAnimatePointsSynObj) {
			for (AnimatePoint animatePoint : allAnimatePoints.values()) {
				PointF point = transferMapPoint2ViewPoint(animatePoint.curPoint, combinedMatrix);
				drawPoint(canvas, new MapPoint2Draw(point, animatePoint.paintColor));
			}
		}

		rwLock.writeLock().unlock();
	}

	private void drawLine(Canvas canvas, PointF beginPoint, PointF endPoint, int color) {
		pointPaint.setColor(color);
		canvas.drawLine(beginPoint.x, beginPoint.y, endPoint.x, endPoint.y, pointPaint);
	}

	private void drawPoint(Canvas canvas, MapPoint2Draw point2Draw) {
		pointPaint.setColor(point2Draw.paintColor);
		canvas.drawCircle(point2Draw.point.x, point2Draw.point.y, 
				GeneralUtils.getScreenW(mContext) / Consts.MYLOCATION_RADIUS_TO_SCREEN,
				pointPaint);
	}

	private void drawMark(Canvas canvas, Mark2Draw mk) {
		PointF position = mk.location;
		mk.drawable.setBounds((int) (position.x - mk.width / 2f), (int) (position.y - mk.height),
				(int) (position.x + mk.width / 2f), (int) (position.y));
		mk.drawable.draw(canvas);
		
		if (showMarkLocation) {
			PointF point = transferViewPoint2MapPoint(mk.location, combinedMatrix);
			point = transferMapPoint2OriginalMapPoint(point);
			pointPaint.setTextSize(50);
			canvas.drawText("" + point.x + "," + point.y, 0, 50, pointPaint);
		}
	}

	private PointF transferMapPoint2ViewPoint(PointF point, Matrix matrix) {
		float[] mapPoint = new float[] { point.x, point.y };
		matrix.mapPoints(mapPoint);
		return new PointF(mapPoint[0], mapPoint[1]);
	}

	private PointF transferViewPoint2MapPoint(PointF point, Matrix matrix) {
		Matrix invertMatrix = new Matrix();
		matrix.invert(invertMatrix);
		float[] result = new float[] { point.x, point.y };
		invertMatrix.mapPoints(result);
		return new PointF(result[0], result[1]);
	}

	private PointF transferOriginalMapPoint2MapPoint(PointF point) {
		return new PointF(point.x * widthScaleFromOrigin2Compressed, point.y * heightScaleFromOrigin2Compressed);
	}

	private PointF transferMapPoint2OriginalMapPoint(PointF point) {
		return new PointF(point.x / widthScaleFromOrigin2Compressed, point.y / heightScaleFromOrigin2Compressed);
	}

	public void initNewMap(BitmapRegionDecoder bitmapRegionDecoder) {
		int height = bitmapRegionDecoder.getHeight();
		int width = bitmapRegionDecoder.getWidth();

		Rect rect = new Rect(0, 0, width, height); // show the whole range
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		// down-sample the original picture to around 1024 pixels
		options.inSampleSize = Math.round(Math.max(width, height) / 1024f);

		rwLock.writeLock().lock();
		mapBitmap = bitmapRegionDecoder.decodeRegion(rect, options);
		heightScaleFromOrigin2Compressed *= 1f * mapBitmap.getHeight() / height;
		widthScaleFromOrigin2Compressed *= 1f * mapBitmap.getWidth() / width;

		// mapMatrix = new Matrix();
		// float translationX = initTranslation[0] *
		// heightScaleFromOrigin2Compressed;
		// float translationY = initTranslation[1] *
		// heightScaleFromOrigin2Compressed;
		// mapMatrix.postTranslate(translationX, translationY);
		// mapMatrix.postRotate(initRotation, 500, 500);
		// mapMatrix.postScale(initScale, initScale);
		mapMatrix = initMapMatrix;
		rwLock.writeLock().unlock();

		invalidate();

		viewInitialized = true;
	}

	public void setOnMapClickListener (OnMapClickListener onMapClickListener) {
    	mOnMapClickListener = onMapClickListener;
    }
	
	@Override
	protected void onClick(float x, float y) {
		rwLock.writeLock().lock();
		PointF mapPoint = transferViewPoint2MapPoint(new PointF(x, y), combinedMatrix);
		rwLock.writeLock().unlock();
		mapPoint = transferMapPoint2OriginalMapPoint(mapPoint);
		Log.d(TAG, "click point: " + mapPoint);
		
		if (mOnMapClickListener != null) {
			mOnMapClickListener.onClick(mapPoint);
		}
	}

	@Override
	protected void onGestureDetected(float[] translate, float[] scale, float[] rotate) {
		rwLock.writeLock().lock();
		gestureMatrix.postTranslate(translate[0], translate[1]);
		gestureMatrix.postScale(scale[0], scale[1], scale[2], scale[3]);
		gestureMatrix.postRotate(rotate[0], rotate[1], rotate[2]);
		rwLock.writeLock().unlock();
		invalidate();
	}

	private class AnimatePoint {
		PointF curPoint, targetPoint;
		int paintColor;

		AnimatePoint(PointF curPoint, PointF targetPoint, int paintColor) {
			this.curPoint = curPoint;
			this.targetPoint = targetPoint;
			this.paintColor = paintColor;
		}
	}

	public void updateAnimatePoint(UUID pointId, PointF point, int paintColor) {
		point = transferOriginalMapPoint2MapPoint(point);

		synchronized (allAnimatePointsSynObj) {
			if (allAnimatePoints.isEmpty()) {
				allAnimatePoints.put(pointId, new AnimatePoint(point, point, paintColor));
				focusOnPoint(point);
			} else {
				if (!allAnimatePoints.containsKey(pointId)) {
					allAnimatePoints.put(pointId, new AnimatePoint(point, point, paintColor));
				} else {
					allAnimatePoints.get(pointId).targetPoint = point;
					allAnimatePoints.get(pointId).paintColor = paintColor;
				}
			}
		}

		Thread.State state = mUpdateAnimatePointsThread.getState();
		if (state.equals(Thread.State.TERMINATED)) {
			mUpdateAnimatePointsThread = new UpdateAnimatePointsThread();
			state = mUpdateAnimatePointsThread.getState();
		}
		if (state.equals(Thread.State.NEW)) {
			mUpdateAnimatePointsThread.start();
		}
	}

	private class UpdateAnimatePointsThread extends Thread {
		@Override
		public void run() {
			while (!viewInitialized) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			while (true) {
				boolean pointChanged = false;
				// rwLock.writeLock().lock();
				// Log.d(TAG, "UpdateDisplayPointsAnimationThread lock");
				synchronized (allAnimatePointsSynObj) {
					for (Map.Entry<UUID, AnimatePoint> entry : allAnimatePoints.entrySet()) {
						UUID displayId = entry.getKey();
						AnimatePoint animatePoint = entry.getValue();
						PointF curPoint = animatePoint.curPoint;
						PointF targetPoint = entry.getValue().targetPoint;
						if (MathUtil.distance(curPoint, targetPoint) > Consts.eps) {
							float stepX = (targetPoint.x - curPoint.x) / 4f;
							float stepY = (targetPoint.y - curPoint.y) / 4f;
							if (Math.abs(stepX) > 1f || Math.abs(stepY) > 1f) {
								curPoint.set(curPoint.x + stepX, curPoint.y + stepY);
							} else {
								curPoint.set(targetPoint.x, targetPoint.y);
							}
							allAnimatePoints.get(displayId).curPoint = curPoint;
							pointChanged = true;
						}
					}
				}
				// rwLock.writeLock().unlock();
				// Log.d(TAG, "UpdateDisplayPointsAnimationThread unlock");
				// Log.d(TAG, "pointChanged: " + pointChanged);

				postInvalidate();
				if (pointChanged) {
					try {
						Thread.sleep(30);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					return;
				}
			}
		}
	}

	private class FocusAnimationThread extends Thread {
		private PointF targetPoint = new PointF();
		private final Object targetSynObj = new Object();

		public void setTargetPoint(PointF targetPoint) {
			synchronized (targetSynObj) {
				this.targetPoint.x = targetPoint.x;
				this.targetPoint.y = targetPoint.y;
			}
		}

		@Override
		public void run() {
			while (!viewInitialized) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

			PointF centerViewPoint = new PointF(getWidth() / 2f, getHeight() / 2f);
			rwLock.writeLock().lock();
			PointF targetViewPoint = transferMapPoint2ViewPoint(targetPoint, combinedMatrix);
			rwLock.writeLock().unlock();
			while (true) {
				rwLock.writeLock().lock();

				float stepX = (centerViewPoint.x - targetViewPoint.x) / 2f;
				float stepY = (centerViewPoint.y - targetViewPoint.y) / 2f;
				if (Math.abs(stepX) > 10 || Math.abs(stepY) > 10) {
					focusMatrix.postTranslate(stepX, stepY);
					rwLock.writeLock().unlock();
					postInvalidate();
				} else {
					rwLock.writeLock().unlock();
					break;
				}
				targetViewPoint.x += stepX;
				targetViewPoint.y += stepY;

				try {
					Thread.sleep(30);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void focusOnPoint(PointF pointF) {
		Thread.State state = mFocusAnimationThread.getState();
		if (state.equals(Thread.State.TERMINATED)) {
			mFocusAnimationThread = new FocusAnimationThread();
			state = mFocusAnimationThread.getState();
		}

		mFocusAnimationThread.setTargetPoint(pointF);

		if (state.equals(Thread.State.NEW)) {
			mFocusAnimationThread.start();
		}
	}

	public void display() {
		postInvalidate();
	}

	public void clearAllStuff() {
//		synchronized (allAnimatePointsSynObj) {
//			allAnimatePoints.clear();
//		}
		synchronized (allFixedPointsSynObj) {
			allFixedPoints.clear();
		}
		synchronized (fixedMarkSynObj) {
			fixedMark = null;
		}
		synchronized (allLinesSynObj) {
			allLines.clear();
		}
	}

	public void addFixedPoints(List<PointF> allFixedPoints, int paintColor) {
		synchronized (allFixedPointsSynObj) {
			for (PointF point : allFixedPoints) {
				MapPoint2Draw mapPoint2Draw = new MapPoint2Draw(transferOriginalMapPoint2MapPoint(point), paintColor);
				this.allFixedPoints.add(mapPoint2Draw);
			}
		}
	}

	public void addFixedPoint(PointF fixedPoint, int paintColor) {
		synchronized (allFixedPointsSynObj) {
			MapPoint2Draw mapPoint2Draw = new MapPoint2Draw(transferOriginalMapPoint2MapPoint(fixedPoint), paintColor);
			this.allFixedPoints.add(mapPoint2Draw);
		}
	}

	public List<PointF> getFixedPoints() {
		List<PointF> ptList = new ArrayList<>();
		for (MapPoint2Draw point : allFixedPoints) {
			ptList.add(transferMapPoint2OriginalMapPoint(point.point));
		}
		return ptList;
	}

	public void removeLastPoint() {
		synchronized (allFixedPointsSynObj) {
			if (this.allFixedPoints.size() >= 1) {
				this.allFixedPoints.remove(this.allFixedPoints.size() - 1);
				if (this.allLines.size() >= 1) {
					this.allLines.remove(allLines.size() - 1);
				}
			} else {
				Toast.makeText(mContext, "No point left.", Toast.LENGTH_SHORT).show();
			}
		}
	}

	public void changePointColor(int index, int color) {
		synchronized (allFixedPointsSynObj) {
			if (this.allFixedPoints.size() >= index && index >= 0) {
				this.allFixedPoints.get(index).setPaintColor(color);
			}
		}
	}

	public void changePathColor(int index, int color) {
		synchronized (allLinesSynObj) {
			if (this.allLines.size() >= index && index >= 0) {
				this.allLines.get(index).setPaintColor(color);
			}
		}
	}

	public void addLine(PointF point1, PointF point2, int paintColor) {
		synchronized (allLinesSynObj) {
			allLines.add(new Line2Draw(transferOriginalMapPoint2MapPoint(point1),
					transferOriginalMapPoint2MapPoint(point2), paintColor));
		}
	}

	public void setFixedMarkDrawable(Drawable drawable, PointF viewLocation, float width, float height) {
		synchronized (fixedMarkSynObj) {
			fixedMark = new Mark2Draw(drawable, viewLocation, width, height);
		}
	}

	public void setDynamicMarkDrawable(Drawable drawable, PointF originMapLocation, float width, float height) {
		PointF mapPoint = transferOriginalMapPoint2MapPoint(originMapLocation);
		
		synchronized (dynamicMarkSynObj) {
			dynamicMark = new Mark2Draw(drawable, mapPoint, width, height);
		}
	}

	public PointF getMarkLocationOnOriginalMap() {
		if (fixedMark == null) {
			return null;
		} else {
			rwLock.writeLock().lock();
			PointF point = transferViewPoint2MapPoint(fixedMark.location, combinedMatrix);
			rwLock.writeLock().unlock();
			return transferMapPoint2OriginalMapPoint(point);
		}
	}

	public void showMarkLocation(boolean showMarkLocation) {
		this.showMarkLocation = showMarkLocation;
	}
	
	private class Mark2Draw {
		public Mark2Draw(Drawable drawable, PointF location, float width, float height) {
			this.drawable = drawable;
			this.location = location;
			this.width = width;
			this.height = height;
		}

		Drawable drawable;
		PointF location;
		float width;
		float height;
	}

	private class Line2Draw {
		PointF beginPoint, endPoint;
		int paintColor;

		public Line2Draw(PointF point1, PointF point2, int paintColor) {
			beginPoint = point1;
			endPoint = point2;
			this.paintColor = paintColor;
		}

		public void setPaintColor(int color) {
			paintColor = color;
		}
	}

	private class MapPoint2Draw {
		PointF point;
		int paintColor;

		public MapPoint2Draw(PointF point, int color) {
			this.point = point;
			this.paintColor = color;
		}

		public void setPaintColor(int color) {
			this.paintColor = color;
		}
	}
}
