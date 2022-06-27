package hk.ust.steve.signalcollector;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.util.Log;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import hk.ust.steve.signalcollector.bean.ApSignal;
import hk.ust.steve.signalcollector.bean.Position;
import hk.ust.steve.signalcollector.bean.WiFiVector;
import hk.ust.steve.signalcollector.event.WiFiSignalCollectedEvent;
import hk.ust.steve.signalcollector.utils.FileUtils;
import hk.ust.steve.signalcollector.utils.GeneralUtils;
import hk.ust.steve.signalcollector.utils.WiFiUtils;

/**
 * Created by Steve on 18/9/2017.
 */

public class WiFiSignalManager {

    private Context mContext;
    private WifiManager mWiFiManager;
    private WiFiBroadcastReceiver mReceiver;
    private WiFiScanThread mWiFiScanThread;
    private List<WiFiVector> mWiFiVectors;
    private List<Integer> mBreakPoints;

    public WiFiSignalManager(Context context) {
        mContext = context;
        mWiFiManager = (WifiManager) mContext.getApplicationContext().getSystemService(Context.WIFI_SERVICE);

        mWiFiVectors = new ArrayList<>();
        mBreakPoints = new ArrayList<>();
        mReceiver = new WiFiBroadcastReceiver();
        mWiFiScanThread = new WiFiScanThread();
        mWiFiScanThread.start();
    }

    public void startCollection() {
        mWiFiVectors.clear();
        mBreakPoints.clear();
        mBreakPoints.add(0); // Starting index
        mContext.registerReceiver(mReceiver,
                new IntentFilter(WifiManager.SCAN_RESULTS_AVAILABLE_ACTION));
    }

    public void stopCollection() {
        try {
            mContext.unregisterReceiver(mReceiver);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }

    public void nextPointReached() {
        mBreakPoints.add(mWiFiVectors.size());
    }

    public void undoPointReached() {
        if (mBreakPoints.size() > 0) {
            mBreakPoints.remove(mBreakPoints.size() - 1);
        }
    }

    public void writeToFile(String filePath, List<Position> posList) {
        List<String> path = new ArrayList<>();
        path.add("Started:" + GeneralUtils.getCurrentTimeString());
        // Store the coordinates of anchor points.
        String posStr = "";
        for (Position pos : posList) {
            posStr += pos.getX() + "," + pos.getY() + " ";
        }
        path.add(posStr);

        // Store the sample index when people reach anchor points.
        String breakPoints = "";
        for (Integer index : mBreakPoints) {
            breakPoints += index + " ";
        }
        path.add(breakPoints);

        try {
            FileUtils.writeLines(path, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<String> signalList = new ArrayList<>();
        for (WiFiVector wifi: mWiFiVectors) {
            String signalStr = "";
            for (ApSignal signal: wifi.apSignalList) {
                signalStr += signal.getApAddress() + "," + signal.getRssi() + "," + signal.getMicroTimestamp() + " ";
            }
            signalList.add(signalStr);
        }

        try {
            FileUtils.writeLines(signalList, filePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void destroy() {
        mContext.unregisterReceiver(mReceiver);
        mWiFiScanThread.interrupt();
        mWiFiScanThread = null;
    }

    private class WiFiBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context c, Intent intent) {
            WiFiVector wiFiVector = new WiFiVector();
            for (ScanResult result : mWiFiManager.getScanResults()) {
                String wifiApAdd = WiFiUtils.uniformApAddress(result.BSSID);
                wiFiVector.addApSignal(new ApSignal(wifiApAdd, result.SSID, result.level, result.timestamp));
            }
            EventBus.getDefault().post(new WiFiSignalCollectedEvent(wiFiVector.apSignalList));
            mWiFiVectors.add(wiFiVector);
        }
    }

    private class WiFiScanThread extends Thread {
        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                mWiFiManager.startScan();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
