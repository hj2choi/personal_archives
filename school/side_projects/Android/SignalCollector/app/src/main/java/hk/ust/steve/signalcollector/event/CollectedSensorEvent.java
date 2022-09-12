package hk.ust.steve.signalcollector.event;

import java.io.Serializable;

/**
 * Created by Steve on 10/10/2017.
 */

public class CollectedSensorEvent implements Serializable{

    public int type;
    public int accuracy;
    public float[] values;
    public long timestamp;
}
