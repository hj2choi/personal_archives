package hk.ust.steve.signalcollector.bean;

public class ApSignal {
    private double rssi;
    private String apAddress;
    private String apName;
    private long microTimestamp;

    public ApSignal(String apAddress, String apName, double rssi, long microTimestamp) {
        this.apAddress = apAddress;
        this.apName = apName;
        this.rssi = rssi;
        this.microTimestamp = microTimestamp;
    }

    public String getApAddress() {
        return apAddress;
    }

    public String getApName () {return apName;}

    public double getRssi() {
        return rssi;
    }

    public long getMicroTimestamp() {
        return microTimestamp;
    }

    public void setRssi(double rssi) {
        this.rssi = rssi;
    }
}
