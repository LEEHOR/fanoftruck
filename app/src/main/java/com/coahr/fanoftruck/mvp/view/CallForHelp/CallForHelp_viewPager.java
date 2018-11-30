package com.coahr.fanoftruck.mvp.view.CallForHelp;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.view.CallForHelp.adapter.HelpPagerAdapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 16:57
 */
public class CallForHelp_viewPager extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;
    @BindView(R.id.help_tab)
    TabLayout help_tab;
    @BindView(R.id.help_viewPager)
    ViewPager help_viewPager;
    private HelpPagerAdapter helpPagerAdapter;

    public static CallForHelp_viewPager newInstance() {
        CallForHelp_viewPager viewPager=new CallForHelp_viewPager();
        return viewPager;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_callforhelp_viewpage;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        helpPagerAdapter = new HelpPagerAdapter(getChildFragmentManager());
        help_viewPager.setAdapter(helpPagerAdapter);
        help_tab.setupWithViewPager(help_viewPager);
        help_viewPager.setCurrentItem(0);
    }
}
