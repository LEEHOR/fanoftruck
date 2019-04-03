package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Myself.HelpCenterFragment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by lizhiguo
 * on 2019/4/1
 * on 11:36
 */
@Module
public class HelpCenterFragmentModule {
    @Provides
    public  String provideName() {
        return HelpCenterFragment.class.getName();
    }
}
