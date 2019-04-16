package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.content.pm.ActivityInfo;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.VideoListFragment_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.TruckVideoListData;
import com.coahr.fanoftruck.mvp.presenter.VideoListFragment_P;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.VideoListAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JZMediaManager;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdMgr;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 11:31
 */
public class Fragment_home_videoList extends BaseFragment<VideoListFragment_C.Presenter> implements VideoListFragment_C.View {
    @Inject
    VideoListFragment_P p;

    @BindView(R.id.mytittle)
    MyTittleBar myTittleBar;
//    @BindView(R.id.video_list_swipe)
//    SwipeRefreshLayout video_list_swipe;
    @BindView(R.id.video_list_recycler)
    RecyclerView video_list_recycler;
    private List<TruckVideoListData.JdataBean> videoList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private VideoListAdapter adapter;

    public static Fragment_home_videoList newInstance() {
        return new Fragment_home_videoList();
    }

    @Override
    public VideoListFragment_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_videolist;
    }

    @Override
    public void initView() {
        myTittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
//        video_list_swipe.setRefreshing(false);
        adapter = new VideoListAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        video_list_recycler.setAdapter(adapter);
        video_list_recycler.setLayoutManager(linearLayoutManager);
        video_list_recycler.addItemDecoration(new SpacesItemDecoration(0,DensityUtils.dp2px(BaseApplication.mContext,4),getResources().getColor(R.color.colorWhite)));
        video_list_recycler.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() {
            @Override
            public void onChildViewAttachedToWindow(View view) {

            }

            @Override
            public void onChildViewDetachedFromWindow(View view) {
                Jzvd jzvd = view.findViewById(R.id.video_list);
                if (jzvd != null && jzvd.jzDataSource.containsTheUrl(JZMediaManager.getCurrentUrl())) {
                    Jzvd currentJzvd = JzvdMgr.getCurrentJzvd();
                    if (currentJzvd != null && currentJzvd.currentScreen != Jzvd.SCREEN_WINDOW_FULLSCREEN) {
                        Jzvd.releaseAllVideos();
                    }
                }
            }
        });

        p.getTruckVideoList();
    }


    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
    }


    @Override
    public boolean onBackPressedSupport() {
        if (Jzvd.backPress()) {
            return true;
        }
        return super.onBackPressedSupport();
    }

    @Override
    public void getVideoListSuccess(TruckVideoListData truckVideoListData) {
        KLog.e("lizhiguo", "truckVideoListData == " + truckVideoListData.toString());
        videoList.clear();
        if (truckVideoListData != null){
            List<TruckVideoListData.JdataBean> jdata = truckVideoListData.getJdata();
            if (jdata != null && !jdata.isEmpty()){
                for (TruckVideoListData.JdataBean jdataBean : jdata) {
                    videoList.add(jdataBean);
                }
                adapter.setNewData(videoList);
            }
        }
    }

    @Override
    public void getVideoListFailure(String failure) {
        ToastUtils.showShort(_mActivity, failure);
    }

    @Override
    public void getVideoMoreSuccess(TruckVideoListData truckVideoListData) {

    }

    @Override
    public void getVideoMoreFailure(String failure) {

    }
}
