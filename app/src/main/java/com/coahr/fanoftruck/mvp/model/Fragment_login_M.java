package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_login_C;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 19:23
 */
public class Fragment_login_M extends BaseModel<Fragment_login_C.Presenter> implements Fragment_login_C.Model {

   @Inject
    public Fragment_login_M() {
       super();
    }

    @Override
    public void Login(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LoginBean>(getApiService().login(map.get("phone"),map.get("pwd"))))
                .subscribeWith(new SimpleDisposableSubscriber<LoginBean>() {
                    @Override
                    public void _onNext(LoginBean loginBean) {
                        if (getPresenter() != null) {
                            if (loginBean.getCode()==0) {
                                getPresenter().LoginSuccess(loginBean);
                            } else {
                                getPresenter().LoginFailure(loginBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
