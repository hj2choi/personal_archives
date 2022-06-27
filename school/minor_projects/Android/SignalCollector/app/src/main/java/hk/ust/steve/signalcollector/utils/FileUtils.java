package hk.ust.steve.signalcollector.utils;

import android.os.Environment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.List;

/**
 * Created by Steve on 18/9/2017.
 */

public class FileUtils {

    private static volatile String mCollectionFilePath = "";

    /**
     * Obtain the file path for storing signals.
     * @param site site name, e.g., HKUST or HKIA
     * @param floor WiFi or Sensor
     * @param start Signal or Coor
     * @return
     */
    public static String getWiFiFilePath(String site, String floor, String start, String end) {
        return Environment.getExternalStorageDirectory() + "/SCollector/"
                + site + "_" + floor + "_" + start + "_" + end +"_WiFi.txt";
    }

    public static void setSensorFilePath(String site, String floor, String start, String end) {
        mCollectionFilePath = Environment.getExternalStorageDirectory() + "/SCollector/"
                + site + "_" + floor + "_" + start + "_" + end + "_Sensors.txt";
    }

    public static String getCollectionFilePath() {
        return mCollectionFilePath;
    }

    public static void makeStartupDirs() {
        String fileDir = Environment.getExternalStorageDirectory() + "/SCollector";
        File file = new File(fileDir);
        if (!file.exists() || !file.isDirectory()) {
            int count = 0;
            while(!file.mkdir()){
                if (count++ > 3)
                    return;
            }
        }
    }

    public static void writeLines(List<String> lines, String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        for (String line : lines) {
            bufferedWriter.write(line + "\n");
            bufferedWriter.flush();
        }
        bufferedWriter.close();
    }

    public static void writeLine(String line, String filePath) throws Exception {
        File file = new File(filePath);
        if (!file.exists())
            file.createNewFile();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
        bufferedWriter.write(line + "\n");
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}
