package hk.ust.steve.signalcollector.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.adapter.WiFiFragmentAdapter;
import hk.ust.steve.signalcollector.base.BaseFragment;
import hk.ust.steve.signalcollector.bean.ApSignal;
import hk.ust.steve.signalcollector.event.LineDataEvent;
import hk.ust.steve.signalcollector.event.WiFiSignalCollectedEvent;

/**
 * Created by Steve on 26/9/2017.
 */

public class WiFiFragment extends BaseFragment {

    private LineChart mChart;
    private WiFiFragmentAdapter mWiFiAdapter;
    private RecyclerView mApRV;
    private List<ApSignal> mApSignalList;
    private LineData mWiFiLineData;
    private ArrayList<ILineDataSet> mWiFiSets;
    private Typeface mTfRegular, mTfLight;
    private int[] mColors;
    private int mNumSample;

    public static WiFiFragment newInstance() {
        WiFiFragment fragment = new WiFiFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_wifi;
    }

    @Override
    protected void onViewCreated(View view) {
        mChart = view.findViewById(R.id.chart_live_wifi);
        mTfRegular = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");

        mApRV = view.findViewById(R.id.rv_list_ap);
        mApRV.setLayoutManager(new LinearLayoutManager(mContext));
        mColors = mContext.getResources().getIntArray(R.array.chart_colors);

        mNumSample = 0;
        initAdapter();
        initChart(view);
    }

    private void initAdapter() {
        mApSignalList = new ArrayList<>();
        mWiFiAdapter = new WiFiFragmentAdapter(R.layout.item_ap_signal, mApSignalList);
        mApRV.setAdapter(mWiFiAdapter);
    }

    private void initChart(View view) {

        mWiFiSets = new ArrayList<>();

        mWiFiLineData = new LineData(mWiFiSets);

        mChart = view.findViewById(R.id.chart_live_wifi);

        // enable description text
        mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);
        mChart.getXAxis().setAxisMinimum(0f);
        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);

        // add empty data
        mChart.setData(mWiFiLineData);

        // limit the number of visible entries
        // mChart.setVisibleXRangeMaximum(5);

        // get the legend (only possible after setting data)
        Legend l = mChart.getLegend();

        // modify the legend ...
        l.setForm(Legend.LegendForm.LINE);
        l.setTypeface(mTfLight);
        l.setTextColor(Color.BLACK);

        XAxis xl = mChart.getXAxis();
        xl.setTypeface(mTfLight);
        xl.setTextColor(Color.BLACK);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(true);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTypeface(mTfLight);
        leftAxis.setTextColor(Color.BLACK);
        leftAxis.setAxisMaximum(0f);
        leftAxis.setAxisMinimum(-120f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);
    }

    private LineDataSet createSet(String legend) {
        LinkedList<Entry> list = new LinkedList<>();
        list.offer(new Entry(0f, -120f));
        LineDataSet set = new LineDataSet(list, legend);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(1f);
        set.setCircleRadius(2f);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }

    @Subscribe
    public void WiFiSignalCollected(WiFiSignalCollectedEvent event) {
        for (ApSignal signal : event.getApSignalList()) {
            boolean contained = false;
            int index = 0;
            for (ApSignal signalContained : mApSignalList) {
                if ((signal.getApAddress()+"").equals(signalContained.getApAddress()+"")){
                    signalContained.setRssi(signal.getRssi());
                    contained = true;
                    if(mWiFiSets.size() > index) {
                        if (mWiFiSets.get(index).getEntryCount() < LineDataEvent.LIMIT) {
                            ((LinkedList)((LineDataSet)mWiFiSets.get(index)).getValues()).offer(new Entry(mWiFiSets.get(index).getEntryCount(), (float) signal.getRssi()));
                        } else {
                            ((LinkedList)((LineDataSet)mWiFiSets.get(index)).getValues()).poll();
                            for (Entry entry : ((LineDataSet) mWiFiSets.get(index)).getValues()) {
                                entry.setX(entry.getX()-1f);
                            }
                            ((LinkedList)((LineDataSet)mWiFiSets.get(index)).getValues()).offer(new Entry(mWiFiSets.get(index).getEntryCount(), (float) signal.getRssi()));
                        }
                    }
                    break;
                }
                index++;
            }
            if (!contained) {
                LineDataSet set = createSet(signal.getApName()+"");
                //Fill in the previous dots.
                for (int i = 0; i < mNumSample; i++) {
                    ((LinkedList) set.getValues()).offer(new Entry(set.getEntryCount(), -120f));
                }
                //Draw current spot.
                ((LinkedList)set.getValues()).offer(new Entry(set.getEntryCount(), (float) signal.getRssi()));

                int color = mColors[(int)(Math.random() * 140)];
                set.setColor(color);
                set.setCircleColor(color);
                set.setFillColor(color);
                mWiFiSets.add(set);
                mApSignalList.add(signal);
            }
        }
        mWiFiAdapter.notifyDataSetChanged();
        mWiFiLineData.notifyDataChanged();
        if (mNumSample < LineDataEvent.LIMIT) {
            mNumSample++;
        }
        // let the chart know it's data has changed
        mChart.getXAxis().setAxisMaximum((float)mNumSample + 1);
        mChart.notifyDataSetChanged();
        mChart.postInvalidate();
    }
}
