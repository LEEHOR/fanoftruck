package com.coahr.fanoftruck.mvp.constract;

import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_forgetPass_C {

    interface View extends BaseContract.View {

        void forgetPassSuccess(ForgetPass forgetPass);

        void forgetPassFailure(String failure);


        void getVerifyCodeSuccess(VerifyCode verifyCode);

        void getVerifyCodeFailure(String Failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void forgetPass(Map<String, String> map);

        void forgetPassSuccess(ForgetPass forgetPass);

        void forgetPassFailure(String failure);

        void getVerifyCode(Map<String, String> map);

        void getVerifyCodeSuccess(VerifyCode verifyCode);

        void getVerifyCodeFailure(String Failure);

    }

    interface Model extends BaseContract.Model {

        void forgetPass(Map<String, String> map);

        void getVerifyCode(Map<String, String> map);
    }
}
