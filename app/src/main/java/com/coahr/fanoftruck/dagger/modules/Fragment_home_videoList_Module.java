package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoList;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoPlay;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_home_videoList_Module {
    @Provides
    public  String provideName() {
        return Fragment_home_videoList.class.getName();
    }
}
