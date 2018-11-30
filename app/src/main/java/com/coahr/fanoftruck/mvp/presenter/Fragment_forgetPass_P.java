package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_forgetPass_C;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.model.Fragment_forgetPass_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_forgetPass;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 15:47
 */
public class Fragment_forgetPass_P extends BasePresenter<Fragment_forgetPass_C.View,Fragment_forgetPass_C.Model> implements Fragment_forgetPass_C.Presenter {
    @Inject
    public Fragment_forgetPass_P(Fragment_forgetPass mview, Fragment_forgetPass_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void forgetPass(Map<String, String> map) {
        if (mModle != null) {
            mModle.forgetPass(map);
        }

    }

    @Override
    public void forgetPassSuccess(ForgetPass forgetPass) {
        if (getView() != null) {
            getView().forgetPassSuccess(forgetPass);
        }
    }

    @Override
    public void forgetPassFailure(String failure) {
        if (getView() != null) {
            getView().forgetPassFailure(failure);
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
