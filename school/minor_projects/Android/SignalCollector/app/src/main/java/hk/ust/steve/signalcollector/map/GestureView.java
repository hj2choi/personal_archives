package hk.ust.steve.signalcollector.map;

import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import hk.ust.steve.signalcollector.utils.MathUtil;

public abstract class GestureView extends View {
    public GestureView(Context context) {
        super(context);
    }

    public GestureView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    // for gesture detection
    private boolean mIsMoved = false;
    private PointF[] mPreviousTouchPoints = {new PointF(), new PointF()};
    private int mPreviousPointerCount = 0;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_UP:
                if ((!mIsMoved) && event.getPointerCount() == 1) {
                    onClick(event.getX(), event.getY());
                }
                mIsMoved = false;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                mPreviousTouchPoints[0].set(event.getX(), event.getY());
                if (event.getPointerCount() > 1) {
                    mPreviousTouchPoints[1].set(event.getX(1), event.getY(1));
                }
                mPreviousPointerCount = event.getPointerCount();
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
            case MotionEvent.ACTION_DOWN:
                mPreviousTouchPoints[0].set(event.getX(), event.getY());
                if (event.getPointerCount() > 1) {
                    mPreviousTouchPoints[1].set(event.getX(1), event.getY(1));
                }
                mPreviousPointerCount = event.getPointerCount();
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getPointerCount() == 1 &&
                        (event.getX() - mPreviousTouchPoints[0].x)
                                * (event.getX() - mPreviousTouchPoints[0].x)
                                + (event.getY() - mPreviousTouchPoints[0].y)
                                * (event.getY() - mPreviousTouchPoints[0].y) < 20) {
                    break;
                }
                if (event.getPointerCount() >= 2 &&
                        MathUtil.squareDistance(new PointF(event.getX(), event.getY()),
                                mPreviousTouchPoints[0]) < 20 &&
                        MathUtil.squareDistance(new PointF(event.getX(1), event.getY(1)),
                                mPreviousTouchPoints[1]) < 20) {
                    break;
                }
                mIsMoved = true;
                if (event.getPointerCount() == mPreviousPointerCount) {
                    float[]translateParam = new float[] {0, 0};
                    float[]scaleParam = new float[] {1, 1, 0, 0};
                    float[]rotateParam = new float[] {0, 0, 0};

                    if (event.getPointerCount() == 1) {
                        float delX = event.getX() - mPreviousTouchPoints[0].x;
                        float delY = event.getY() - mPreviousTouchPoints[0].y;
                        translateParam[0] = delX;
                        translateParam[1] = delY;
                    } else {
                        PointF preMid = MathUtil.midPoint(mPreviousTouchPoints[0],
                                mPreviousTouchPoints[1]);
                        PointF curMid = MathUtil.midPoint(new PointF(event.getX(), event.getY()),
                                new PointF(event.getX(1), event.getY(1)));
                        double preDis = MathUtil.distance(mPreviousTouchPoints[0],
                                mPreviousTouchPoints[1]);
                        double curDis = MathUtil.distance(new PointF(event.getX(), event.getY()),
                                new PointF(event.getX(1), event.getY(1)));
                        if (MathUtil.distance(curMid, preMid) > 8) {
                            translateParam[0] = curMid.x - preMid.x;
                            translateParam[1] = curMid.y - preMid.y;
                        }
                        float scale = (float) (curDis / preDis);
                        scaleParam[0] = scale;
                        scaleParam[1] = scale;
                        scaleParam[2] = preMid.x;
                        scaleParam[3] = preMid.y;

                        double preAngle = MathUtil.angle(mPreviousTouchPoints[0], preMid);
                        double curAngle = MathUtil.angle(new PointF(event.getX(), event.getY()), curMid);
                        float delAngle = (float) ((curAngle - preAngle) / Math.PI * 180);
                        rotateParam[0] = delAngle;
                        rotateParam[1] = preMid.x;
                        rotateParam[2] = preMid.y;
                    }
                    onGestureDetected(translateParam, scaleParam, rotateParam);
                }
                break;
            default:
                break;
        }
        if (event.getPointerCount() == 1) {
            mPreviousTouchPoints[0].set(event.getX(), event.getY());
        } else if (event.getPointerCount() > 1) {
            mPreviousTouchPoints[0].set(event.getX(), event.getY());
            mPreviousTouchPoints[1].set(event.getX(1), event.getY(1));
        }
        mPreviousPointerCount = event.getPointerCount();
        return true;
    }

    protected abstract void onClick(float x, float y);

    /**
     * @param translate should be of size 2.
     *                  translate[0] means translate in x-axis,
     *                  translate[1] means translate in y-axis.
     * @param scale should be of size 4.
     *              scale[0] means the scale in x-axis,
     *              scale[1] means the scale in y-axis,
     *              scale[2] means x-coor of the center point,
     *              scale[3] means y-coor of the center point.
     * @param rotate should be of size 3.
     *               rotate[0] means rotation angle.
     *               rotate[1] means x-coor of the center point,
     *               rotate[2] means y-coor of the center point,
     */
    protected abstract void onGestureDetected(float[]translate, float[]scale, float[]rotate);
}
