package hk.ust.steve.signalcollector.base;

import android.app.Application;

import hk.ust.steve.signalcollector.utils.FileUtils;

/**
 * Created by Steve on 18/9/2017.
 */

public class BaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        FileUtils.makeStartupDirs();
    }
}
