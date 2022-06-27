package hk.ust.steve.signalcollector.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.view.View;
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
 * Modified by Zhi on 7/10/2017.
 */

public class GravityFragment extends BaseFragment {

    TextView gravityX, gravityY, gravityZ;
    LineChart lineChart;
    LineData lineData;
    LineDataSet dataSetX, dataSetY, dataSetZ;
    LinkedList<Entry> entriesX, entriesY, entriesZ;

    public static GravityFragment newInstance() {
        GravityFragment fragment = new GravityFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_gravity;
    }

    @Override
    protected void onViewCreated(View view) {
        gravityX = view.findViewById(R.id.gravity_x);
        gravityY = view.findViewById(R.id.gravity_y);
         gravityZ = view.findViewById(R.id.gravity_z);
        lineChart = view.findViewById(R.id.chart_live_gravity);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.getAxisLeft().setAxisMaximum(12f);
        lineChart.getAxisLeft().setAxisMinimum(-12f);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setAxisMaximum((float)LineDataEvent.LIMIT + 1);
        lineChart.getXAxis().setAxisMinimum(0f);

        entriesX = new LinkedList<>();
        entriesY = new LinkedList<>();
        entriesZ = new LinkedList<>();
        entriesX.add(new Entry(0f, 0f));
        entriesY.add(new Entry(0f, 0f));
        entriesZ.add(new Entry(0f, 0f));

        dataSetX = getDataSet(entriesX, "Coor-X", Color.RED);
        dataSetY = getDataSet(entriesY, "Coor-Y", Color.BLUE);
        dataSetZ = getDataSet(entriesZ, "Coor-Z", Color.GREEN);
        lineData = new LineData(dataSetX, dataSetY, dataSetZ);
        lineChart.setData(lineData);
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
        if(event.getSignalType() == LineDataEvent.SignalType.GRAVITY){
            gravityX.setText("axisX:" + event.getDataList().get(0).strength);
            gravityY.setText("axisY:" + event.getDataList().get(1).strength);
            gravityZ.setText("axisZ:" + event.getDataList().get(2).strength);

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
