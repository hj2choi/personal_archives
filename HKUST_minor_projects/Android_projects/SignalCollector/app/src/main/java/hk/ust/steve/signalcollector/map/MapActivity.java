package hk.ust.steve.signalcollector.map;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;

import hk.ust.steve.signalcollector.R;
import hk.ust.steve.signalcollector.SensorSignalManager;
import hk.ust.steve.signalcollector.WiFiSignalManager;
import hk.ust.steve.signalcollector.bean.Position;
import hk.ust.steve.signalcollector.event.CollectedSensorEvent;
import hk.ust.steve.signalcollector.utils.FileUtils;
import hk.ust.steve.signalcollector.utils.MapUtils;

/**
 * Created by Steve on 27/9/2017.
 */

public class MapActivity extends Activity implements View.OnClickListener{

    private final int paintColor = Color.BLUE;
    private final int walkedColor = Color.RED;
    private MapLayout mMapLayout;
    private RelativeLayout mPathDesignRL;
    private RelativeLayout mPathWalkingRL;
    private Button mStartPathDesignBtn;
    private Button mEndPathDesignBtn;
    private Button mNextPtBtn;
    private Button mStartWalkingBtn;
    private Button mNextPtReachedBtn;
    private Button mUndoReachedBtn;
    private Button mReversePathBtn;
    private List<Position> mPosList;
    private FloatingActionsMenu mActionMenu;
    private FloatingActionButton mActionUndo;
    private FloatingActionButton mActionRestart;

    private WiFiSignalManager mWiFiManger;
    private int mCurrentIndex;
    private boolean mEnded;
    private Gson mGson;
    private SensorSignalManager mSensorManager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initVars();
        initViews();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        try {
            SignalWritterThread.getWritter().shutDown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private void initVars() {
        mPosList = new ArrayList<>();
        mEnded = false;
        mWiFiManger = new WiFiSignalManager(this);
        mCurrentIndex = 0;
        mGson = new Gson();
        mSensorManager = new SensorSignalManager(this, true);
    }

    private void initViews() {
        mPathDesignRL = findViewById(R.id.rl_path_design);
        mPathWalkingRL = findViewById(R.id.rl_path_walking);

        mMapLayout = findViewById(R.id.maplayout);

        mStartPathDesignBtn = findViewById(R.id.btn_start_design_path);
        mEndPathDesignBtn = findViewById(R.id.btn_end_design_path);
        mNextPtBtn = findViewById(R.id.btn_next_point);
        mStartWalkingBtn = findViewById(R.id.btn_start_walking);
        mNextPtReachedBtn = findViewById(R.id.btn_next_point_reached);
        mUndoReachedBtn = findViewById(R.id.btn_undo_reached);
        mReversePathBtn = findViewById(R.id.btn_reverse_path);

        mActionMenu = findViewById(R.id.menu_action);
        mActionUndo = findViewById(R.id.action_undo);
        mActionRestart = findViewById(R.id.action_restart);
        mActionRestart.setIcon(R.drawable.restart);
        mActionUndo.setIcon(R.drawable.undo);

        mStartPathDesignBtn.setOnClickListener(this);
        mEndPathDesignBtn.setOnClickListener(this);
        mNextPtBtn.setOnClickListener(this);
        mStartWalkingBtn.setOnClickListener(this);
        mNextPtReachedBtn.setOnClickListener(this);
        mUndoReachedBtn.setOnClickListener(this);
        mReversePathBtn.setOnClickListener(this);
        mActionRestart.setOnClickListener(this);
        mActionUndo.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start_design_path:
                startDesignPath();
                break;
            case R.id.btn_end_design_path:
                endDesignPath();
                break;
            case R.id.btn_next_point:
                designNextPoint();
                break;
            case R.id.btn_start_walking:
                startWalking();
                break;
            case R.id.btn_next_point_reached:
                nextPointReached();
                break;
            case R.id.btn_undo_reached:
                undoPointReached();
                break;
            case R.id.btn_reverse_path:
                reversePath();
                break;
            case R.id.action_undo:
                undo();
                break;
            case R.id.action_restart:
                restartDesign();
                break;
        }
    }

    private void startWalking() {
        mStartWalkingBtn.setVisibility(View.GONE);
        mNextPtReachedBtn.setVisibility(View.VISIBLE);
        mUndoReachedBtn.setVisibility(View.VISIBLE);
        mMapLayout.getCurrentMapController().display();
        List<PointF> ptList = mMapLayout.getCurrentMapController().getMapView().getFixedPoints();
        String start = (int)ptList.get(0).x + "_" + (int)ptList.get(0).y;
        String end = (int)ptList.get(1).x + "_" + (int)ptList.get(1).y;
        FileUtils.setSensorFilePath(MapUtils.getCurSite().getSiteName(), MapUtils.getCurSite().getCurAreaId(), start, end);
        mWiFiManger.startCollection();
        SignalWritterThread.getWritter().startWriting();
        Toast.makeText(this, "Signal collection started.", Toast.LENGTH_SHORT).show();
    }

