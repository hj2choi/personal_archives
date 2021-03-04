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

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.adapter.WiFiFragmentAdapter;
import hk.ust.steve.signalcollector.base.BaseFragment;
import hk.ust.steve.signalcollector.bean.ApSignal;
import hk.ust.steve.signalcollector.event.WiFiSignalCollectedEvent;

/**
 * Created by Steve on 26/9/2017.
 */

public class CellularFragment extends BaseFragment {

    private LineChart mChart;
    private WiFiFragmentAdapter mCellularAdapter;
    private RecyclerView mCellularRV;
    private List<ApSignal> mCellularSignalList;
    private LineData mCellularLineData;
    private ArrayList<ILineDataSet> mCellularSets;
    private Typeface mTfRegular, mTfLight;
    private int[] mColors;
    private int mNumSample;

    public static CellularFragment newInstance() {
        CellularFragment fragment = new CellularFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return false;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_cellular;
    }

    @Override
    protected void onViewCreated(View view) {
        mChart = view.findViewById(R.id.chart_live_cellular);
        mTfRegular = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");

        mCellularRV = view.findViewById(R.id.rv_list_cell);
        mCellularRV.setLayoutManager(new LinearLayoutManager(mContext));
        mColors = mContext.getResources().getIntArray(R.array.chart_colors);

        mNumSample = 0;
        initAdapter();
        initChart(view);
    }

    private void initAdapter() {
        mCellularSignalList = new ArrayList<>();
        mCellularAdapter = new WiFiFragmentAdapter(R.layout.item_ap_signal, mCellularSignalList);
        mCellularRV.setAdapter(mCellularAdapter);
    }

    private void initChart(View view) {

        mCellularSets = new ArrayList<>();

        mCellularLineData = new LineData(mCellularSets);

        mChart = view.findViewById(R.id.chart_live_cellular);

        // enable description text
        mChart.getDescription().setEnabled(true);

        // enable touch gestures
        mChart.setTouchEnabled(true);

        // enable scaling and dragging
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);
        mChart.setDrawGridBackground(false);

        // if disabled, scaling can be done on x- and y-axis separately
        mChart.setPinchZoom(true);

        // set an alternative background color
        mChart.setBackgroundColor(Color.WHITE);

        // add empty data
        mChart.setData(mCellularLineData);

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
        LineDataSet set = new LineDataSet(null, legend);
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

    /*@Subscribe
    public void CellularSignalCollected(WiFiSignalCollectedEvent event) {
        for (ApSignal signal : event.getApSignalList()) {
            boolean contained = false;
            int index = 0;
            for (ApSignal signalContained : mCellularSignalList) {
                if ((signal.getApAddress()+"").equals(signalContained.getApAddress()+"")){
                    signalContained.setRssi(signal.getRssi());
                    contained = true;
                    if(mCellularSets.size() > index) {
                        mCellularSets.get(index).addEntry(new Entry(mCellularSets.get(index).getEntryCount(), (float)signal.getRssi()));
                    }
                    break;
                }
                index++;
            }
            if (!contained) {
                LineDataSet set = createSet(signal.getApName()+"");
                for (int i=0; i<mNumSample; i++) {
                    set.addEntry(new Entry(set.getEntryCount(), -120f));
                }
                set.addEntry(new Entry(set.getEntryCount(), (float) signal.getRssi()));

                int color = mColors[(int)(Math.random() * 140)];
                set.setColor(color);
                set.setCircleColor(color);
                set.setFillColor(color);
                mCellularSets.add(set);
                mCellularSignalList.add(signal);
            }
        }
        mCellularAdapter.notifyDataSetChanged();
        mCellularLineData.notifyDataChanged();
        // let the chart know it's data has changed
        mChart.notifyDataSetChanged();
        mChart.postInvalidate();
        mNumSample++;
    }*/
}
