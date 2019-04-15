package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.content.pm.ActivityInfo;
import android.media.Image;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BasePlayVideoFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import butterknife.BindView;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 15:46
 */
public class Fragment_home_videoPlay extends BasePlayVideoFragment {
    @BindView(R.id.jz_video_play)
    JzvdStd jz_video_play;

    @BindView(R.id.mytittle)
    MyTittleBar myTittleBar;

    public static Fragment_home_videoPlay newInstance(String vidioLink, String videoCover) {
        Fragment_home_videoPlay fragment_home_videoPlay = new Fragment_home_videoPlay();
        Bundle bundle = new Bundle();
        bundle.putString("videoLink", vidioLink);
        bundle.putString("videoCover", videoCover);
        fragment_home_videoPlay.setArguments(bundle);
        return fragment_home_videoPlay;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_videoplay;
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
        Bundle bundle = getArguments();
        String videoLink = bundle.getString("videoLink");
        String videoCover = bundle.getString("videoCover");

        jz_video_play.setUp(videoLink, "", JzvdStd.SCREEN_WINDOW_NORMAL);
        Imageloader.loadImage_larger(videoCover, jz_video_play.thumbImageView);
        Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
        Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;

    }

    @Override
    public void onPause() {
        super.onPause();
        Jzvd.releaseAllVideos();
        //Change these two variables back
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
}
