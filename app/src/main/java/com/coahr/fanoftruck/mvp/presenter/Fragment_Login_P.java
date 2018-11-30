package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_login_C;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Fragment_login_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 19:41
 */
public class Fragment_Login_P extends BasePresenter<Fragment_login_C.View,Fragment_login_C.Model> implements Fragment_login_C.Presenter {
    @Inject
    public Fragment_Login_P(Fragment_login mview, Fragment_login_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void Login(Map<String, String> map) {
        if (mModle != null) {
            mModle.Login(map);
        }
    }

    @Override
    public void LoginSuccess(LoginBean loginBean) {
        if (getView() != null) {
            getView().LoginSuccess(loginBean);
        }
    }

    @Override
    public void LoginFailure(String failure) {
        if (getView() != null) {
            getView().LoginFailure(failure);
        }
    }
}
