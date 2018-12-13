package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.AnimationUtil;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.model.Bean.View_videoBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_videoPlay_P;
import com.coahr.fanoftruck.mvp.view.VideoPlay.adapter.Video_play_adapter;
import com.coahr.fanoftruck.widgets.AltDialog.Dialog_share;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlay_Normal;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JzvdStd;
import okhttp3.internal.platform.Platform;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 18:10
 * 仿抖音播放
 */
public class Fragment_maintenance_videoPlay extends BaseChildFragment<Fragment_maintenance_videoPlay_C.Presenter> implements Fragment_maintenance_videoPlay_C.View, View.OnClickListener {

   @Inject
   Fragment_maintenance_videoPlay_P p;
   @BindView(R.id.video_play)
    RecyclerView recyclerView;
    @BindView(R.id.right_menu)
    FrameLayout right_menu;
    @BindView(R.id.iv_user_head)
    ImageView iv_user_head; //头像
    @BindView(R.id.video_view_num)
    TextView video_view_num;  //浏览数
    @BindView(R.id.rl_2)
    RelativeLayout rl_2; //评论
    @BindView(R.id.video_discuss_num)
    TextView video_discuss_num; //评论数
    @BindView(R.id.rl_3)
    RelativeLayout rl_3; //点赞
    @BindView(R.id.video_dz_num)
    TextView video_dz_num; //点赞数
    @BindView(R.id.rl_4)
    RelativeLayout rl_4; //分享
    private PagerSnapHelper pagerSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private Video_play_adapter adapter;
    private int position;
    private int status;
    private int start = 0;
    private int length = 1000;
    private String video_id;
    private int lastVisibleItemPosition;
    private List<MaintenanceVideoList.JdataBean> videoList = new ArrayList<>();
    private View_videoBean.JdataBean one_video;  //单个视频


    public static Fragment_maintenance_videoPlay newInstance(int position, int status, String video_id) {
    Fragment_maintenance_videoPlay videoPlay=new Fragment_maintenance_videoPlay();
    Bundle bundle=new Bundle();
    bundle.putInt("position",position);
        bundle.putInt("status", status);
        bundle.putString("video_id", video_id);
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
        rl_2.setOnClickListener(this);
        rl_3.setOnClickListener(this);
        rl_4.setOnClickListener(this);
    }

    @Override
    public void initData() {
        position = getArguments().getInt("position");
        status = getArguments().getInt("status");
        video_id = getArguments().getString("video_id");
        KLog.d("video_id", video_id);
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
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (maintenanceVideoList.getJdata() != null) {
            videoList.clear();
            videoList = maintenanceVideoList.getJdata();
            adapter.setNewData(maintenanceVideoList.getJdata());
            getOneView(video_id);

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
        getOneView(one_video.getId());
    }

    @Override
    public void getVideo_dzFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void getVideo_oneSuccess(View_videoBean view_videoBean) {
        one_video = view_videoBean.getJdata();
        video_view_num.setText(view_videoBean.getJdata().getVideo_view_num());
        video_discuss_num.setText(view_videoBean.getJdata().getDiscuss_num());
        video_dz_num.setText(view_videoBean.getJdata().getVideo_dz_num());
    }

    @Override
    public void getVideo_oneFailure(String failure) {
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
                   /* LinearLayoutManager layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                    //屏幕中最后一个可见子项的position
                    // int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
                    //当前屏幕所看到的子项个数
                    int visibleItemCount = layoutManager.getChildCount();
                    //当前RecyclerView的所有子项个数
                    int totalItemCount = layoutManager.getItemCount();
                    //RecyclerView的滑动状态
                    int state = recyclerView.getScrollState();*/
                    switch (newState) {
                        case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                            LinearLayoutManager layoutManagerS = (LinearLayoutManager) recyclerView.getLayoutManager();
                            View view = pagerSnapHelper.findSnapView(linearLayoutManager);
                            JzvdStd.releaseAllVideos();
                            RecyclerView.ViewHolder childViewHolder = recyclerView.getChildViewHolder(view);
                            int childCount = layoutManagerS.getChildCount(); //获取屏幕可见数目
                            if (childCount == 1) {  //当屏幕可见数目为1时调用
                                lastVisibleItemPosition = layoutManagerS.findLastVisibleItemPosition();
                                getItem(lastVisibleItemPosition);
                                AnimationUtil.bottomMoveToViewLocation_Visible(right_menu, 300);
                                ((MyVideoPlay_Normal) childViewHolder.itemView.findViewById(R.id.myVideo)).startVideo();
                                KLog.d("滑动", "停止1");
                            } else {
                                KLog.d("滑动", "停止2");
                            }

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

    public void onScrolledUp() {  //上滑
        KLog.d("滑动", "up");
        AnimationUtil.moveToViewBottom_Gone(right_menu, 300);
    }

    public void onScrolledDown() { //下滑
        KLog.d("滑动", "down");
        AnimationUtil.moveToViewBottom_Gone(right_menu, 300);
    }

    public void onScrolledToTop() { //滑动到顶部

        KLog.d("滑动", "top");
    }

    public void onScrolledToBottom() {//滑动到底部
        KLog.d("滑动", "bottom");
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
        KLog.d("数目",n,adapter.getData().size());
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
            KLog.d("数目","1");
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
            KLog.d("数目","2");
        } else {
            mRecyclerView.scrollToPosition(n);
            KLog.d("数目","3");
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
        map.put("token",Constants.token);
        p.getVideo_dz(map);
    }


    @Override
    public void onClick(View view) {
        if (one_video != null) {
            switch (view.getId()) {
                case R.id.rl_2:  //评论
                    Fragment_discuss discuss = Fragment_discuss.newInstance(one_video.getId());
                    discuss.show(getChildFragmentManager(), TAG);
                    discuss.setAddDiscussListener(new AddDiscuss_Listener());
                    break;
                case R.id.rl_3:  //点赞
                    getVideoDiscuss(one_video.getId());
                    break;
                case R.id.rl_4:  //分享

                    break;
            }
        } else {
            ToastUtils.showLong("请刷新后再试");
        }

    }

    private void getOneView(String video_id) {
        Map map = new HashMap();
        map.put("video_id", video_id);
        p.getVideo_one(map);
        ToastUtils.showLong("请求");
    }


    /**
     * 获取当前位置的视频数据
     *
     * @param position
     * @return
     */
    private void getItem(int position) {
        if (videoList != null && videoList.size() > 0) {
            MaintenanceVideoList.JdataBean jdataBean = videoList.get(position);
            getOneView(jdataBean.getId());
        } else {

        }
    }

    /**
     * 评论成功回调
     */
    public class AddDiscuss_Listener implements Fragment_discuss.AddDiscussListener {
        @Override
        public void addDiscussSuccess() {
            //刷新评论数
            getOneView(one_video.getId());
        }
    }
}
