package com.coahr.fanoftruck.mvp.view.VideoPlay.adapter;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_child;

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
