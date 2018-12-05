package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 17:08
 */
@Module
public class Fragment_store_Module {
    @Provides
    public  String provideName() {
        return Fragment_Store.class.getName();
    }
}
