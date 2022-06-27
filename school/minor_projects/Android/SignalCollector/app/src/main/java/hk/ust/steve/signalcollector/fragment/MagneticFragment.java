package hk.ust.steve.signalcollector.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;

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

public class MagneticFragment extends BaseFragment {

    private LineChart mLineChart;
    private Typeface mTfRegular, mTfLight;
    private LineData mLineData;
    private LineDataSet mDataSetX, mDataSetY, mDataSetZ;
    private LinkedList<Entry> mEntriesX, mEntriesY, mEntriesZ;

    public static MagneticFragment newInstance() {
        MagneticFragment fragment = new MagneticFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_magnetic;
    }

    @Override
    protected void onViewCreated(View view) {
        mLineChart = view.findViewById(R.id.chart_live_mag);
        mTfRegular = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");

        initChart();
    }

    private void initChart() {
        mLineChart.setDragEnabled(true);
        mLineChart.setScaleEnabled(true);
        mLineChart.getAxisLeft().setAxisMaximum(100f);
        mLineChart.getAxisLeft().setAxisMinimum(-100f);
        mLineChart.getAxisRight().setEnabled(false);
        mLineChart.getXAxis().setAxisMaximum((float)LineDataEvent.LIMIT + 1);
        mLineChart.getXAxis().setAxisMinimum(0f);

        mEntriesX = new LinkedList<>();
        mEntriesX.add(new Entry(0f, 0));
        mEntriesY = new LinkedList<>();
        mEntriesY.add(new Entry(0f, 0));
        mEntriesZ = new LinkedList<>();
        mEntriesZ.add(new Entry(0f, 0));

        mDataSetX = getDataSet(mEntriesX, "MagEarth-X", Color.RED);
        mDataSetY = getDataSet(mEntriesY, "MagEarth-Y", Color.GREEN);
        mDataSetZ = getDataSet(mEntriesZ, "MagEarth-Z", Color.BLUE);
        mLineData = new LineData(mDataSetX, mDataSetY, mDataSetZ);
        mLineChart.setData(mLineData);
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
        if(event.getSignalType() == LineDataEvent.SignalType.MAGNETIC){

            if(mEntriesX.size()<LineDataEvent.LIMIT){
                mEntriesX.add(new Entry((float)mEntriesX.size(), event.getDataList().get(0).strength));
                mEntriesY.add(new Entry((float)mEntriesY.size(), event.getDataList().get(1).strength));
                mEntriesZ.add(new Entry((float)mEntriesZ.size(), event.getDataList().get(2).strength));
            }else{
                mEntriesX.poll();
                mEntriesY.poll();
                mEntriesZ.poll();
                for(Entry entry:mEntriesX){
                    entry.setX(entry.getX()-1f);
                }
                for(Entry entry:mEntriesY){
                    entry.setX(entry.getX()-1f);
                }
                for(Entry entry:mEntriesZ){
                    entry.setX(entry.getX()-1f);
                }
                mEntriesX.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(0).strength));
                mEntriesY.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(1).strength));
                mEntriesZ.offer(new Entry((float)LineDataEvent.LIMIT, event.getDataList().get(2).strength));
            }

            mLineChart.notifyDataSetChanged();
            mLineChart.postInvalidate();
        }
    }
}
