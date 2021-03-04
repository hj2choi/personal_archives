package hk.ust.steve.signalcollector.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

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

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.base.BaseFragment;
import hk.ust.steve.signalcollector.event.LineDataEvent;

/**
 * Created by Steve on 26/9/2017.
 * Modified by Hong Joon on 06/10/2017
 * Modified by Zhi on 07/10/2017
 */

public class AcceFragment extends BaseFragment {

    private static final float NS2S = 1.0f/1000000000.0f;

    private LineChart mChart;
    private TextView mTxtDebug;
    private TextView mTxtAcceX;
    private TextView mTxtAcceY;
    private TextView mTxtAcceZ;
    private TextView mTxtVelX;
    private TextView mTxtPosX;
    private TextView mTxtAveAccX;
    private Typeface mTfRegular;
    private Typeface mTfLight;
    private int[] mColors;
    private ArrayList<ILineDataSet> mWiFiSets;
    private LineData mWiFiLineData;

    private float[] integral;
    private float[] doubleIntegral;
    private float average;
    private float steps; // integer value
    private float prevTimestamp;

    public static AcceFragment newInstance() {
        AcceFragment fragment = new AcceFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_accelerometer;
    }

    @Override
    protected void onViewCreated(View view) {
        mTxtDebug = view.findViewById(R.id.txt_debug);
        mTxtAcceX = view.findViewById(R.id.txt_acc_x);
        mTxtAcceY = view.findViewById(R.id.txt_acc_y);
        mTxtAcceZ = view.findViewById(R.id.txt_acc_z);
        mTxtVelX = view.findViewById(R.id.txt_vel_x);
        mTxtPosX = view.findViewById(R.id.txt_pos_x);
        mTxtAveAccX = view.findViewById(R.id.txt_aveacc_x);

        mChart = view.findViewById(R.id.chart_live_accelerometer);
        mTfRegular = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(mContext.getAssets(), "OpenSans-Light.ttf");

        mColors = mContext.getResources().getIntArray(R.array.chart_colors);


        integral = new float[3];
        doubleIntegral = new float[3];
        average = 0;
        steps = 0;
        initChart(view);


        Log.d("AcceFragment", "onViewCreated()");
    }

    private LineDataSet createSet(String legend) {
        LineDataSet set = new LineDataSet(null, legend);
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setLineWidth(1f);
        //set.setCircleRadius(2f);
        //set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
    }

    private void initChart(View view) {

        mWiFiSets = new ArrayList<>();

        mWiFiLineData = new LineData(mWiFiSets);

        mChart = view.findViewById(R.id.chart_live_accelerometer);

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
        leftAxis.setAxisMaximum(20f);
        leftAxis.setAxisMinimum(-20f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);

        // add entry
        LineDataSet set = createSet("x");
        set.addEntry(new Entry(set.getEntryCount(), 0f));
        set.addEntry(new Entry(set.getEntryCount(), 1f));
        //set.addEntry(new Entry(set.getEntryCount(), 2f));
        int color = mColors[(int)(Math.random() * 140)];
        set.setColor(color);
        set.setCircleColor(color);
        set.setFillColor(color);
        mWiFiSets.add(set);
        //mApSignalList.add(signal);
        mChart.notifyDataSetChanged();
        mChart.postInvalidate();
    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
    @Subscribe
    public void updateView(LineDataEvent event) {



        if (event.getDataList() == null || event.getDataList().isEmpty()) {
            return;
        }
        if(event.getSignalType() == LineDataEvent.SignalType.ACCELEROMETER){
            //mTxtAcceX.setText("x = "+String.format("%1$" + 30 + "%.2f", event.getDataList().get(0).strength));
            //mTxtAcceY.setText("y = "+String.format("%1$" + 30 + "%.2f", event.getDataList().get(1).strength));
            //mTxtAcceZ.setText("z = "+String.format("%1$" + 30 + "%.2f", event.getDataList().get(2).strength));
        }
        if(event.getSignalType() == LineDataEvent.SignalType.LINEAR_ACCELERATION){
            // temporary variables for easier representation
            float[] acc_val = new float[3];
            for (int i=0; i<3; ++i) {
                acc_val[i] = event.getDataList().get(i).strength;
            }

            // compute timestamp
            if (steps==0){
                prevTimestamp = event.getDataList().get(0).microTimeStamp;
            }
            long currentTimeStamp = event.getDataList().get(0).microTimeStamp;

            // compute integral and double integral
            for (int i=0; i<event.getDataList().size(); ++i) {
                integral[i] += acc_val[i]*(currentTimeStamp-prevTimestamp)*NS2S;
                doubleIntegral[i] += integral[i]*(currentTimeStamp-prevTimestamp)*NS2S;
                average = average*((steps)/(steps+1)) + acc_val[i]*(1/(steps+1));
            }

            // update text
            mTxtAcceX.setText("x_acc = "+String.format("%1$" + 15 + "s", String.format("%.2f",acc_val[0])));
            mTxtAcceY.setText("y_acc = "+String.format("%1$" + 15 + "s", String.format("%.2f",acc_val[1])));
            mTxtAcceZ.setText("z_acc = "+String.format("%1$" + 15 + "s", String.format("%.2f",acc_val[2])));
            mTxtVelX.setText("Vel = ("+String.format("%.2f",integral[0])+", "+String.format("%.2f",integral[1])+", "+String.format("%.2f",integral[2])+")");
            mTxtPosX.setText("Vel = ("+String.format("%.2f",doubleIntegral[0])+", "+String.format("%.2f",doubleIntegral[1])+", "+String.format("%.2f",doubleIntegral[2])+")");
            mTxtAveAccX.setText("x_ave_acc = "+String.format("%1$" + 15 + "s", String.format("%.2f",average)));

            // update chart
            if (steps%100==0) {
                //TODO: invalidate() seem to cause java.lang.NegativeArraySizeException: -2
                //Log.d("AcceFragment", "updateView() chartEntryCount = "+mWiFiSets.get(0).getEntryCount());
                //mWiFiSets.get(0).addEntry(new Entry((steps/500f), acc_val[0]));
                //mWiFiSets.get(0).addEntryOrdered(new Entry((steps/500f), acc_val[0]));
                //mChart.notifyDataSetChanged();
                //Log.d("AcceFragment", "added entry");
                //mChart.invalidate();
            }

            // timestamp post_process
            prevTimestamp = currentTimeStamp;
            steps++;
        }
    }
}