    private void nextPointReached() {
        mWiFiManger.nextPointReached();
        mCurrentIndex++;
        mMapLayout.getCurrentMapController().getMapView().changePointColor(mCurrentIndex, Color.RED);
        mMapLayout.getCurrentMapController().getMapView().changePathColor(mCurrentIndex - 1, Color.RED);
        mMapLayout.getCurrentMapController().display();
        if (mCurrentIndex == mPosList.size()-1) {
            finishWalking();
        }
    }

    private void undoPointReached() {
        if (mCurrentIndex <= 0) return;
        mWiFiManger.undoPointReached();
        mMapLayout.getCurrentMapController().getMapView().changePointColor(mCurrentIndex, Color.BLUE);
        mMapLayout.getCurrentMapController().getMapView().changePathColor(mCurrentIndex - 1, Color.BLUE);
        mCurrentIndex--;
        mMapLayout.getCurrentMapController().display();
    }

    private void reversePath() {
        if (mPosList.size() > 0) {
            for (int i=mPosList.size()-1; i >= 0; i--) {
                mPosList.add(mPosList.get(i));
            }
            mPosList.subList(0, mPosList.size()/2).clear();
            endDesignPath();
        }
    }

    private void finishWalking() {
        mPathWalkingRL.setVisibility(View.GONE);
        mStartWalkingBtn.setVisibility(View.VISIBLE);
        mNextPtReachedBtn.setVisibility(View.GONE);
        mUndoReachedBtn.setVisibility(View.GONE);
        mWiFiManger.stopCollection();
        List<PointF> ptList = mMapLayout.getCurrentMapController().getMapView().getFixedPoints();
        String start = (int)ptList.get(0).x + "_" + (int)ptList.get(0).y;
        String end = (int)ptList.get(ptList.size()-1).x + "_" + (int)ptList.get(ptList.size()-1).y;
        String filePath = FileUtils.getWiFiFilePath(MapUtils.getCurSite().getSiteName(), MapUtils.getCurSite().getCurAreaId(), start, end);
        mWiFiManger.writeToFile(filePath, mPosList);
        mPathDesignRL.setVisibility(View.VISIBLE);
        mStartPathDesignBtn.setVisibility(View.VISIBLE);
        mReversePathBtn.setVisibility(View.VISIBLE);
        SignalWritterThread.getWritter().finishWriting();
        mCurrentIndex = 0;
    }

    private void undo() {
        if (mPosList.size() >= 1 && !mEnded) {
            mPosList.remove(mPosList.size()-1);
            mMapLayout.getCurrentMapController().removeLastPoint();
            mMapLayout.getCurrentMapController().display();
        }
    }

    private void restartDesign() {
        mPosList.clear();
        mPathWalkingRL.setVisibility(View.GONE);
        mPathDesignRL.setVisibility(View.VISIBLE);
        startDesignPath();
    }

    private void startDesignPath() {
        mEnded = false;
        mPosList.clear();
        mMapLayout.getCurrentMapController().clearAllStuff();
        mMapLayout.getCurrentMapController().setCenterMarkDrawable();
        mMapLayout.getCurrentMapController().showMarkLocation(true);
        mMapLayout.getCurrentMapController().display();
        mStartPathDesignBtn.setVisibility(View.GONE);
        mEndPathDesignBtn.setVisibility(View.VISIBLE);
        mNextPtBtn.setVisibility(View.VISIBLE);
        mReversePathBtn.setVisibility(View.GONE);
    }

    private void endDesignPath() {
        if (mPosList.size() <= 1) {
            Toast.makeText(MapActivity.this, "Less than 2 points, please get more.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            try {
                mMapLayout.getCurrentMapController().showMarkLocation(false);
                mMapLayout.getCurrentMapController().clearAllStuff();

                for (int i = 0; i < mPosList.size() - 1; i++) {
                    Position beginPosition = mPosList.get(i);
                    Position endPosition = mPosList.get(i + 1);
                    mMapLayout.getCurrentMapController().addLine(beginPosition, endPosition, paintColor);
                }
                mMapLayout.getCurrentMapController().addFixedPoints(mPosList, paintColor);
                mMapLayout.getCurrentMapController().getMapView().changePointColor(mCurrentIndex, Color.RED);
                mMapLayout.getCurrentMapController().display();

            } catch (Exception e) {
                Toast.makeText(MapActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        mEnded = true;
        mEndPathDesignBtn.setVisibility(View.GONE);
        mNextPtBtn.setVisibility(View.GONE);
        mPathWalkingRL.setVisibility(View.VISIBLE);
        mPathDesignRL.setVisibility(View.GONE);
    }

    private void designNextPoint() {
        Position position = mMapLayout.getCurrentMapController().getMarkLocation();
        mPosList.add(position);
        mMapLayout.getCurrentMapController().addFixedPoint(position, paintColor);
        if (mPosList.size() >= 2) {
            Position lastPosition = mPosList.get(mPosList.size() - 2);
            mMapLayout.getCurrentMapController().addLine(position, lastPosition, paintColor);
        }
        mMapLayout.getCurrentMapController().display();
    }

    @Subscribe
    public void collectedSignalEvent(CollectedSensorEvent event) {
        String eventStr = mGson.toJson(event, CollectedSensorEvent.class);
        SignalWritterThread.getWritter().addSignalStr(eventStr);
    }

}
