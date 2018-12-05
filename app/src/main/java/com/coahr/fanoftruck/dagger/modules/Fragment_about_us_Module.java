package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Myself.Fragment_about_us;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 11:36
 */
@Module
public class Fragment_about_us_Module {
    @Provides
    public  String provideName() {
        return Fragment_about_us.class.getName();
    }
}
