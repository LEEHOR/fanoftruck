package com.coahr.fanoftruck.mvp.view.Mycoupon.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;



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
        return  null;
    }

    @Override
    public int getCount() {
        return 2 ;
    }
}
