package hk.ust.steve.signalcollector.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import org.greenrobot.eventbus.Subscribe;

import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.base.BaseFragment;
import hk.ust.steve.signalcollector.event.LineDataEvent;

/**
 * Created by Steve on 26/9/2017.
 * Modified by Zeng Zhi on 12/10/2017
 */

public class PedoFragment extends BaseFragment {
    TextView textView;
    LineChart lineChart;
    LineData lineData;
    LineDataSet dataSetX;
    LinkedList<Entry> entriesX;
    PedometerQueueThread mPedoQueueThread = new PedometerQueueThread();
    private long mLastTime = System.currentTimeMillis();
    private final int DURATION_GAP = 100;

    int mCount = 0;
    float[] oriValues = new float[3];
    final int valueNum = 4;
    //用于存放计算阈值的波峰波谷差值
    float[] tempValue = new float[valueNum];
    int tempCount = 0;
    //是否上升的标志位
    boolean isDirectionUp = false;
    //持续上升次数
    int continueUpCount = 0;
    //上一点的持续上升的次数，为了记录波峰的上升次数
    int continueUpFormerCount = 0;
    //上一点的状态，上升还是下降
    boolean lastStatus = false;
    //波峰值
    float peakOfWave = 0;
    //波谷值
    float valleyOfWave = 0;
    //此次波峰的时间
    long timeOfThisPeak = 0;
    //上次波峰的时间
    long timeOfLastPeak = 0;
    //当前的时间
    long timeOfNow = 0;
    //当前传感器的值
    float gravityNew = 0;
    //上次传感器的值
    float gravityOld = 0;
    //动态阈值需要动态的数据，这个值用于这些动态数据的阈值
    final float initialValue = (float) 1.3;
    //初始阈值
    float ThresholdValue = (float) 2.0;

    public static PedoFragment newInstance() {
        PedoFragment fragment = new PedoFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return true;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_pedometer;
    }

    @Override
    protected void onViewCreated(View view) {
        textView = view.findViewById(R.id.text);
        textView.setText("Step count: " + mCount);
        lineChart = view.findViewById(R.id.chart_live_step);
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);
        lineChart.getAxisLeft().setAxisMaximum(2f);
        lineChart.getAxisLeft().setAxisMinimum(-2f);
        lineChart.getAxisRight().setEnabled(false);
        lineChart.getXAxis().setAxisMaximum((float) LineDataEvent.LIMIT + 1);
        lineChart.getXAxis().setAxisMinimum(0f);

        entriesX = new LinkedList<>();
        entriesX.offer(new Entry(0f, 0));

        dataSetX = getDataSet(entriesX, "Coor-X", Color.BLUE);
        lineData = new LineData(dataSetX);
        lineChart.setData(lineData);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        try {
            mPedoQueueThread.shutDown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private class PedometerQueueThread extends Thread {

        private static final int SHUTDOWN_REQ = -1;
        private BlockingQueue<Integer> mStepQueue = new ArrayBlockingQueue<>(100);
        private volatile boolean mShutdown, mThreadTerminated;

        private PedometerQueueThread() {
            start();
        }

        @Override
        public void run() {
            try {
                int step;
                while ((step = mStepQueue.take()) != SHUTDOWN_REQ) {
                    if (entriesX.size() >= LineDataEvent.LIMIT) {
                        entriesX.poll();
                        for (Entry entry : entriesX) {
                            entry.setX(entry.getX() - 1f);
                        }
                    }
                    entriesX.offer(new Entry((float) entriesX.size(), step));
                    lineChart.notifyDataSetChanged();
                    lineChart.postInvalidate();
                }
            } catch (InterruptedException iex) {
            } finally {
                mThreadTerminated = true;
            }
        }

        public void addStep(int step) {
            if (mShutdown || mThreadTerminated) return;
            try {
                mStepQueue.put(step);
            } catch (InterruptedException iex) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Unexpected interruption");
            }
        }

        public void shutDown() throws InterruptedException {
            mShutdown = true;
            mStepQueue.put(SHUTDOWN_REQ);
        }

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

        if (event.getSignalType() == LineDataEvent.SignalType.ACCELEROMETER) {
            for (int i = 0; i < 3; i++) {
                oriValues[i] = event.getDataList().get(i).strength;
            }
            gravityNew = (float) Math.sqrt(oriValues[0] * oriValues[0]
                    + oriValues[1] * oriValues[1] + oriValues[2] * oriValues[2]);
            DetectorNewStep(gravityNew);
        }
    }

    public void DetectorNewStep(float values) {
        if (gravityOld == 0) {
            gravityOld = values;
            if (System.currentTimeMillis() - mLastTime > DURATION_GAP) {
                mPedoQueueThread.addStep(0);
                mLastTime = System.currentTimeMillis();
            }
        } else {
            if (DetectorPeak(values, gravityOld)) {
                timeOfLastPeak = timeOfThisPeak;
                timeOfNow = System.currentTimeMillis();
                if (timeOfNow - timeOfLastPeak >= 250
                        && (peakOfWave - valleyOfWave >= ThresholdValue)) {
                    timeOfThisPeak = timeOfNow;
                    mCount++;
                    textView.setText("Step count: " + mCount);
                    mPedoQueueThread.addStep(1);
                    mLastTime = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - mLastTime > DURATION_GAP) {
                        mPedoQueueThread.addStep(0);
                        mLastTime = System.currentTimeMillis();
                    }
                }
                if (timeOfNow - timeOfLastPeak >= 250
                        && (peakOfWave - valleyOfWave >= initialValue)) {
                    timeOfThisPeak = timeOfNow;
                    ThresholdValue = Peak_Valley_Threshold(peakOfWave - valleyOfWave);
                }
            } else {
                if (System.currentTimeMillis() - mLastTime > DURATION_GAP) {
                    mPedoQueueThread.addStep(0);
                    mLastTime = System.currentTimeMillis();
                }
            }
        }
        gravityOld = values;
    }

    public boolean DetectorPeak(float newValue, float oldValue) {
        lastStatus = isDirectionUp;
        if (newValue >= oldValue) {
            isDirectionUp = true;
            continueUpCount++;
        } else {
            continueUpFormerCount = continueUpCount;
            continueUpCount = 0;
            isDirectionUp = false;
        }

        if (!isDirectionUp && lastStatus
                && (continueUpFormerCount >= 2 || oldValue >= 20)) {
            peakOfWave = oldValue;
            return true;
        } else if (!lastStatus && isDirectionUp) {
            valleyOfWave = oldValue;
            return false;
        } else {
            return false;
        }
    }

    public float Peak_Valley_Threshold(float value) {
        float tempThread = ThresholdValue;
        if (tempCount < valueNum) {
            tempValue[tempCount] = value;
            tempCount++;
        } else {
            tempThread = averageValue(tempValue, valueNum);
            for (int i = 1; i < valueNum; i++) {
                tempValue[i - 1] = tempValue[i];
            }
            tempValue[valueNum - 1] = value;
        }
        return tempThread;
    }

    /*
     * 梯度化阈值
     * */
    public float averageValue(float value[], int n) {
        float ave = 0;
        for (int i = 0; i < n; i++) {
            ave += value[i];
        }
        ave = ave / valueNum;
        if (ave >= 8)
            ave = (float) 4.3;
        else if (ave >= 7 && ave < 8)
            ave = (float) 3.3;
        else if (ave >= 4 && ave < 7)
            ave = (float) 2.3;
        else if (ave >= 3 && ave < 4)
            ave = (float) 2.0;
        else {
            ave = (float) 1.3;
        }
        return ave;
    }

}
