package hk.ust.steve.signalcollector.utils;

import android.content.Context;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;

import java.util.Date;
import java.util.Random;

public class WiFiUtils {
    public static String uniformApAddress(String apAddress) {
        return apAddress.replace("\"", "").replace(":", "").toLowerCase();
    }

    public static double signalDBM2Watt(double dbm) {
        // return watt * 10000, making it not so small
        return Math.pow(2, dbm / 10.0) * 10000;
    }

    public static boolean isVirtualMac(String hexMacAddrStr) {
        String hexMacAddr = hexMacAddrStr.replace(":", "").replace("\"", "");
        if (hexMacAddr.length() % 2 != 0)
            hexMacAddr = "0" + hexMacAddr;
        byte[] macAddr = hexStringToBytes(hexMacAddr);
        return (macAddr[0] & 0x02) != 0;
    }

    private static byte[] hexStringToBytes(String hexString) {
        if (hexString == null || hexString.equals("")) {
            return null;
        }
        hexString = hexString.toUpperCase();
        int length = hexString.length() / 2;
        char[] hexChars = hexString.toCharArray();
        byte[] d = new byte[length];
        for (int i = 0; i < length; i++) {
            int pos = i * 2;
            d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
        }
        return d;
    }

    private static byte charToByte(char c) {
        return (byte) "0123456789ABCDEF".indexOf(c);
    }

    public static String getMacAddress(Context context) {
        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        return info.getMacAddress();
    }

    private static String currentLocalMacAddress = null;
    public static String getPseudoMacAddress() {
        if (currentLocalMacAddress == null) {
            String allChars = "1234567890ABCDEF";
            String answer = "";
            Date date = new Date();
            Random random = new Random(date.getTime());
            for (int i = 0; i < 6; i++) {
                int index;
                index = random.nextInt(allChars.length());
                answer += allChars.charAt(index);
                index = random.nextInt(allChars.length());
                answer += allChars.charAt(index);
                if (i != 5) answer += ":";
            }
            currentLocalMacAddress = answer;
        }
        return currentLocalMacAddress;
    }
}
