package hk.ust.steve.signalcollector.event;

import java.util.List;

import hk.ust.steve.signalcollector.bean.ApSignal;
import hk.ust.steve.signalcollector.bean.WiFiVector;

/**
 * Created by Steve on 18/9/2017.
 */

public class WiFiSignalCollectedEvent {

    private List<ApSignal> mApSignalList;

    public WiFiSignalCollectedEvent(List<ApSignal> signalList) {
        mApSignalList = signalList;
    }

    public List<ApSignal> getApSignalList() {
        return mApSignalList;
    }
}
