package hk.ust.steve.signalcollector.map;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import hk.ust.steve.signalcollector.bean.Position;
import hk.ust.steve.signalcollector.utils.MapUtils;

public class MapLayout extends RelativeLayout {
	public MapLayout(Context context) {
		super(context);
		init();
	}

	public MapLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public MapLayout(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private String lastAreaId = MapUtils.getCurSite().getStartAreaId();
	private ArrayList<String> allAreaIds = MapUtils.getCurSite().getAllAreaIds();
	private Map<String, MapController> allMapControllers = new HashMap<String, MapController>();
	private MapController currentMapController = null;
	private static final int evaluatedColor = Color.BLUE;
	private static final int topTotalPotentialColor = Color.GREEN;
	private static final int topCurPotentialColor = Color.GRAY;

	public void init() {
		for (String areaId : allAreaIds) {
			allMapControllers.put(areaId, new MapController(getContext(), areaId));
		}
		currentMapController = allMapControllers.get(lastAreaId);

		addView(currentMapController.getMapView());
	}

	public void updateLocation(Position currentLocation,
                               List<Position> topTotalPotentialPos, List<Position> topCurPotentialPos) {
		if (currentLocation != null) {
			double x = currentLocation.getX();
			double y = currentLocation.getY();
			String areaId = currentLocation.getAreaId();

			if (!allAreaIds.contains(areaId)) {
				// Toast.makeText(getContext(), "wrong area id",
				// Toast.LENGTH_SHORT).show();
				return;
			}

			if (!lastAreaId.equals(areaId)) {
				lastAreaId = areaId;
				reloadMap(areaId);
			}
			
			if (x > 0 && x < currentMapController.getWidth() && y > 0 && y < currentMapController.getHeight()) {
				currentMapController.updateLocationResult(currentLocation, evaluatedColor);
			} else {
				// Toast.makeText(getContext(), "wrong (x, y)",
				// Toast.LENGTH_SHORT).show();
			}
		}
		currentMapController.clearAllStuff();
		if (topCurPotentialPos != null && !topCurPotentialPos.isEmpty()) {
			currentMapController.addFixedPoints(topCurPotentialPos, topCurPotentialColor);
		}
		if (topTotalPotentialPos != null && !topTotalPotentialPos.isEmpty()) {
			currentMapController.addFixedPoints(topTotalPotentialPos, topTotalPotentialColor);
		}
		currentMapController.display();
	}

	private void reloadMap(String areaId) {
		currentMapController = allMapControllers.get(areaId);

		removeAllViews();
		addView(currentMapController.getMapView());
	}

	public void changeFloor() {
		String areaId = MapUtils.getCurSite().getCurAreaId();
		if (!lastAreaId.equals(areaId)) {
			lastAreaId = areaId;
			reloadMap(areaId);
		}
	}

	public MapController getCurrentMapController() {
		return currentMapController;
	}
}
