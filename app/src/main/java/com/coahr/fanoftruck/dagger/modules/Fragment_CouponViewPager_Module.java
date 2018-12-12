package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_add_address;
import com.coahr.fanoftruck.mvp.view.Mycoupon.Fragment_CouponViewPager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/25
 * on 23:01
 */
@Module
public class Fragment_CouponViewPager_Module {
    @Provides
    public  String provideName() {
        return Fragment_CouponViewPager.class.getName();
    }
}
