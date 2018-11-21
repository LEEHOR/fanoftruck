package com.coahr.fanoftruck.mvp.view.BusinessOpportunity.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_business_child;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 15:46
 */
public class ViewPageAdapter extends FragmentPagerAdapter {
  String  headers[] = {"全部意向", "带跟进", "跟进中", "已成单","已返佣","已关闭"};
    public ViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return Fragment_business_child.newInstance(i);
    }

    @Override
    public int getCount() {
        return headers.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return headers[position];
    }
}
