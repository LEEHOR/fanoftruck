package com.coahr.fanoftruck.mvp.view.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.view.Home.adapter.Fragment_maintenance_viewpager_adapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 0:28
 * 维修视频
 */
public class Fragment_MaintenanceVideo_viewPage extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintenance_video;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        Fragment_maintenance_viewpager_adapter adapter=new Fragment_maintenance_viewpager_adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        viewPager.setCurrentItem(0);
    }
}
