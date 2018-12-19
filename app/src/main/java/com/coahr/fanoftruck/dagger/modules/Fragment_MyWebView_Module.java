package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.MyWebView.Fragment_myWebView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 17:06
 */
@Module
public class Fragment_MyWebView_Module {
    @Provides
    public  String provideName() {
        return Fragment_myWebView.class.getName();
    }
}
