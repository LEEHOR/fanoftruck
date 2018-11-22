package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_child_M;
import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_videoPlay_M;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_child;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_videoPlay;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class Fragment_maintenance_videoPlay_P extends BasePresenter<Fragment_maintenance_videoPlay_C.View,Fragment_maintenance_videoPlay_C.Model> implements Fragment_maintenance_videoPlay_C.Presenter
{
    @Inject
    public Fragment_maintenance_videoPlay_P(Fragment_maintenance_videoPlay mview, Fragment_maintenance_videoPlay_M mModel) {
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
