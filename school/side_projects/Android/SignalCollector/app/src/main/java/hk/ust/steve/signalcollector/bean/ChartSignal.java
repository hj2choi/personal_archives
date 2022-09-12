package hk.ust.steve.signalcollector.bean;

/**
 * Created by Steve on 19/9/2017.
 * Modified by Hong Joon on 06/10/2017. (added extra fields)
 */

public class ChartSignal {

    public String label;
    public float strength;
    public long microTimeStamp;

    // TODO: Kept for compatibility for now. Remove this function if possible
    public ChartSignal(float strength) {
        this.label = "";
        this.strength = strength;
        this.microTimeStamp = 0;
    }

    /*public ChartSignal(String label, float strength, long microTimeStamp){
        this.label = label;
        this.strength = strength;
        this.microTimeStamp = microTimeStamp;
    }*/
}
