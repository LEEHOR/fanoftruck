package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_business_viewPager_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_business_viewPager_P;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.adapter.ViewPageAdapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:10
 */
public class Fragment_Business_viewPager extends BaseFragment<Fragment_business_viewPager_C.Presenter> implements Fragment_business_viewPager_C.View {

    @Inject
    Fragment_business_viewPager_P p;
    @BindView(R.id.businss_viewPage)
    ViewPager businss_viewPage;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.mytitle_business)
    MyTittleBar mytitle_business;

    public static Fragment_Business_viewPager newInstance() {
        return new Fragment_Business_viewPager();
    }


    @Override
    public Fragment_business_viewPager_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_business_opportunity_order;
    }

    @Override
    public void initView() {
        mytitle_business.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        mytitle_business.getRightText().setText("推荐购车");
        mytitle_business.getRightText().setVisibility(View.VISIBLE);
        mytitle_business.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        mytitle_business.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.putExtra("tofragment",Constants.Fragment_recommendCar);
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {
        ViewPageAdapter adapter=new ViewPageAdapter(getChildFragmentManager());
        businss_viewPage.setAdapter(adapter);
        tabLayout.setupWithViewPager(businss_viewPage);
        businss_viewPage.setCurrentItem(0);
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
