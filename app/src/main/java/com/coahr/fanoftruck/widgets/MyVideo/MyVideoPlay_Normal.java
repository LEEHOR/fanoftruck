package com.coahr.fanoftruck.widgets.MyVideo;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.danikula.videocache.HttpProxyCacheServer;

import cn.jzvd.JZDataSource;
import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;


public class MyVideoPlay_Normal extends JzvdStd {
    public Context context;
    private ImageView fullscreen,backButton;

    public MyVideoPlay_Normal(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlay_Normal(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void init(Context context) {
        super.init(context);
        this.context = context;
        fullscreen = findViewById(cn.jzvd.R.id.fullscreen);
        fullscreen.setVisibility(GONE);
        backButton = findViewById(cn.jzvd.R.id.back);
        backButton.setVisibility(VISIBLE);
        backButton.setOnClickListener(this);

    }

    @Override
    public int getLayoutId() {
        return super.getLayoutId();
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        if (v.getId()== R.id.back){
            backPress();
        }

    }

    @Override
    public void setUp(JZDataSource jzDataSource, int screen) {
        super.setUp(jzDataSource, screen);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            // shareButton.setVisibility(View.VISIBLE);
        } else {
            // shareButton.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setUp(String url, String title, int screen) {
        super.setUp(url, title, screen);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            // shareButton.setVisibility(View.VISIBLE);
        } else {
            // shareButton.setVisibility(View.INVISIBLE);
        }
        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = BaseApplication.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, title, screen);
        } else {
            super.setUp(url, title, screen);
        }


    }

    @Override
    public void onAutoCompletion() {
        thumbImageView.setVisibility(View.GONE);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            onStateAutoComplete();
            setUp(jzDataSource, JzvdStd.SCREEN_WINDOW_FULLSCREEN);
        } else {
            super.onAutoCompletion();
        }
        //循环播放

        startVideo();
    }

    @Override
    public void startVideo() {
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            initTextureView();
            addTextureView();
            AudioManager mAudioManager = (AudioManager) getContext().getSystemService(Context.AUDIO_SERVICE);
            mAudioManager.requestAudioFocus(onAudioFocusChangeListener, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);
            JZUtils.scanForActivity(getContext()).getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
            JZMediaManager.setDataSource(jzDataSource);
            JZMediaManager.instance().positionInList = positionInList;
            onStatePreparing();

        } else {
            Jzvd.FULLSCREEN_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
            Jzvd.NORMAL_ORIENTATION = ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
            super.startVideo();

        }
    }
}
