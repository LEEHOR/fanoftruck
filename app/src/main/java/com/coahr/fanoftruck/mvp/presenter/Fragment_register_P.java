package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_register_C;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.model.Fragment_register_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_register;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:29
 */
public class Fragment_register_P extends BasePresenter<Fragment_register_C.View,Fragment_register_C.Model> implements Fragment_register_C.Presenter {
    @Inject
    public Fragment_register_P(Fragment_register mview, Fragment_register_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void Register(Map<String, String> map) {
        if (mModle != null) {
            mModle.Register(map);
        }
    }

    @Override
    public void RegisterSuccess(RegisterBean registerBean) {
        if (getView() != null) {
            getView().RegisterSuccess(registerBean);
        }
    }

    @Override
    public void RegisterFailure(String failure) {
        if (getView() != null) {
            getView().RegisterFailure(failure);
        }
    }

    @Override
    public void getVerifyCode(Map<String, String> map) {
        if (mModle != null) {
            mModle.getVerifyCode(map);
        }
    }

    @Override
    public void getVerifyCodeSuccess(VerifyCode verifyCode) {
        if (getView() != null) {
            getView().getVerifyCodeSuccess(verifyCode);
        }
    }

    @Override
    public void getVerifyCodeFailure(String Failure) {
        if (getView() != null) {
            getView().getVerifyCodeFailure(Failure);
        }
    }
}
