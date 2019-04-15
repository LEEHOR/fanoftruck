package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.model.Bean.HomeData;
import com.coahr.fanoftruck.mvp.model.Fragment_home_M;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_home_P extends BasePresenter<Fragment_home_C.View,Fragment_home_C.Model> implements Fragment_home_C.Presenter {

    @Inject
    public Fragment_home_P(Fragment_Home mview, Fragment_home_M mModel) {
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

    @Override
    public void getHomeData() {
        if (mModle != null) {
            mModle.getHomeData();
        }
    }

    @Override
    public void getHomeDataSuccess(HomeData homeData) {
        if (getView() != null) {
            getView().getHomeDataSuccess(homeData);
        }
    }

    @Override
    public void getHomeDataFailure(String failure) {
        if (getView() != null) {
            getView().getHomeDataFailure(failure);
        }
    }
}
