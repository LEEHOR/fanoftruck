package com.coahr.fanoftruck.mvp.constract;

import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_login_C {

    interface View extends BaseContract.View {

        void LoginSuccess(LoginBean loginBean);

        void LoginFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void Login(Map<String, String> map);

        void LoginSuccess(LoginBean loginBean);

        void LoginFailure(String failure);

    }

    interface Model extends BaseContract.Model {

        void Login(Map<String, String> map);

    }
}
