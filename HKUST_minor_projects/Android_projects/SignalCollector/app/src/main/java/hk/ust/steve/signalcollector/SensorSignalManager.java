package hk.ust.steve.signalcollector;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import hk.ust.steve.signalcollector.bean.ChartSignal;
import hk.ust.steve.signalcollector.bean.DataLinkedList;
import hk.ust.steve.signalcollector.bean.SensorData;
import hk.ust.steve.signalcollector.event.CollectedSensorEvent;
import hk.ust.steve.signalcollector.event.LineDataEvent;
import hk.ust.steve.signalcollector.map.SignalWritterThread;
import hk.ust.steve.signalcollector.utils.FileUtils;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * Created by Steve on 19/9/2017.
 * Modified by Hong Joon on 06/10/2017. (added LinearAcceleration)
 */
public class SensorSignalManager implements SensorEventListener {

    private final int SAMPLING_RATE = 10000;
    private final int SLIDING_WINDOW_SIZE = 1500;
    private final int REFRESH_TIME = 10000;              //reset north every 10s
    private final float LOW_PASS_FILTER = 0.48f;
    private final float HIGH_PASS_FILTER = 0.05f;
    private final float MAG_NOISE_THRESHOLD = 60f;       //if magnet-intensity > MAG_NOISE_THRESHOLD, it is regarded as magnet-interference
    private final float ANGULAR_SPEED_THRESHOLD = 3.5f;  //if angular speed > angular_threshold, we should reset north
    private final int SENSORS_COUNT = 7;
    private final float kFilteringFactor = 0.25f;

    private SensorManager mSensorManager;
    private Sensor mMagSensor;
    private Sensor mRotationVectorSensor;
    private Sensor mGyroscopeSensor;
    private Sensor mGravitySensor;
    private Sensor mAccSensor;
    private Sensor mLinearAccSensor;
    private Sensor mBarometer;
    private Sensor mOrientationSensor;

    private LinkedList<SensorData> mMagRawList;
    private LinkedList<SensorData> mMagRefinedList;
    private LinkedList<SensorData> mRotationRawList;
    private LinkedList<SensorData> mOrientationRawList;
    private LinkedList<SensorData> mGravityRawList;
    private LinkedList<SensorData> mGyroDataList;
    private LinkedList<SensorData> mDecompositionMagData;
    private LinkedList<SensorData> mAccDataList;
    private LinkedList<SensorData> mLinearAccDataList;
    private LinkedList<SensorData> mBarometerDataList;
    private List<ArrayList<float[]>> mHistoricalData;
    private ArrayList<float[]> mHistoricalFilter;
    private DataLinkedList mMagLinkedList;

    private float mMagFieldIntensity = 0f;                  //magnet-field intensity
    private float mMaxAngularSpeed = 0f;
    private long mMagDegreeTimestamp = 0;

    private float[] mSmoothedData;
    private float[] mReferenceDegree = {0, 0, 0};            //reference degree
    private float[] mMagDegree = {0, 0, 0};                  //magnet degree
    private final float[] mQuatDegrees = {0, 0, 0};         //absolute-north degree
    private final float[] mRotateDegrees = {0, 0, 0};       //delta absolute-north degree
    private long mRotateDegreesTimestamp = 0;
    private float[] mDeltaAngular = {0, 0, 0};
    private float[] mAngularSpeed = {0, 0, 0};
    private float[] mDiff = {0, 0, 0};
    private float[][] mDiffArr = new float[3][SLIDING_WINDOW_SIZE];

    private int mIter = 0;
    private long mPeriod = 0;
    private boolean mDirectionStarted = false;

    private float[] mRMatrix = new float[9];
    private float[] mDeltaQuater = {0, 0, 0, 0};
    private float[] mOldQuaternion = {0, 0, 0, 0};
    private float[] mNewQuaternion = {0, 0, 0, 0};

    private boolean mCollecting;

