package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.ContainerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 17:06
 */
@Module
public class ContainerActivity_Module {
    @Provides
    public  String provideName() {
        return ContainerActivity.class.getName();
    }
}
