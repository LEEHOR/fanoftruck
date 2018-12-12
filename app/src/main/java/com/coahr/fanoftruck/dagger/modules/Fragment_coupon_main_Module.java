package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_add_address;
import com.coahr.fanoftruck.mvp.view.Mycoupon.Fragment_coupon_main;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/25
 * on 23:01
 */
@Module
public class Fragment_coupon_main_Module {
    @Provides
    public  String provideName() {
        return Fragment_coupon_main.class.getName();
    }
}
