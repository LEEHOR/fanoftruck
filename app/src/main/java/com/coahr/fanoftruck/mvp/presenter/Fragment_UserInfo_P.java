package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.model.Fragment_UserInfo_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;

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
}
