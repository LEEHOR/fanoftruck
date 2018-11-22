package com.coahr.fanoftruck.mvp.view.Home.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_child_M;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_child;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 15:19
 */
public class Fragment_maintenance_viewpager_adapter extends FragmentPagerAdapter {
    private String head[]={"全部","车辆操作演示","卡车视频互动区","技术维修"};
    public Fragment_maintenance_viewpager_adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return Fragment_maintenance_child.newInstance(i);
    }

    @Override
    public int getCount() {
        return head.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return head[position];
    }
}
