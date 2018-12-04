package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_child_M;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_child;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class Fragment_maintenance_child_P extends BasePresenter<Fragment_maintenance_child_C.View,Fragment_maintenance_child_C.Model> implements Fragment_maintenance_child_C.Presenter
{
    @Inject
    public Fragment_maintenance_child_P(Fragment_maintenance_child mview, Fragment_maintenance_child_M mModel) {
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

    @Override
    public void getVideoList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideoList(map);
        }
    }

    @Override
    public void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (getView() != null) {
            getView().getVideoListSuccess(maintenanceVideoList);
        }
    }

    @Override
    public void getVideoListFailure(String failure) {
        if (getView() != null) {
            getView().getVideoListFailure(failure);
        }
    }

    @Override
    public void getVideoMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideoMore(map);
        }
    }

    @Override
    public void getVideoMoreSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (getView() != null) {
            getView().getVideoMoreSuccess(maintenanceVideoList);
        }
    }

    @Override
    public void getVideoMoreFailure(String failure) {
        if (getView() != null) {
            getView().getVideoMoreFailure(failure);
        }
    }

    @Override
    public void getVideoSearch(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideoSearch(map);
        }
    }

    @Override
    public void getVideoSearchSuccess(MaintenanceVideoList maintenanceVideoList) {
        if (getView() != null) {
            getView().getVideoSearchSuccess(maintenanceVideoList);
        }
    }

    @Override
    public void getVideoSearchFailure(String failure) {
        if (getView() != null) {
            getView().getVideoSearchFailure(failure);
        }
    }
}
