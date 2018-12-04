package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.MainActivityC;
import com.coahr.fanoftruck.mvp.model.MainActivityM;
import com.coahr.fanoftruck.mvp.view.MainActivity;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class MainActivityP extends BasePresenter<MainActivityC.View,MainActivityC.Model> implements MainActivityC.Presenter
{
    @Inject
    public MainActivityP(MainActivity mview, MainActivityM mModel) {
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
