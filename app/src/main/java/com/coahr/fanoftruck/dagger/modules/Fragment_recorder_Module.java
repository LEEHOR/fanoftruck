package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.RecorderVideo.FragmentRecorder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/25
 * on 23:01
 */
@Module
public class Fragment_recorder_Module {
    @Provides
    public  String provideName() {
        return FragmentRecorder.class.getName();
    }
}
