package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 18:11
 */
@Module
public class MainActivity_Module {
    @Provides
    public  String provideName() {
        return MainActivity.class.getName();
    }
}
