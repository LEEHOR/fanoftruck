package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.model.Bean.Center_Initial_Data;
import com.coahr.fanoftruck.mvp.model.Bean.LoginOutBean;
import com.coahr.fanoftruck.mvp.model.Bean.Save_Identity_Info;
import com.coahr.fanoftruck.mvp.model.Fragment_UserInfo_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:14
 */
public class Fragment_UserInfo_P extends BasePresenter<Fragment_userInfo_C.View,Fragment_userInfo_C.Model> implements Fragment_userInfo_C.Presenter {
    @Inject
    public Fragment_UserInfo_P(Fragment_myUerInfo mview, Fragment_UserInfo_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCenter_Initial_Data(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCenter_Initial_Data(map);
        }
    }

    @Override
    public void getCenter_Initial_DataSuccess(Center_Initial_Data center_initial_data) {
        if (getView() != null) {
            getView().getCenter_Initial_DataSuccess(center_initial_data);
        }
    }

    @Override
    public void getCenter_Initial_DataFailure(String failure) {
        if (getView() != null) {
            getView().getCenter_Initial_DataFailure(failure);
        }
    }

    @Override
    public void Save_Identity_Info(Map<String, String> map) {
        if (mModle != null) {
            mModle.Save_Identity_Info(map);
        }
    }

    @Override
    public void Save_Identity_InfoSuccess(Save_Identity_Info save_identity_info) {
        if (getView() != null) {
            getView().Save_Identity_InfoSuccess(save_identity_info);
        }
    }

    @Override
    public void Save_Identity_InfoFailure(String failure) {
        if (getView() != null) {
            getView().Save_Identity_InfoFailure(failure);
        }
    }

    @Override
    public void LoginOut(Map<String, String> map) {
        if (mModle != null) {
            mModle.LoginOut(map);
        }
    }

    @Override
    public void LoginOutSuccess(LoginOutBean loginOutBean) {
        if (getView() != null) {
            getView().LoginOutSuccess(loginOutBean);
        }
    }

    @Override
    public void LoginOutFailure(String failure) {
        if (getView() != null) {
            getView().LoginOutFailure(failure);
        }
    }
}
