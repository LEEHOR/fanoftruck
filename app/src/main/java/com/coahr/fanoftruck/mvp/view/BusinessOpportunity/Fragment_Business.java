package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_business_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_business_P;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.adapter.ViewPageAdapter;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:10
 */
public class Fragment_Business extends BaseFragment<Fragment_business_C.Presenter> implements Fragment_business_C.View {

    @Inject
    Fragment_business_P p;
    @BindView(R.id.businss_viewPage)
    ViewPager businss_viewPage;
    @BindView(R.id.tab)
    TabLayout tabLayout;

    public static Fragment_Business newInstance() {
        return new Fragment_Business();
    }


    @Override
    public Fragment_business_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_business_opportunity_order;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        ViewPageAdapter adapter=new ViewPageAdapter(getChildFragmentManager());
        businss_viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(businss_viewPage);
        businss_viewPage.setCurrentItem(0);
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
