package hk.ust.steve.signalcollector;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.telephony.NeighboringCellInfo;
import android.telephony.PhoneStateListener;
import android.telephony.SignalStrength;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import hk.ust.steve.signalcollector.fragment.AcceFragment;
import hk.ust.steve.signalcollector.fragment.BaroFragment;
import hk.ust.steve.signalcollector.fragment.CameraFragment;
import hk.ust.steve.signalcollector.fragment.CellularFragment;
import hk.ust.steve.signalcollector.fragment.CompassFragment;
import hk.ust.steve.signalcollector.fragment.GPSFragment;
import hk.ust.steve.signalcollector.fragment.GravityFragment;
import hk.ust.steve.signalcollector.fragment.GyroFragment;
import hk.ust.steve.signalcollector.fragment.MagneticFragment;
import hk.ust.steve.signalcollector.fragment.PedoFragment;
import hk.ust.steve.signalcollector.fragment.WiFiFragment;
import hk.ust.steve.signalcollector.map.MapActivity;

/**
 * Modified by Hong Joon on 06/10/2017. (added mSensorManager)
 */

public class MainActivity extends AppCompatActivity {

    protected Typeface mTfRegular;
    protected Typeface mTfLight;
    private Drawer mDrawer;
    private Toolbar mToolbar;
    private ViewPager mViewPager;
    private SmartTabLayout mViewPagerTab;
    private FragmentPagerItemAdapter mFragmentAdapter;
    private WiFiSignalManager mWifiManager;
    private SensorSignalManager mSensorManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initVars();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWifiManager.destroy();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mWifiManager.stopCollection();
    }

    private void initViews() {
        mTfRegular = Typeface.createFromAsset(getAssets(), "OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(), "OpenSans-Light.ttf");

        initToolbar();
        initDrawer();
        initViewPager();
    }

    private void initToolbar() {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.inflateMenu(R.menu.realtime);
        mToolbar.getMenu().getItem(0).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                startMapActivity();
                return true;
            }
        });
        mToolbar.getMenu().getItem(1).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mWifiManager.startCollection();
                return true;
            }
        });
        mToolbar.getMenu().getItem(2).setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                mWifiManager.stopCollection();
                return true;
            }
        });
    }

    private void initDrawer() {
        PrimaryDrawerItem item0 = new PrimaryDrawerItem().withIdentifier(0).withName(R.string.cellular);
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName(R.string.wifi);
        PrimaryDrawerItem item2 = new PrimaryDrawerItem().withIdentifier(2).withName(R.string.magnetic);
        PrimaryDrawerItem item3 = new PrimaryDrawerItem().withIdentifier(3).withName(R.string.accelerometer);
        PrimaryDrawerItem item4 = new PrimaryDrawerItem().withIdentifier(4).withName(R.string.gyroscope);
        PrimaryDrawerItem item5 = new PrimaryDrawerItem().withIdentifier(5).withName(R.string.gravity);
        PrimaryDrawerItem item6 = new PrimaryDrawerItem().withIdentifier(6).withName(R.string.compass);
        PrimaryDrawerItem item7 = new PrimaryDrawerItem().withIdentifier(7).withName(R.string.pedometer);
        PrimaryDrawerItem item8 = new PrimaryDrawerItem().withIdentifier(8).withName(R.string.barometer);
        PrimaryDrawerItem item9 = new PrimaryDrawerItem().withIdentifier(9).withName(R.string.gps);
        PrimaryDrawerItem item10 = new PrimaryDrawerItem().withIdentifier(10).withName(R.string.camera);

        mDrawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .addDrawerItems(item0, item1, item2, item3, item4, item5, item6, item7, item8, item9, item10)
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        mViewPager.setCurrentItem(position);
                        mDrawer.closeDrawer();
                        return true;
                    }
                })
                .build();
    }

    private void initVars() {
        mWifiManager = new WiFiSignalManager(this);
        mSensorManager = new SensorSignalManager(this, false);
    }

    private void initViewPager() {
        mFragmentAdapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add(R.string.cellular, CellularFragment.class)
                .add(R.string.wifi, WiFiFragment.class)
                .add(R.string.magnetic, MagneticFragment.class)
                .add(R.string.accelerometer, AcceFragment.class)
                .add(R.string.gyroscope, GyroFragment.class)
                .add(R.string.gravity, GravityFragment.class)
                .add(R.string.compass, CompassFragment.class)
                .add(R.string.pedometer, PedoFragment.class)
                .add(R.string.barometer, BaroFragment.class)
                .add(R.string.gps, GPSFragment.class)
                .add(R.string.camera, CameraFragment.class)
                .create());

        mViewPager = findViewById(R.id.viewpager);
        mViewPager.setAdapter(mFragmentAdapter);

        mViewPagerTab = findViewById(R.id.viewpagertab);
        mViewPagerTab.setViewPager(mViewPager);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.realtime, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.map:
                startMapActivity();
                return true;
            case R.id.start:
                startWiFiCollection();
                return true;
            case R.id.stop:
                stopWiFiCollection();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startMapActivity() {
        mWifiManager.stopCollection();
        Intent intent = new Intent(this, MapActivity.class);
        startActivity(intent);
    }

    private void startWiFiCollection() {
        mWifiManager.startCollection();
    }

    private void stopWiFiCollection() {
        mWifiManager.stopCollection();
    }

}
