package hk.ust.steve.signalcollector.fragment;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.base.BaseFragment;
import hk.ust.steve.signalcollector.event.LineDataEvent;

/**
 * Created by Steve on 26/9/2017.
 */

public class CompassFragment extends BaseFragment {

    TextView azimuth, pitch, roll;
    LineChart lineChart;
    ImageView imageView;
    Matrix matrix = new Matrix();
    LineData lineData;
    LineDataSet dataSetX, dataSetY, dataSetZ;
    LinkedList<Entry> entriesX, entriesY, entriesZ;

    public static CompassFragment newInstance() {
        CompassFragment fragment = new CompassFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_compass;
    }

    @Override
    protected void onViewCreated(View view) {
        azimuth = view.findViewById(R.id.azimuth);
        pitch = view.findViewById(R.id.pitch);
        roll = view.findViewById(R.id.roll);
        imageView = view.findViewById(R.id.compass_image);
        imageView.setImageDrawable(resize(imageView.getDrawable()));
        lineChart = view.findViewById(R.id.chart_live_compass);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.getAxisLeft().setAxisMaximum(360f);
        lineChart.getAxisLeft().setAxisMinimum(-180f);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setAxisMaximum((float) LineDataEvent.LIMIT + 1);
        lineChart.getXAxis().setAxisMinimum(0f);

        entriesX = new LinkedList<>();
        entriesY = new LinkedList<>();
        entriesZ = new LinkedList<>();
        entriesX.add(new Entry(0f, 0f));
        entriesY.add(new Entry(0f, 0f));
        entriesZ.add(new Entry(0f, 0f));

        dataSetX = getDataSet(entriesX, "azimuth", Color.RED);
        dataSetY = getDataSet(entriesY, "pitch", Color.BLUE);
        dataSetZ = getDataSet(entriesZ, "roll", Color.GREEN);
        lineData = new LineData(dataSetX, dataSetY, dataSetZ);
        lineChart.setData(lineData);

    }

    private Drawable resize(Drawable image) {
        Bitmap b = ((BitmapDrawable)image).getBitmap();
        Bitmap bitmapResized = Bitmap.createScaledBitmap(b, 500, 500, false);
        return new BitmapDrawable(getResources(), bitmapResized);
    }

    private LineDataSet getDataSet(LinkedList<Entry> list, String label, int color) {
        LineDataSet dataSet = new LineDataSet(list, label);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        dataSet.setColor(color);
        dataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        dataSet.setCircleColor(color);
        dataSet.setLineWidth(1f);
        dataSet.setCircleRadius(2f);
        dataSet.setValueTextSize(9f);
        dataSet.setDrawValues(false);
        return dataSet;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }

    @Subscribe
    public void updateView(LineDataEvent event) {
        if (event.getDataList() == null || event.getDataList().isEmpty()) {
            return;
        }
        if(event.getSignalType() == LineDataEvent.SignalType.ORIENTATION){
            azimuth.setText("azimuth:" + event.getDataList().get(0).strength);
            pitch.setText("pitch:" + event.getDataList().get(1).strength);
            roll.setText("roll:" + event.getDataList().get(2).strength);
            matrix.setRotate(-1.0f * event.getDataList().get(0).strength, imageView.getDrawable().getBounds().width()/2, imageView.getDrawable().getBounds().height()/2);
            imageView.setImageMatrix(matrix);

            if(entriesX.size()<LineDataEvent.LIMIT){
                entriesX.add(new Entry((float)entriesX.size(), event.getDataList().get(0).strength));
                entriesY.add(new Entry((float)entriesY.size(), event.getDataList().get(1).strength));
                entriesZ.add(new Entry((float)entriesZ.size(), event.getDataList().get(2).strength));
            }else{
                entriesX.poll();
                entriesY.poll();
                entriesZ.poll();
                for(Entry entry:entriesX){
                    entry.setX(entry.getX()-1f);
                }
                for(Entry entry:entriesY){
                    entry.setX(entry.getX()-1f);
                }
                for(Entry entry:entriesZ){
                    entry.setX(entry.getX()-1f);
                }
                entriesX.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(0).strength));
                entriesY.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(1).strength));
                entriesZ.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(2).strength));
            }
            lineChart.notifyDataSetChanged();
            lineChart.postInvalidate();
        }
    }
}
