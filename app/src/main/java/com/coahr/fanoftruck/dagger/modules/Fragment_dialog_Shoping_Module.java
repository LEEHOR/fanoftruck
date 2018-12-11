package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_dialog_shopping;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_discuss;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/28
 * on 0:09
 */
@Module
public class Fragment_dialog_Shoping_Module {
    @Provides
    public  String provideName() {
        return Fragment_dialog_shopping.class.getName();
    }
}
