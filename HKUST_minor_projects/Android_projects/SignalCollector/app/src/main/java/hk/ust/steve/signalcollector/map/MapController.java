package hk.ust.steve.signalcollector.map;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.BitmapRegionDecoder;
import android.graphics.PointF;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.bean.Position;
import hk.ust.steve.signalcollector.utils.MapUtils;

public class MapController {
    public MapView getMapView() {
        return mapView;
    }

    private MapView mapView = null;
    private int width;
    private int height;

    private static final UUID locationResultUUID = UUID.randomUUID();

    private Drawable locationMarkDrawable;

    public MapController(Context context, String areaId) {
        mapView = new MapView(context);

        try {
            AssetManager mngr = context.getAssets();
            InputStream is = mngr.open(MapUtils.getCurSite().getSiteName() + "Map" + areaId + ".jpg");
            BitmapRegionDecoder bitmapRegionDecoder = BitmapRegionDecoder.newInstance(is, false);
            mapView.initNewMap(bitmapRegionDecoder);
            width = bitmapRegionDecoder.getWidth();
            height = bitmapRegionDecoder.getHeight();

            locationMarkDrawable = ContextCompat.getDrawable(context, R.drawable.location_mark);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void updateLocationResult(Position position, int paintColor) {
        mapView.updateAnimatePoint(locationResultUUID, new PointF((float)position.getX(), (float)position.getY()), paintColor);
    }

    public void addFixedPoints(List<Position> positions, int paintColor) {
        List<PointF> allPointFs = new ArrayList<PointF>();
        for (Position position : positions) {
            allPointFs.add(new PointF((float)position.getX(), (float)position.getY()));
        }
        mapView.addFixedPoints(allPointFs, paintColor);
    }
    
    public void addFixedPoint(Position position, int paintColor) {
    	mapView.addFixedPoint(new PointF((float)position.getX(), (float)position.getY()), paintColor);
    }

    public void removeLastPoint() {
        mapView.removeLastPoint();
    }

    public void addLine(Position position1, Position position2, int paintColor) {
    	mapView.addLine(
    			new PointF((float)position1.getX(), (float)position1.getY()),
    			new PointF((float)position2.getX(), (float)position2.getY()),
    			paintColor);
    }
    
    public void clearAllStuff() {
        mapView.clearAllStuff();
    }

    public void setCenterMarkDrawable() {
        mapView.setFixedMarkDrawable(
                locationMarkDrawable,
                new PointF(mapView.getWidth() / 2f, mapView.getHeight() / 2f),
                locationMarkDrawable.getIntrinsicWidth() / 10f,
                locationMarkDrawable.getIntrinsicHeight() / 10f);
    }
    
    public void setMarkDrawable(Position pos) {
    	mapView.setDynamicMarkDrawable(
                locationMarkDrawable,
                new PointF((float)pos.getX(), (float)pos.getY()),
                locationMarkDrawable.getIntrinsicWidth() / 10f,
                locationMarkDrawable.getIntrinsicHeight() / 10f);
    }

    public void display() {
        mapView.display();
    }

    public Position getMarkLocation() {
        PointF location = mapView.getMarkLocationOnOriginalMap();
        if (location == null) {
            return null;
        }
        return new Position(location.x, location.y);
    }
    
    public void showMarkLocation(boolean showMarkLocation) {
    	mapView.showMarkLocation(showMarkLocation);
    }
  
    public void setOnMapClickListener (OnMapClickListener onMapClickListener) {
    	mapView.setOnMapClickListener(onMapClickListener);
    }
}
