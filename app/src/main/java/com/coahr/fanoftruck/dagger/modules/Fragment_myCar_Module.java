package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myCar;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_myCar_Module {
    @Provides
    public  String provideName() {
        return Fragment_myCar.class.getName();
    }
}
