package com.coahr.fanoftruck.widgets.MyVideo;

import android.content.Context;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.danikula.videocache.HttpProxyCacheServer;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZUtils;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * 作者： ch
 * 时间： 2018/8/17 0017-下午 5:14
 * 描述：
 * 来源：
 */


public class MyVideoPlayer extends JZVideoPlayerStandard {
    private Context context;
    public MyVideoPlayer(Context context) {
        super(context);
        this.context = context;
    }

    public MyVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public void onAutoCompletion() {

        thumbImageView.setVisibility(View.GONE);

        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            onStateAutoComplete();
            setUp((String) getCurrentUrl(), JZVideoPlayer.SCREEN_WINDOW_FULLSCREEN);
        } else {
            super.onAutoCompletion();
            setUp((String) getCurrentUrl(), JZVideoPlayer.CURRENT_STATE_NORMAL);
        }
        //循环播放

        startVideo();
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {

        if (url.startsWith("http")) {
            HttpProxyCacheServer proxy = BaseApplication.getProxy(context);
            String proxyUrl = proxy.getProxyUrl(url);
            super.setUp(proxyUrl, screen, objects);
        } else {
            super.setUp(url, screen, objects);
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

            JZMediaManager.setDataSource(dataSourceObjects);
            JZMediaManager.setCurrentDataSource(JZUtils.getCurrentFromDataSource(dataSourceObjects, currentUrlMapIndex));
            JZMediaManager.instance().positionInList = positionInList;
            onStatePreparing();

        } else {
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
