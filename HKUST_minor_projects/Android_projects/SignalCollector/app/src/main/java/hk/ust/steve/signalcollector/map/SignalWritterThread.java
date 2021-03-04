package hk.ust.steve.signalcollector.map;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import hk.ust.steve.signalcollector.utils.FileUtils;
import hk.ust.steve.signalcollector.utils.GeneralUtils;

/**
 * Created by Steve on 10/10/2017.
 */

public class SignalWritterThread extends Thread {

    private static final SignalWritterThread mInstance = new SignalWritterThread();
    private static final String SHUTDOWN_REQ = "SHUTDOWN";

    private BlockingQueue<String> mEventStrQueue = new ArrayBlockingQueue<>(5000);
    private volatile boolean mShutdown, mWritterTerminated, mStarted;

    public static SignalWritterThread getWritter() {
        return mInstance;
    }
    private SignalWritterThread() {
        start();
    }

    @Override
    public void run() {
        try {
            String line;
            while (!(line = mEventStrQueue.take()).equals(SHUTDOWN_REQ)) {
                if (mStarted) {
                    if (FileUtils.getCollectionFilePath().isEmpty()) {
                        FileUtils.setSensorFilePath("0", "0", "0", "0");
                    }
                    try {
                        FileUtils.writeLine(line, FileUtils.getCollectionFilePath());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (InterruptedException iex) {
        } finally {
            mWritterTerminated = true;
        }
    }

    public void addSignalStr(String str) {
        if (mShutdown || mWritterTerminated) return;
        try {
            mEventStrQueue.put(str);
        } catch (InterruptedException iex) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("Unexpected interruption");
        }
    }

    public void shutDown() throws InterruptedException {
        mShutdown = true;
        mEventStrQueue.put(SHUTDOWN_REQ);
    }

    public void startWriting() {
        mStarted = true;
        addSignalStr("Started:" + GeneralUtils.getCurrentTimeString());
    }

    public void finishWriting() {
        mStarted = false;
    }
}
