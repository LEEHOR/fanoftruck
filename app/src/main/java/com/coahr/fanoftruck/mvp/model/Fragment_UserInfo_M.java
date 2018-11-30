package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:12
 */
public class Fragment_UserInfo_M extends BaseModel<Fragment_userInfo_C.Presenter> implements Fragment_userInfo_C.Model{
   @Inject
    public Fragment_UserInfo_M() {
        super();
    }
}
