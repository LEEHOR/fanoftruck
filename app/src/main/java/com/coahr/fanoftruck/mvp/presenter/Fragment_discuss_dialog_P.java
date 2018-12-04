package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_discuss_dialog_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;
import com.coahr.fanoftruck.mvp.model.Fragment_discuss_dialog_M;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_discuss;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_discuss_dialog_P extends BasePresenter<Fragment_discuss_dialog_C.View,Fragment_discuss_dialog_C.Model> implements Fragment_discuss_dialog_C.Presenter {

    @Inject
    public Fragment_discuss_dialog_P(Fragment_discuss mview, Fragment_discuss_dialog_M mModel) {
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
    public void getVideoDiscussList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideoDiscussList(map);
        }
    }

    @Override
    public void getVideoDiscussSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {
        if (getView() != null) {
            getView().getVideoDiscussSuccess(maintenanceVideoDiscussList);
        }
    }

    @Override
    public void getVideoDiscussFailure(String failure) {
        if (getView() != null) {
            getView().getVideoDiscussFailure(failure);
        }
    }

    @Override
    public void getVideoDiscussMoreList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVideoDiscussMoreList(map);
        }
    }

    @Override
    public void getVideoDiscussMoreSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {
        if (getView() != null) {
            getView().getVideoDiscussMoreSuccess(maintenanceVideoDiscussList);
        }
    }

    @Override
    public void getVideoDiscussMoreFailure(String failure) {
        if (getView() != null) {
            getView().getVideoDiscussMoreFailure(failure);
        }
    }

    @Override
    public void getAddDiscuss(Map<String, String> map) {
        if (mModle != null) {
            mModle.getAddDiscuss(map);
        }
    }

    @Override
    public void getAddDiscussSuccess(AddDiscuss addDiscuss) {
        if (getView() != null) {
            getView().getAddDiscussSuccess(addDiscuss);
        }
    }

    @Override
    public void getAddDiscussFailure(String failure) {
        if (getView() != null) {
            getView().getAddDiscussFailure(failure);
        }
    }
}
