package hk.ust.steve.signalcollector.bean;

import java.util.ArrayList;
import java.util.List;

public class WiFiVector {
    public String floorId;
    public Position pos;
    public List<ApSignal> apSignalList = new ArrayList<>();

    public void addApSignal(ApSignal apSignal) {
        apSignalList.add(apSignal);
    }

    public boolean isEmpty() {
        return apSignalList.isEmpty();
    }
}
