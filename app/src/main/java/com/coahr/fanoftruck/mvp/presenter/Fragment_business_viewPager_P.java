package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_business_viewPager_C;
import com.coahr.fanoftruck.mvp.model.Fragment_business_viewPaget_M;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_Business_viewPager;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class Fragment_business_viewPager_P extends BasePresenter<Fragment_business_viewPager_C.View,Fragment_business_viewPager_C.Model> implements Fragment_business_viewPager_C.Presenter
{
    @Inject
    public Fragment_business_viewPager_P(Fragment_Business_viewPager mview, Fragment_business_viewPaget_M mModel) {
       super(mview,mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        if (getView() != null) {
            getView().onLocationFailure(failure);
        }
    }
}
