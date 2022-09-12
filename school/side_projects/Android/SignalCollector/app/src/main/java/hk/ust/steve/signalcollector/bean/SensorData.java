package hk.ust.steve.signalcollector.bean;

import android.hardware.SensorEvent;

public class SensorData {
	public float[] values;
	public long timeStamp;
	public int step;
	
	public SensorData(long timeStamp, float values[], int step) {
		this.timeStamp = timeStamp;
		this.step = step;
		this.values = new float[values.length];
		for ( int i = 0; i < values.length; i++ ) {
			this.values[i] = values[i];
		}
	}
	
	public static SensorData toSensorData(SensorEvent event, int step) {
		return new SensorData(event.timestamp, event.values, step);
	}
	
	public static SensorData toSensorData(long timeStamp, float[] values, int step) {
		return new SensorData(timeStamp, values, step);
	}
	
	public static SensorData toSensorData(SensorEvent event) {
		return new SensorData(event.timestamp, event.values, -1);
	}
	
	public static SensorData toSensorData(long timeStamp, float[] values) {
		return new SensorData(timeStamp, values, -1);
	}
	
	public String toString() {
		StringBuffer log = new StringBuffer();
		for (int i = 0; i < values.length; i++ ) {
			log.append(values[i] + "|");
		}
		log.append(timeStamp+"|"+step);
		return log.toString();
	}
	public String myToString() {
		String ret="";
		for (int i=0;i<values.length;++i)
			ret+=(""+values[i]+" ");
		ret=ret+timeStamp+" "+step;
		return ret;
	}
}
