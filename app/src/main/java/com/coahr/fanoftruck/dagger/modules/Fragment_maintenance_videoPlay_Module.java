package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Home.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_videoPlay;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:42
 */
@Module
public class Fragment_maintenance_videoPlay_Module {
    @Provides
    public String provideName() {
        return Fragment_maintenance_videoPlay.class.getName();
    }
}
