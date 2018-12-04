package com.coahr.fanoftruck.mvp.model;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.GaodeMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_storeOrder_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_storeOrder_M extends BaseModel<Fragment_storeOrder_C.Presenter> implements Fragment_storeOrder_C.Model {
    @Inject
    public Fragment_storeOrder_M() {
        super();
    }
    @Inject
    GaodeMapLocation gaodeMapLocation;

    private GaodeMapLocation.OnLocationCallBack onlocationCallBack=new GaodeMapLocation.OnLocationCallBack() {
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
        gaodeMapLocation.registerLocationCallback(onlocationCallBack);
    }
    @Override
    public void detachPresenter() {
        super.detachPresenter();
        gaodeMapLocation.unRegisterLocationCallback(onlocationCallBack);
    }

    @Override
    public void startLocation() {
        initlocation();
        gaodeMapLocation.startLocation();
    }
}
