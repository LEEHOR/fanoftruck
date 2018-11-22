package com.coahr.fanoftruck.mvp.view.Home;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseViewHolder;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseRecAdapter;
import com.coahr.fanoftruck.mvp.Base.BaseRecViewHolder;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_videoPlay_P;
import com.coahr.fanoftruck.mvp.view.Home.adapter.Video_play_adapter;
import com.coahr.fanoftruck.widgets.MyVideo.MyVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 18:10
 */
public class Fragment_maintenance_videoPlay extends BaseChildFragment<Fragment_maintenance_videoPlay_C.Presenter> implements Fragment_maintenance_videoPlay_C.View {

   @Inject
   Fragment_maintenance_videoPlay_P P;
   @BindView(R.id.video_play)
    RecyclerView recyclerView;
    private PagerSnapHelper pagerSnapHelper;
    private LinearLayoutManager linearLayoutManager;
    private Video_play_adapter adapter;
    private List<String> urlList;
    private ListVideoAdapter listVideoAdapter;

    public static Fragment_maintenance_videoPlay newInstance(int position){
    Fragment_maintenance_videoPlay videoPlay=new Fragment_maintenance_videoPlay();
    Bundle bundle=new Bundle();
    bundle.putInt("position",position);
    videoPlay.setArguments(bundle);
    return  videoPlay;

   }
    @Override
    public BaseContract.Presenter getPresenter() {
        return P;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_video_play;
    }

    @Override
    public void initView() {
        urlList = new ArrayList<>();
        urlList.add("http://image.38.hn/public/attachment/201805/100651/201805181532123423.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803151735198462.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150923220770.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150922255785.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803150920130302.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141625005241.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803141624378522.mp4");
        urlList.add("http://image.38.hn/public/attachment/201803/100651/201803131546119319.mp4");

    }

    @Override
    public void initData() {
        recyclerView.setOnFlingListener(null);
        pagerSnapHelper = new PagerSnapHelper();
        pagerSnapHelper.attachToRecyclerView(recyclerView);
        listVideoAdapter = new ListVideoAdapter(urlList);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(listVideoAdapter);
        addListener();
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    private void addListener() {

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {


            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState) {
                    case RecyclerView.SCROLL_STATE_IDLE://停止滚动
                        View view = pagerSnapHelper.findSnapView(linearLayoutManager);
                        JZVideoPlayer.releaseAllVideos();
                        RecyclerView.ViewHolder viewHolder = recyclerView.getChildViewHolder(view);
                        if (viewHolder != null && viewHolder instanceof VideoViewHolder) {
                            ((VideoViewHolder) viewHolder).myvideo.startVideo();
                        }

                        break;
                    case RecyclerView.SCROLL_STATE_DRAGGING://拖动
                        break;
                    case RecyclerView.SCROLL_STATE_SETTLING://惯性滑动
                        break;
                }

            }
        });
    }
    @Override
    public void onPause() {
        super.onPause();
        JZVideoPlayer.releaseAllVideos();
    }
    class ListVideoAdapter extends BaseRecAdapter<String, VideoViewHolder> {


        public ListVideoAdapter(List<String> list) {
            super(list);
        }

        @Override
        public void onHolder(VideoViewHolder holder, String bean, int position) {
            ViewGroup.LayoutParams layoutParams = holder.itemView.getLayoutParams();
            layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT;

            holder.myvideo.setUp(bean, JZVideoPlayerStandard.CURRENT_STATE_NORMAL);
            if (position == 0) {
                holder.myvideo.startVideo();
            }
            Glide.with(context).load(bean).into(holder.myvideo.thumbImageView);
        }

        @Override
        public VideoViewHolder onCreateHolder() {
            return new VideoViewHolder(getViewByRes(R.layout.item_video_play));

        }


    }

    public class VideoViewHolder extends BaseRecViewHolder {
        public View rootView;
        public MyVideoPlayer myvideo;

        public VideoViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.myvideo = rootView.findViewById(R.id.myVideo);
        }

    }
}
