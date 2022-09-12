package hk.ust.steve.signalcollector.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import org.greenrobot.eventbus.EventBus;

public abstract class BaseFragment extends Fragment {

    protected Context mContext = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mContext = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isEventBusBound()) {
            EventBus.getDefault().register(this); //注册事件
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View layout = inflater.inflate(obtainLayoutID(), container, false);
        return layout;
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onViewCreated(view);
        initDatas(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (isEventBusBound()) {
            EventBus.getDefault().unregister(this);  //取消事件注册
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 是否绑定EventBus
     */
    protected abstract boolean isEventBusBound();

    /**
     * 返回onCreateView中需要的layoutID
     */
    protected abstract int obtainLayoutID();

    /**
     * 初始化控件
     */
    protected abstract void onViewCreated(View view);

    /**
     * 加载数据
     */
    protected abstract void initDatas(Bundle savedInstanceState);


}
