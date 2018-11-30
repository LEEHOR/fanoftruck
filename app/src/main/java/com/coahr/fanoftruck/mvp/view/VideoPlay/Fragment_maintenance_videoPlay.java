package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_videoPlay_P;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Video_play_adapter;
import com.coahr.fanoftruck.widgets.AltDialog.Dialog_share;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlay_Normal;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlayer_douyin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 18:10
 */
public class Fragment_maintenance_videoPlay extends BaseChildFragment<Fragment_maintenance_videoPlay_C.Presenter> implements Fragment_maintenance_videoPlay_C.View {

   @Inject
   Fragment_maintenance_videoPlay_P p;
   @BindView(R.id.video_play)
    RecyclerView recyclerView;
    private PagerSnapHelper pagerSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private Video_play_adapter adapter;
    private int position;
    private int status;
    private int start = 0;
    private int length = 1000;

    public static Fragment_maintenance_videoPlay newInstance(int position, int status) {
    Fragment_maintenance_videoPlay videoPlay=new Fragment_maintenance_videoPlay();
    Bundle bundle=new Bundle();
    bundle.putInt("position",position);
        bundle.putInt("status", status);
    videoPlay.setArguments(bundle);
    return  videoPlay;

   }

    @Override
    public Fragment_maintenance_videoPlay_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_video_play;
    }

    @Override
    public void initView() {
        recyclerView.setOnFlingListener(null);
        pagerSnapHelper = new PagerSnapHelper();
        adapter = new Video_play_adapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext,LinearLayoutManager.VERTICAL,false);
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void initData() {
        position = getArguments().getInt("position");
        status = getArguments().getInt("status");
        getList(status);
        adapter.setVideo_playListener(new Video_play_adapter.video_play() {
            @Override
            public void video_discuss(String video_id) {
                Fragment_discuss discuss = Fragment_discuss.newInstance(video_id);
                discuss.show(getChildFragmentManager(),TAG);

            }

            @Override
            public void video_dz(String video_id) {
                getVideoDiscuss(video_id);
            }

            @Override
            public void video_share(String video_id, String video_name, String video_url) {
                Dialog_share dialog_share = new Dialog_share();
                dialog_share.setShareListener(new Dialog_share.shareListener() {
                    @Override
                    public void share_wx() {

                    }

                    @Override
                    public void share_pyq() {

                    }
                });
                dialog_share.show(getChildFragmentManager(), TAG);
            }
        });
        addListener();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (maintenanceVideoList.getJdata() != null) {
            adapter.setNewData(maintenanceVideoList.getJdata());
            if (position != 0) {
                MoveToPosition(linearLayoutManager, recyclerView, position);
            }
        }


    }

    @Override
    public void getVideoListFailure(String failure) {
        ToastUtils.showLong(failure);
        new MaterialDialog.Builder(_mActivity)
                .title("加载失败")
                .content("刷新页面或重进?")
                .negativeText("退出")
                .positiveText("刷新")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        _mActivity.onBackPressed();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                getList(status);
                dialog.dismiss();
            }
        }).build().show();


    }

    @Override
    public void getVideo_dzSuccess(MaintenanceVideo_dz maintenanceVideo_dz) {
                ToastUtils.showLong("点赞成功");
    }

    @Override
    public void getVideo_dzFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void showError(@Nullable Throwable e) {
        super.showError(e);
        new MaterialDialog.Builder(_mActivity)
                .title("加载失败")
                .content("刷新页面或重进?")
                .negativeText("退出")
                .positiveText("刷新")
                .onNegative(new MaterialDialog.SingleButtonCallback() {
                    @Override
                    public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                        dialog.dismiss();
                        _mActivity.onBackPressed();
                    }
                }).onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {

                getList(status);
                dialog.dismiss();
            }
        }).build().show();
    }

    /**
     * 滑动监听
     * 滑动播放
     */
    private void addListener() {
        //  recyclerView.addOnScrollListener(null);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //-1 表示 页面内容向下滑动， 1 表示向上
                //dy <0 表示 上滑， dy>0 表示下滑
                if (!recyclerView.canScrollVertically(-1)) {
                    onScrolledToTop(); //滑动到顶部
                } else if (!recyclerView.canScrollVertically(1)) {
                    onScrolledToBottom();//滑动到底部
                } else if (dy < 0) {
                    onScrolledUp(); //上滑
                } else if (dy > 0) {
                    onScrolledDown(); //下滑
                }

            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (adapter.getData().size() > 0) {
                    LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //屏幕中最后一个可见子项的position
                    // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //当前屏幕所看到的子项个数
                    int visibleItemCount = layoutManager.getChildCount();
                    //当前RecyclerView的所有子项个数
                    int totalItemCount = layoutManager.getItemCount();
                    //RecyclerView的滑动状态
                    int state = recyclerView.getScrollState();
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                            View view = pagerSnapHelper.findSnapView(linearLayoutManager);
                            JzvdStd.releaseAllVideos();
                            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
                            ((MyVideoPlay_Normal) childViewHolder.itemView.findViewById(R.id.myVideo)).startVideo();

                            break;
                        case RecyclerView.SCROLL_STATE_DRAGGING://拖动

                            break;
                        case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                            break;
                    }

                }
            }
        });
    }
    public void onScrolledUp() {  ////滑动到顶部

    }

    public void onScrolledDown() { //滑动到底部

    }

    public void onScrolledToTop() { //上滑

    }

    public void onScrolledToBottom() {//下滑

    }
    @Override
    public void onPause() {
        super.onPause();
        JzvdStd.releaseAllVideos();
    }

    /**
     * RecyclerView 移动到当前位置，
     *
     * @param manager       设置RecyclerView对应的manager
     * @param mRecyclerView 当前的RecyclerView
     * @param n             要跳转的位置
     */
    public void MoveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
        adapter.isScrroll(true, n);

    }

    private void getList(int video_types) {
        Map map = new HashMap();
        map.put("video_name", "");
        if (video_types ==0){
            map.put("video_type", "");
        } else {
            map.put("video_type", String.valueOf(video_types));
        }
        map.put("start", String.valueOf(start));
        map.put("length", String.valueOf(length));
        p.getVideoList(map);
    }

    private void getVideoDiscuss(String video_id){
        Map map=new HashMap();
        map.put("video_id",video_id);
        map.put("token","");
        p.getVideo_dz(map);
    }
}
