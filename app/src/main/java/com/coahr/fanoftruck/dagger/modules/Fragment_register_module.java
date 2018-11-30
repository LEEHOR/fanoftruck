package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_register;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/27
 * on 16:02
 */
@Module
public class Fragment_register_module {
    @Provides
    public  String provideName() {
        return Fragment_register.class.getName();
    }
}
