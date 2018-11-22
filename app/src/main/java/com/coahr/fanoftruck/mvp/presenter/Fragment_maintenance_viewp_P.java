package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_viewp_C;
import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_viewp_M;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_MaintenanceVideo_viewPage;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:32
 */
public class Fragment_maintenance_viewp_P extends BasePresenter<Fragment_maintenance_viewp_C.View,Fragment_maintenance_viewp_C.Model> implements Fragment_maintenance_viewp_C.Presenter {

    @Inject
    public Fragment_maintenance_viewp_P(Fragment_MaintenanceVideo_viewPage mview, Fragment_maintenance_viewp_M mModel) {
        super(mview, mModel);
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
