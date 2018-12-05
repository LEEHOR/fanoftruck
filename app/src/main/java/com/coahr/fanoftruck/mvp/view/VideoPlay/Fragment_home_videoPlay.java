package com.coahr.fanoftruck.mvp.view.VideoPlay;

import android.content.pm.ActivityInfo;
import android.view.View;

import com.bumptech.glide.Glide;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BasePlayVideoFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

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
    public static Fragment_home_videoPlay newInstance() {
        return new Fragment_home_videoPlay();
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
        jz_video_play.setUp("http://jzvd.nathen.cn/342a5f7ef6124a4a8faf00e738b8bee4/cf6d9db0bd4d41f59d09ea0a81e918fd-5287d2089db37e62345123a1be272f8b.mp4"
                , "饺子快长大", JzvdStd.SCREEN_WINDOW_NORMAL);
        Glide.with(this).load("http://jzvd-pic.nathen.cn/jzvd-pic/1bb2ebbe-140d-4e2e-abd2-9e7e564f71ac.png").into(jz_video_play.thumbImageView);
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
            return true ;
        }
        return super.onBackPressedSupport();
    }
}
