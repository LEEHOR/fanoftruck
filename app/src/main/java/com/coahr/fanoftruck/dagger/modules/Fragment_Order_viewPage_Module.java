package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyOrder.Fragment_OrderViewPager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_Order_viewPage_Module {
    @Provides
    public  String provideName() {
        return Fragment_OrderViewPager.class.getName();
    }
}
