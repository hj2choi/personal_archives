package hk.ust.steve.signalcollector.event;

import com.github.mikephil.charting.data.Entry;

import java.util.List;

import hk.ust.steve.signalcollector.bean.ChartSignal;

/**
 * Created by Steve on 19/9/2017.
 * Modified by Hong Joon on 06/10/2017. (added linear_acceleration)
 * Modified by Zhi on 07/10/2017. (added gyroscope)
 */

public class LineDataEvent {

    public enum SignalType {MAGNETIC, ACCELEROMETER, LINEAR_ACCELERATION, BAROMETER, GYROSCOPE, GRAVITY, ORIENTATION};
    public static final int LIMIT = 100;

    private SignalType mSignalType;
    private List<ChartSignal> mDataList;

    public LineDataEvent(SignalType type, List<ChartSignal> dataList) {
        mDataList = dataList;
        mSignalType = type;
    }

    public List<ChartSignal> getDataList() {
        return mDataList;
    }

    public void setDataList(List<ChartSignal> mDataList) {
        this.mDataList = mDataList;
    }

    public SignalType getSignalType() {
        return mSignalType;
    }

    public void setSignalType(SignalType mSignalType) {
        this.mSignalType = mSignalType;
    }
}
