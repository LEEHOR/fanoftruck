package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_child_P;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Fragment_maintenance_child_adapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 15:21
 * 维修视频列表
 */
public class Fragment_maintenance_child extends BaseChildFragment<Fragment_maintenance_child_C.Presenter> implements Fragment_maintenance_child_C.View{
    @Inject
    Fragment_maintenance_child_P p;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    private Fragment_maintenance_child_adapter adapter;
    private int start = 0;
    private int length = 9;
    private int status;
    private int max;
    private boolean isLoading;
    private List<MaintenanceVideoList.JdataBean> maintenanceVideoListJdata = new ArrayList<>();
    private GridLayoutManager gridLayoutManager;
    private int lastVisibleItemPosition;

    public static Fragment_maintenance_child newInstance(int status){
        Fragment_maintenance_child child=new Fragment_maintenance_child();
        Bundle bundle=new Bundle();
        bundle.putInt("status",status);
        child.setArguments(bundle);
        return child;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintance_child;
    }

    @Override
    public void initView() {

        adapter = new Fragment_maintenance_child_adapter();
        gridLayoutManager = new GridLayoutManager(BaseApplication.mContext, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpacesItemDecoration(DensityUtils.dp2px(BaseApplication.mContext, 8), DensityUtils.dp2px(BaseApplication.mContext, 5), getResources().getColor(R.color.material_grey_200)));
        for (int i = 0; i <recyclerView.getItemDecorationCount() ; i++) {
            if (i !=0){
                recyclerView.removeItemDecorationAt(i);
            }
        }
        recyclerView.setAdapter(adapter);
        adapter.setVideoOnClick(new Fragment_maintenance_child_adapter.VideoOnClick() {
            @Override
            public void OnClick(int position) {
                KLog.d("跳转到播放页面");
                ((SupportFragment) getParentFragment()).start(Fragment_maintenance_videoPlay.newInstance(position,status));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    start = 0;
                    length=9;
                    getList(status);
                } else {
                    swipeRefreshLayout.setRefreshing(false);
                }

            }
        });
    }
    /**
     * 找到数组中最大的数
     *
     * @param lastPositions
     * @return
     */
    private int findMax(int[] lastPositions) {
        int max = lastPositions[0];
        for (int value : lastPositions) {
            if (value > max) {
                max = value;
            }
        }
        return max;
    }
    @Override
    public void initData() {
        status = getArguments().getInt("status");
        start=0;
        length=9;
        getList(status);
        addScrollingListener();
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList) {
        maintenanceVideoListJdata.clear();
        maintenanceVideoListJdata.addAll(maintenanceVideoList.getJdata());
        adapter.setNewData(maintenanceVideoListJdata);
        isLoading = false;
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void getVideoListFailure(String failure) {
        isLoading = false;
        swipeRefreshLayout.setRefreshing(false);
        ToastUtils.showLong(failure);
    }

    @Override
    public void getVideoMoreSuccess(MaintenanceVideoList maintenanceVideoList) {
        KLog.d("加载更多c"+maintenanceVideoList.getJdata().size());
        if (maintenanceVideoList.getJdata()!= null && maintenanceVideoList.getJdata().size() == 0) {
            start-=10;
            length-=10;
            Toast.makeText(_mActivity, "没有更多视频了", Toast.LENGTH_LONG).show();
        } else {
            maintenanceVideoListJdata.addAll(maintenanceVideoList.getJdata());
            adapter.setNewData(maintenanceVideoListJdata);
        }
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
        isLoading = false;
    }

    @Override
    public void getVideoMoreFailure(String failure) {
        KLog.d("加载更多"+failure);
        start-=10;
        length-=10;
        if (adapter.getFooterLayoutCount() > 0) {
            adapter.removeAllFooterView();
        }
        ToastUtils.showLong(failure);
        isLoading = false;
    }

    @Override
    public void getVideoSearchSuccess(MaintenanceVideoList maintenanceVideoList) {

    }

    @Override
    public void getVideoSearchFailure(String failure) {

    }

    private void getList(int status_s) {
        start=0;
        length=9;
        Map map = new HashMap();
        map.put("video_name", "");
        if (status_s ==0){
            map.put("video_type", "");
        } else {
            map.put("video_type",String.valueOf(status_s));
        }
        map.put("start", String.valueOf(start));
        map.put("length", String.valueOf(length));
        p.getVideoList(map);
    }
    private void getMoreList(int  status_s) {
        Map map = new HashMap();
        map.put("video_name", "");
        if (status_s ==0){
            map.put("video_type", "");
        } else {
            map.put("video_type",String.valueOf(status_s));
        }
        map.put("start", String.valueOf(start));
        map.put("length", String.valueOf(length));
        p.getVideoMore(map);
    }
    @Override
    public void showError(@Nullable Throwable e) {
        super.showError(e);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void addScrollingListener(){
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (adapter.getFooterLayoutCount() == 0 && adapter.getData().size() > 10) {
                    adapter.addFooterView(child_footView);
                }
                GridLayoutManager layoutManager = (GridLayoutManager) recyclerView.getLayoutManager();
                //屏幕中最后一个可见子项的position
                // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                //当前屏幕所看到的子项个数
                int visibleItemCount = layoutManager.getChildCount();
                //当前RecyclerView的所有子项个数
                int totalItemCount = layoutManager.getItemCount();
                //RecyclerView的滑动状态
                int state = recyclerView.getScrollState();
                if (visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == RecyclerView.SCROLL_STATE_IDLE &&maintenanceVideoListJdata.size()>=2 ) {
                    if (!isLoading) {
                        recyclerView.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                isLoading = true;
                                start+=10;
                                length+=10;
                                getMoreList(status);
                            }
                        }, 500);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
             //   int[] lastVisibleItemPositions = staggeredGridLayoutManager.findLastVisibleItemPositions(new int[staggeredGridLayoutManager.getSpanCount()]);
               // max = findMax(lastVisibleItemPositions);
                lastVisibleItemPosition = gridLayoutManager.findLastVisibleItemPosition();
            }
        });
    }
}
