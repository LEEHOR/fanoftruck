package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:16
 */
@Module
public class Fragment_UserInfo_Module {
    @Provides
    public String provideName(){
        return Fragment_myUerInfo.class.getName();
    }
}
