package com.coahr.fanoftruck.mvp.model;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.GaodeMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.MainActivityC;


import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class MainActivityM extends BaseModel<MainActivityC.Presenter> implements MainActivityC.Model {
    @Inject
    public MainActivityM() {
        super();
    }
    @Inject
    GaodeMapLocation gaodeMapLocation;

    private GaodeMapLocation.OnLocationCallBack onLocationCallBack=new GaodeMapLocation.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(AMapLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                gaodeMapLocation.stopLocation();

            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
            gaodeMapLocation.stopLocation();
        }
    };

    private void initlocation() {
        gaodeMapLocation.registerLocationCallback(onLocationCallBack);
    }
    @Override
    public void detachPresenter() {
        super.detachPresenter();
        gaodeMapLocation.unRegisterLocationCallback(onLocationCallBack);
    }

    @Override
    public void startLocation() {

    }
}
