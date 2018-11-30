package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.Utils.GpsLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.Utils.GpsLocation.GaodeMapLocation;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:56
 */
@Module
public class GaodeLocationModule {
    @Singleton
    @Provides
    public GaodeMapLocation getGaodeLocation(){
        return new GaodeMapLocation();
    }
}
