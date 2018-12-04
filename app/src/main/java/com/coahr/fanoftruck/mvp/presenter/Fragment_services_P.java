package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_services_C;
import com.coahr.fanoftruck.mvp.model.Fragment_services_M;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Services;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:32
 */
public class Fragment_services_P extends BasePresenter<Fragment_services_C.View,Fragment_services_C.Model> implements Fragment_services_C.Presenter {
    @Inject
    public Fragment_services_P(Fragment_Services mview, Fragment_services_M mModel) {
        super(mview, mModel);
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
