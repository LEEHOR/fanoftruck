package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_storeOrder_C;
import com.coahr.fanoftruck.mvp.model.Fragment_storeOrder_M;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_storeOrder;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_storeOrder_P extends BasePresenter<Fragment_storeOrder_C.View,Fragment_storeOrder_C.Model> implements Fragment_storeOrder_C.Presenter {

    @Inject
    public Fragment_storeOrder_P(Fragment_storeOrder mview, Fragment_storeOrder_M mModel) {
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
