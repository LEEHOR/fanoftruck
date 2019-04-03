package com.coahr.fanoftruck.mvp.view.MyOrder.adapter;

import com.coahr.fanoftruck.mvp.view.MyOrder.Fragment_Order_pager;

import org.jetbrains.annotations.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

/**
 * @author Leehor
 * 版本：
 * 创建日期：2019/4/2
 * 描述：
 */
public class MyOrderViewPagerAdapter extends FragmentPagerAdapter {
    private String[] title = {"全部", "待付款","待发货","待收货","待评价","退换货"};
    private String[] order_statuss = {"-2","0","1","2","4","5"};
    private String order_id;
    public MyOrderViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }
    public void setOrder_id(String order_id){
        this.order_id=order_id;
    }


    @Override
    public Fragment getItem(int position) {
        return Fragment_Order_pager.newInstance(order_statuss[position]);
    }

    @Override
    public int getCount() {
        return title.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title[position];
    }
}
