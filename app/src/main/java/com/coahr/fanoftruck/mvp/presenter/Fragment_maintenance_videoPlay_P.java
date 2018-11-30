package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.model.Fragment_maintenance_videoPlay_M;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_videoPlay;

import java.util.Map;

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
    public void getVideo_dz(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideo_dz(map);
        }
    }

    @Override
    public void getVideo_dzSuccess(MaintenanceVideo_dz maintenanceVideo_dz) {
        if (getView() != null) {
            getView().getVideo_dzSuccess(maintenanceVideo_dz);
        }
    }

    @Override
    public void getVideo_dzFailure(String failure) {
        if (getView() != null) {
            getView().getVideo_dzFailure(failure);
        }
    }
}
