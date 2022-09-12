package hk.ust.steve.signalcollector.fragment;

import android.os.Bundle;
import android.view.View;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.base.BaseFragment;

/**
 * Created by Steve on 26/9/2017.
 */

public class BaroFragment extends BaseFragment {

    public static BaroFragment newInstance() {
        BaroFragment fragment = new BaroFragment();
        return fragment;
    }

    @Override
    protected boolean isEventBusBound() {
        return false;
    }

    @Override
    protected int obtainLayoutID() {
        return R.layout.fragment_barometer;
    }

    @Override
    protected void onViewCreated(View view) {

    }

    @Override
    protected void initDatas(Bundle savedInstanceState) {

    }
}
