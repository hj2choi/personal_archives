package hk.ust.steve.signalcollector.adapter;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.bean.ApSignal;

/**
 * Created by Steve on 26/9/2017.
 */
public class WiFiFragmentAdapter extends BaseQuickAdapter<ApSignal, BaseViewHolder> {
    public WiFiFragmentAdapter(int layoutResId, List data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ApSignal item) {
        helper.setText(R.id.tv_ap_name, item.getApName());
        helper.setText(R.id.tv_mac, item.getApAddress());
        helper.setText(R.id.tv_signal_strength, item.getRssi() + "dbm");
    }
}
