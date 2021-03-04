package hk.ust.steve.signalcollector.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;

import java.util.Date;
import java.util.Random;

/**
 * Created by Steve on 27/9/2017.
 */

public class GeneralUtils {

    private static int screenW = -1, screenH = -1;

    public static int getScreenW(Context context) {
        if (screenW < 0) {
            initScreenDisplayParams(context);
        }
        return screenW;
    }

    public static int getScreenH(Context context) {
        if (screenH < 0) {
            initScreenDisplayParams(context);
        }
        return screenH;
    }

    private static void initScreenDisplayParams(Context context) {
        DisplayMetrics dm = context.getApplicationContext().getResources().getDisplayMetrics();
        screenW = dm.widthPixels;
        screenH = dm.heightPixels;
    }

    public static String getCurrentTimeString() {
        return DateFormat.format("yyyy_MM_dd_HH_mm_ss", new Date()).toString();
    }

}