    public SensorSignalManager(Context context, boolean collecting) {
        mSensorManager = (SensorManager) context.getSystemService(Activity.SENSOR_SERVICE);
        mMagSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mGyroscopeSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
        mRotationVectorSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
        mGravitySensor = mSensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY);
        mAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mBarometer = mSensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
        mLinearAccSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
        mOrientationSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        List<Sensor> sensors = mSensorManager.getSensorList(Sensor.TYPE_ALL);

        mMagRawList = new LinkedList<>();
        mMagRefinedList = new LinkedList<>();
        mMagLinkedList = new DataLinkedList(50, 3);
        mRotationRawList = new LinkedList<>();
        mOrientationRawList = new LinkedList<>();
        mGravityRawList = new LinkedList<>();
        mGyroDataList = new LinkedList<>();
        mAccDataList = new LinkedList<>();
        mLinearAccDataList = new LinkedList<>();
        mBarometerDataList = new LinkedList<>();
        mDecompositionMagData = new LinkedList<>();
        mHistoricalData = new LinkedList<>();

        for (int i = 0; i < SENSORS_COUNT; ++i)
            mHistoricalData.add(new ArrayList<float[]>());
        mHistoricalFilter = new ArrayList<>();
        for (int i = 0; i < SENSORS_COUNT; ++i)
            mHistoricalFilter.add(new float[]{-99999});

