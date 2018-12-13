package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponReceive;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_CouponReceive_Module {
    @Provides
    public  String provideName() {
        return Fragment_CouponReceive.class.getName();
    }
}
