package com.coahr.fanoftruck.mvp.view.MyOrder;

import android.os.Bundle;
import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.view.MyOrder.adapter.MyOrderViewPagerAdapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;

/**
 * @author Leehor
 * 版本：
 * 创建日期：2019/4/3
 * 描述：
 */
public class Fragment_OrderViewPager extends BaseFragment {

    @BindView(R.id.myOrder_tittle)
    MyTittleBar myOrderTittle;
    @BindView(R.id.myorder_tab)
    TabLayout myorderTab;
    @BindView(R.id.myOrder_viewPage)
    ViewPager myOrderViewPage;
    private int showPosition;
    private MyOrderViewPagerAdapter myCommodityOrderVPAdapter;

    public static Fragment_OrderViewPager newInstance(int showPosition){
        Fragment_OrderViewPager fragment = new Fragment_OrderViewPager();
        Bundle arg = new Bundle();
        arg.putInt("showPosition",showPosition);
        fragment.setArguments(arg);
        return fragment;
    }
    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_myorder_viewpager;
    }

    @Override
    public void initView() {
        myOrderTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        if (getArguments() != null) {
            showPosition = getArguments().getInt("showPosition");
        }
    }

    @Override
    public void initData() {
        myCommodityOrderVPAdapter = new MyOrderViewPagerAdapter(getChildFragmentManager());
        myOrderViewPage.setAdapter(myCommodityOrderVPAdapter);
        myOrderViewPage.setOffscreenPageLimit(myCommodityOrderVPAdapter.getCount()-1);
        myorderTab.setupWithViewPager(myOrderViewPage);
        myOrderViewPage.setCurrentItem(showPosition);
    }
}
