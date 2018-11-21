package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_myself_Module {
    @Provides
    public  String provideName() {
        return Fragment_Myself.class.getName();
    }
}