        registerSensors();
        mCollecting = collecting;
    }

    private void registerSensors() {
        mSensorManager.registerListener(this, mRotationVectorSensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mMagSensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mGyroscopeSensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mGravitySensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mAccSensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mBarometer, SAMPLING_RATE);
        mSensorManager.registerListener(this, mLinearAccSensor, SAMPLING_RATE);
        mSensorManager.registerListener(this, mOrientationSensor, SAMPLING_RATE);
    }

    private void unRegisterSensors() {
        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (mCollecting) {
            postEvent(event);
        }
        mSmoothedData = smooth(event);
        long startTime = 0;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            if (mMagRawList.size() > LineDataEvent.LIMIT) {
                mMagRawList.poll();
            }
            mMagRawList.offer(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            if (mGravityRawList.size() > 0 && mGyroDataList.size() > 0) {
                float[] R = new float[9], I = new float[9];
                float[] gravity = mGravityRawList.get(mGravityRawList.size() - 1).values;
                float[] magDevice = mMagRawList.get(mMagRawList.size() - 1).values;
                if (SensorManager.getRotationMatrix(R, I, gravity, magDevice)) {
                    /*float value = mGyroDataList.get(mGyroDataList.size() - 1).values[0];
                    float intensity = (float) Math.sqrt(magnet[0] * magnet[0] + magnet[1] * magnet[1] + magnet[2] * magnet[2]);
                    SensorData temp = SensorData.toSensorData(
                            event.timestamp,
                            new float[]{intensity,
                                    magnet[0] * R[6] + magnet[1] * R[7] + magnet[2] * R[8],
                                    magnet[0] * R[3] + magnet[1] * R[4] + magnet[2] * R[5],
                                    value},
                            0);
                    mDecompositionMagData.add(temp);*/
                    float[] magEarth = new float[3];
                    magEarth[0] = R[0] * magDevice[0] + R[1] * magDevice[1] + R[2] * magDevice[2];
                    magEarth[1] = R[3] * magDevice[0] + R[4] * magDevice[1] + R[5] * magDevice[2];
                    magEarth[2] = R[6] * magDevice[0] + R[7] * magDevice[1] + R[8] * magDevice[2];
                    List<ChartSignal> signals = new ArrayList<>();
                    for (float s : magEarth) {
                        signals.add(new ChartSignal(s));
                    }
                    if (!mCollecting) {
                        EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.MAGNETIC, signals));
                    }
                }
            }
        } else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
            calibrateRotationVector(event);
        } else if(event.sensor.getType() == Sensor.TYPE_ORIENTATION){
            if (mOrientationRawList.size() > LineDataEvent.LIMIT) {
                mOrientationRawList.poll();
            }
            mOrientationRawList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            List<ChartSignal> signals = new ArrayList<>();
            for (float s: mSmoothedData) {
                signals.add(new ChartSignal(s));
            }
            if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.ORIENTATION, signals));
            }
        } else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) {
            if (mGravityRawList.size() > LineDataEvent.LIMIT) {
                mGravityRawList.poll();
            }
            mGravityRawList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            List<ChartSignal> signals = new ArrayList<>();
            for (float s: mSmoothedData) {
                signals.add(new ChartSignal(s));
            }
            if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.GRAVITY, signals));
            }
        } else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            if (mGyroDataList.size() > LineDataEvent.LIMIT) {
                mGyroDataList.poll();
            }
            mGyroDataList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            calibrateGyro(event, startTime);
            List<ChartSignal> signals = new ArrayList<>();
            for (float s: mSmoothedData) {
                signals.add(new ChartSignal(s));
            }
            if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.GYROSCOPE, signals));
            }
        } else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            if (mAccDataList.size() > LineDataEvent.LIMIT) {
                mAccDataList.poll();
            }
            mAccDataList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            List<ChartSignal> signals = new ArrayList<>();
            for (float s: mSmoothedData) {
                signals.add(new ChartSignal(s));
            }
            if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.ACCELEROMETER, signals));
            }
        } else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            if (mLinearAccDataList.size() > LineDataEvent.LIMIT) {
                mLinearAccDataList.poll();
            }
            mLinearAccDataList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
            List<ChartSignal> signals = new ArrayList<>();
            for (float s: mSmoothedData) {
                signals.add(new ChartSignal(s));
            }
            if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.LINEAR_ACCELERATION, signals));
            }
        } else if (event.sensor.getType() == Sensor.TYPE_PRESSURE) {
            if (mBarometerDataList.size() > LineDataEvent.LIMIT) {
                mBarometerDataList.poll();
            }
            mBarometerDataList.add(SensorData.toSensorData(event.timestamp, event.values, 0));
            List<ChartSignal> signals = new ArrayList<>();
            signals.add(new ChartSignal(event.values[0]));
            /*if (!mCollecting) {
                EventBus.getDefault().post(new LineDataEvent(LineDataEvent.SignalType.BAROMETER, signals));
            }*/
        }

    }

    private void postEvent(SensorEvent event) {
        CollectedSensorEvent cEvent = new CollectedSensorEvent();
        cEvent.accuracy = event.accuracy;
        cEvent.timestamp = event.timestamp;
        cEvent.type = event.sensor.getType();
        cEvent.values = event.values;
        EventBus.getDefault().post(cEvent);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    private float[] smooth(SensorEvent event) {
        int index = -1;
        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) index = 0;
        else if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) index = 1;
        else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) index = 2;
        else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) index = 3;
        else if (event.sensor.getType() == Sensor.TYPE_GRAVITY) index = 4;
        else if (event.sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) index = 5;
        else if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) index = 6;
        else return null;
        ArrayList<float[]> now = mHistoricalData.get(index);
        now.add(event.values.clone());
        if (now.size() >= 6) now.remove(0);
        float[] smoothing = calculateAverage(now);

        // filtering
        float[] ret = mHistoricalFilter.get(index);
        if (ret.length == 1 && Math.abs(ret[0] + 99999) < 1e-5) {
            mHistoricalFilter.set(index, smoothing);
            return smoothing;
        }
        for (int i = 0; i < ret.length; ++i)
            ret[i] = ret[i] + kFilteringFactor * (smoothing[i] - ret[i]);
        mHistoricalFilter.set(index, ret);
        return ret;
    }

    private float[] calculateAverage(ArrayList<float[]> input) {
        float[] ret = new float[input.get(0).length];
        for (int i = 0; i < ret.length; ++i)
            ret[i] = 0;
        for (int i = 0; i < input.size(); ++i) {
            float[] past = input.get(i);
            for (int j = 0; j < ret.length; ++j)
                ret[j] += past[j];
        }
        for (int i = 0; i < ret.length; ++i)
            ret[i] /= input.size();
        return ret;
    }

    private void calibrateRotationVector(SensorEvent event) {
        if (mRotationRawList.size() > LineDataEvent.LIMIT){
            mRotationRawList.poll();
        }
        mRotationRawList.add(SensorData.toSensorData(event.timestamp, mSmoothedData, 0));
        float[] rotationMatrix = new float[9];
        if (mMagRawList.size() > 0) {
            SensorData magEvent = mMagRawList.get(mMagRawList.size() - 1);
            SensorManager.getRotationMatrixFromVector(rotationMatrix, mSmoothedData);
            float[] norData = getNormalCoordinate(rotationMatrix, magEvent.values);
            mMagRefinedList.add(SensorData.toSensorData(event.timestamp, norData, 0));
            mMagLinkedList.addData(norData);

            float[] filteredNorData = mMagLinkedList.getWeightedAverageFilteredData();
            mMagRefinedList.add(SensorData.toSensorData(event.timestamp, filteredNorData, 0));
        }

    }

    private float[] getNormalCoordinate(float[] rotationMatrix, float[] data) {
        float[] result = null;
        if (data.length == 3) {
            result = new float[3];
            result[0] = rotationMatrix[0] * data[0] + rotationMatrix[1] * data[1] + rotationMatrix[2] * data[2];
            result[1] = rotationMatrix[3] * data[0] + rotationMatrix[4] * data[1] + rotationMatrix[5] * data[2];
            result[2] = rotationMatrix[6] * data[0] + rotationMatrix[7] * data[1] + rotationMatrix[8] * data[2];
        } else if (data.length == 6) {
            result = new float[6];
            result[0] = rotationMatrix[0] * data[0] + rotationMatrix[1] * data[1] + rotationMatrix[2] * data[2];
            result[1] = rotationMatrix[3] * data[0] + rotationMatrix[4] * data[1] + rotationMatrix[5] * data[2];
            result[2] = rotationMatrix[6] * data[0] + rotationMatrix[7] * data[1] + rotationMatrix[8] * data[2];

            result[3] = rotationMatrix[0] * data[3] + rotationMatrix[1] * data[4] + rotationMatrix[2] * data[5];
            result[4] = rotationMatrix[3] * data[3] + rotationMatrix[4] * data[4] + rotationMatrix[5] * data[5];
            result[5] = rotationMatrix[6] * data[3] + rotationMatrix[7] * data[4] + rotationMatrix[8] * data[5];
        }

        return result;
    }

    private void calibrateGyro(SensorEvent event, long startTime) {
        long currentTime = event.timestamp;
        mPeriod += currentTime - startTime;
        startTime = currentTime;

        mMaxAngularSpeed = Math.max(event.values[0], Math.max(event.values[1], event.values[2]));

        if (mMaxAngularSpeed >= ANGULAR_SPEED_THRESHOLD || mPeriod < 16000) {
            initValues();
            return;
        }

        if (mPeriod > 1000000)
            mPeriod = 4000;

        /**
         * keep compass pointing to magnetic north in the first 0.5s
         * get the middle values of angular
         */
        //roll
        mDeltaAngular[0] = (mAngularSpeed[0] + event.values[0]) * (currentTime - startTime) / 2000;
        mAngularSpeed[0] = event.values[0];
        //pitch
        mDeltaAngular[1] = (mAngularSpeed[1] + event.values[1]) * (currentTime - startTime) / 2000;
        mAngularSpeed[1] = event.values[1];
        //yaw or azimuth
        mDeltaAngular[2] = (mAngularSpeed[2] + event.values[2]) * (currentTime - startTime) / 2000;
        mAngularSpeed[2] = event.values[2];

        float sin1 = (float) Math.sin(mDeltaAngular[0]);
        float cos1 = (float) Math.cos(mDeltaAngular[0]);
        float sin2 = (float) Math.sin(mDeltaAngular[1]);
        float cos2 = (float) Math.cos(mDeltaAngular[1]);
        float sin3 = (float) Math.sin(mDeltaAngular[2]);
        float cos3 = (float) Math.cos(mDeltaAngular[2]);

        //delta-Quaternion
        mDeltaQuater[0] = cos1 * cos2 * cos3 - sin1 * sin2 * sin3;
        mDeltaQuater[1] = cos1 * cos2 * sin3 + sin1 * sin2 * cos3;
        mDeltaQuater[2] = sin1 * cos2 * cos3 + cos1 * sin2 * sin3;
        mDeltaQuater[3] = cos1 * sin2 * cos3 - sin1 * cos2 * sin3;

        //new Quaternion values
        mNewQuaternion[0] = (mOldQuaternion[0] * mDeltaQuater[0] - mOldQuaternion[1] * mDeltaQuater[1] - mOldQuaternion[2] * mDeltaQuater[2] - mOldQuaternion[3] * mDeltaQuater[3]);
        mNewQuaternion[1] = (mOldQuaternion[0] * mDeltaQuater[1] + mOldQuaternion[1] * mDeltaQuater[0] + mOldQuaternion[2] * mDeltaQuater[3] - mOldQuaternion[3] * mDeltaQuater[2]);
        mNewQuaternion[2] = (mOldQuaternion[0] * mDeltaQuater[2] - mOldQuaternion[1] * mDeltaQuater[3] + mOldQuaternion[2] * mDeltaQuater[0] + mOldQuaternion[3] * mDeltaQuater[1]);
        mNewQuaternion[3] = (mOldQuaternion[0] * mDeltaQuater[3] + mOldQuaternion[1] * mDeltaQuater[2] - mOldQuaternion[2] * mDeltaQuater[1] + mOldQuaternion[3] * mDeltaQuater[0]);

        //low pass filter
        for (int i = 0; i < mOldQuaternion.length; i++) {
            mOldQuaternion[i] = LOW_PASS_FILTER * mOldQuaternion[i] + (1 - LOW_PASS_FILTER) * mNewQuaternion[i];
        }

        SensorManager.getRotationMatrixFromVector(mRMatrix, mOldQuaternion);

        //calculate degree changes
        //x-y plane direction rotate degree
        mRotateDegrees[0] = (float) Math.toDegrees(Math.atan2(mRMatrix[3], mRMatrix[4])) - mQuatDegrees[0];
        mQuatDegrees[0] += mRotateDegrees[0];
        //x-z plane direction rotate degree
        mRotateDegrees[1] = (float) Math.toDegrees(Math.atan2(mRMatrix[3], mRMatrix[5])) - mQuatDegrees[1];
        mQuatDegrees[1] += mRotateDegrees[1];
        //y-z plane direction rotate degree
        mRotateDegrees[2] = (float) Math.toDegrees(Math.atan2(mRMatrix[4], mRMatrix[5])) - mQuatDegrees[2];
        mQuatDegrees[2] += mRotateDegrees[2];

        mRotateDegreesTimestamp = event.timestamp;
    }

    public void initValues() {

        mDirectionStarted = false;
        mMaxAngularSpeed = 0;
        mIter = 0;

        for (int i = 0; i < mMagDegree.length; i++) {
            mReferenceDegree[i] = mQuatDegrees[i] = mMagDegree[i];
            mDiff[i] = mRotateDegrees[i] = 0;
            for (int j = 0; j < SLIDING_WINDOW_SIZE; j++) {
                mDiffArr[i][j] = 0;
            }
        }

        for (int i = 0; i < mOldQuaternion.length; i++) {
            mOldQuaternion[i] = mDeltaQuater[i] = mNewQuaternion[i] = 0;
        }

    }
}