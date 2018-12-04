package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_business_child_C;
import com.coahr.fanoftruck.mvp.model.Fragment_business_child_M;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_business_child;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class Fragment_business_child_P extends BasePresenter<Fragment_business_child_C.View,Fragment_business_child_C.Model> implements Fragment_business_child_C.Presenter
{
    @Inject
    public Fragment_business_child_P(Fragment_business_child mview, Fragment_business_child_M mModel) {
       super(mview,mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {
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
