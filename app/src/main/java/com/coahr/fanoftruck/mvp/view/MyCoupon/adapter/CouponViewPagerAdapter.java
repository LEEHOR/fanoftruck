package com.coahr.fanoftruck.mvp.view.MyCoupon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_coupon_main;


public class CouponViewPagerAdapter extends FragmentPagerAdapter {
    private int  counts;
    private int fromFragment;
    private double orderPricr;
    public CouponViewPagerAdapter(FragmentManager fm, int fromFragment, double orderPrice) {
        super(fm);
        this.fromFragment=fromFragment;
        this.orderPricr=orderPrice;
    }

    @Override
    public Fragment getItem(int position) {
            return Fragment_coupon_main.newInstance(position,fromFragment,orderPricr);
    }

    @Override
    public int getCount() {
        return 2 ;
    }
}
