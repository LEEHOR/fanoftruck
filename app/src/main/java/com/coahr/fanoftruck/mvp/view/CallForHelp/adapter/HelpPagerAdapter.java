package com.coahr.fanoftruck.mvp.view.CallForHelp.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 17:03
 */
public class HelpPagerAdapter extends FragmentPagerAdapter {
    private  String heads[]={"常用","救援","保险"};
    public HelpPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return Fragment_help.newInstance(i);
    }

    @Override
    public int getCount() {
        return heads.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return heads[position];
    }
}
