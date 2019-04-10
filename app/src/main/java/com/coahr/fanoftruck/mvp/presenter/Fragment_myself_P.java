package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.model.Bean.BindWXData;
import com.coahr.fanoftruck.mvp.model.Bean.MyselfData;
import com.coahr.fanoftruck.mvp.model.Bean.UnsetWXData;
import com.coahr.fanoftruck.mvp.model.Fragment_myself_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:33
 */
public class Fragment_myself_P extends BasePresenter<Fragment_myself_C.View,Fragment_myself_C.Model> implements Fragment_myself_C.Presenter {
    @Inject
    public Fragment_myself_P(Fragment_Myself mview, Fragment_myself_M mModel) {
        super(mview, mModel);
    }
    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void bindWX(Map<String, String> map) {
        if (mModle != null) {
            mModle.bindWX(map);
        }
    }

    @Override
    public void unsetWX(Map<String, String> map) {
        if (mModle != null) {
            mModle.unsetWX(map);
        }
    }

    @Override
    public void getMySelfData(Map<String,String> map) {
        if (mModle != null) {
            mModle.getMySelfData(map);
        }
    }

    @Override
    public void unsetWXSuccess(UnsetWXData unsetWXData) {
        if (getView() != null) {
            getView().unsetWXSuccess(unsetWXData);
        }
    }

    @Override
    public void unsetWXFailure(String failure) {
        if (getView() != null) {
            getView().unsetWXFailure(failure);
        }
    }

    @Override
    public void bindWXSuccess(BindWXData bindWXData) {
        if (getView() != null) {
            getView().bindWXSuccess(bindWXData);
        }
    }

    @Override
    public void bindWXFailure(String failure) {
        if (getView() != null) {
            getView().bindWXFailure(failure);
        }
    }

    @Override
    public void getMySelfDataSuccess(MyselfData myselfData) {
        if (getView() != null) {
            getView().getMySelfDataSuccess(myselfData);
        }
    }

    @Override
    public void getMySelfDataFailure(String failure) {
        if (getView() != null) {
            getView().getMySelfDataFailure(failure);
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
