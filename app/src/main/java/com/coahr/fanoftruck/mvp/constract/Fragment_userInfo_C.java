package com.coahr.fanoftruck.mvp.constract;

import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.Center_Initial_Data;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Bean.LoginOutBean;
import com.coahr.fanoftruck.mvp.model.Bean.Save_Identity_Info;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_userInfo_C {

    interface View extends BaseContract.View {

        void getCenter_Initial_DataSuccess(Center_Initial_Data center_initial_data);

        void getCenter_Initial_DataFailure(String  failure);

        void Save_Identity_InfoSuccess(Save_Identity_Info save_identity_info);

        void Save_Identity_InfoFailure(String failure);

        void  LoginOutSuccess(LoginOutBean loginOutBean);

        void LoginOutFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCenter_Initial_Data(Map<String,String> map);

        void getCenter_Initial_DataSuccess(Center_Initial_Data center_initial_data);

        void getCenter_Initial_DataFailure(String  failure);

        void Save_Identity_Info(Map<String,String> map);

        void Save_Identity_InfoSuccess(Save_Identity_Info save_identity_info);

        void Save_Identity_InfoFailure(String failure);

        void LoginOut(Map<String,String> map);

        void  LoginOutSuccess(LoginOutBean loginOutBean);

        void LoginOutFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void getCenter_Initial_Data(Map<String,String> map);

        void Save_Identity_Info(Map<String,String> map);

        void LoginOut(Map<String,String> map);
    }
}
