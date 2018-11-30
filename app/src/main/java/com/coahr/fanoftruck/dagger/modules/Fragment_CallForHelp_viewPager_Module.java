package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.CallForHelp.CallForHelp_viewPager;
import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_CallForHelp_viewPager_Module {
    @Provides
    public  String provideName() {
        return CallForHelp_viewPager.class.getName();
    }
}
