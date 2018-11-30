package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_forgetPass_C;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 15:46
 */
public class Fragment_forgetPass_M extends BaseModel<Fragment_forgetPass_C.Presenter> implements Fragment_forgetPass_C.Model {
    @Inject
    public Fragment_forgetPass_M() {
        super();
    }
    @Override
    public void forgetPass(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ForgetPass>(getApiService().forgertPass(map.get("phone"),map.get("pwd"),map.get("verify_code"))))
                .subscribeWith(new SimpleDisposableSubscriber<ForgetPass>() {
                    @Override
                    public void _onNext(ForgetPass forgetPass) {
                        if (getPresenter() != null) {
                            if (forgetPass.getCode()==0) {
                                getPresenter().forgetPassSuccess(forgetPass);
                            } else {
                                getPresenter().forgetPassFailure(forgetPass.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getVerifyCode(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<VerifyCode>(getApiService().forgertPass_VerifyCode(map.get("phone"))))
                .subscribeWith(new SimpleDisposableSubscriber<VerifyCode>() {
                    @Override
                    public void _onNext(VerifyCode verifyCode) {
                        if (getPresenter() != null) {
                            if (verifyCode.getCode() == 0) {
                                getPresenter().getVerifyCodeSuccess(verifyCode);
                            } else {
                                getPresenter().getVerifyCodeFailure(verifyCode.getMsg());
                            }
                        }
                    }
                }));
    }
}
