package com.coahr.fanoftruck.mvp.view.Myself;

import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_register_C;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.presenter.Fragment_register_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:17
 * 注册
 */
public class Fragment_register extends BaseFragment<Fragment_register_C.Presenter> implements Fragment_register_C.View {


   @Inject
    Fragment_register_P p;
    @Override
    public Fragment_register_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return 0;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void RegisterSuccess(RegisterBean registerBean) {

    }

    @Override
    public void RegisterFailure(String failure) {

    }

    @Override
    public void getVerifyCodeSuccess(VerifyCode verifyCode) {

    }

    @Override
    public void getVerifyCodeFailure(String Failure) {

    }
}
