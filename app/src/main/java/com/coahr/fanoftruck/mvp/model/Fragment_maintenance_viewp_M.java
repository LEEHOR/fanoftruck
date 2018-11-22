package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.BaiDuLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_viewp_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:29
 */
public class Fragment_maintenance_viewp_M extends BaseModel<Fragment_maintenance_viewp_C.Presenter> implements Fragment_maintenance_viewp_C.Model {
    @Inject
    public Fragment_maintenance_viewp_M() {
        super();
    }
    @Inject
    BaiduLocationHelper baiduLocationHelper;
    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                baiduLocationHelper.stopLocation();

            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
        }
    };
    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }
    @Override
    public void startLocation() {

    }
}
