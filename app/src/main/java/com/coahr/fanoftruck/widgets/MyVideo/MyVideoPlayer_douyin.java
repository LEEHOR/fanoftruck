package com.coahr.fanoftruck.widgets.MyVideo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.danikula.videocache.HttpProxyCacheServer;

import cn.jzvd.JZDataSource;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * Created by Leehor
 * on 2018/11/27
 * on 9:28
 *抖音效果
 */

public class MyVideoPlayer_douyin extends JzvdStd {
    private Context context;
    public MyVideoPlayer_douyin(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlayer_douyin(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onAutoCompletion() {
        thumbImageView.setVisibility(View.GONE);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            onStateAutoComplete();
            setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_FULLSCREEN);
        } else {

            setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_FULLSCREEN);
            super.onAutoCompletion();
        }
        //循环播放

        startVideo();
    }

    @Override
    public void setUp(String url, String title, int screen) {
        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = BaseApplication.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, title, screen);
        } else {
            super.setUp(url, title, screen);
        }
    }

    @Override
    public void init(final Context context) {
        super.init(context);

    }

    @Override
    public void startVideo() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            initTextureView();
            addTextureView();
            AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            JZUtils.scanForActivity(getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

            //JZMediaManager.setDataSource(jzDataSource);
           // JZMediaManager.setCurrentDataSource(JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex));
            JZMediaManager.instance().positionInList = positionInList;
            onStatePreparing();

        } else {
            Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
            super.startVideo();

        }
        resetPlayView();
    }

    @Override
    public void startWindowTiny() {

    }

    private void resetPlayView() {
        if (isPlay()) {

        } else {

        }
    }


    /**
     * 是否播放
     *
     * @return
     */
    private boolean isPlay() {
        if (currentState == CURRENT_STATE_PREPARING || currentState == CURRENT_STATE_PLAYING || currentState == -1) {
            return true;
        }

        return false;
    }

}
