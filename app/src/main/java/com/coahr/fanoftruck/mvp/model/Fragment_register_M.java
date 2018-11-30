package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_register_C;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:27
 */
public class Fragment_register_M extends BaseModel<Fragment_register_C.Presenter> implements Fragment_register_C.Model {

    @Inject
    public Fragment_register_M() {
        super();
    }

    @Override
    public void Register(Map<String, String> map) {

    }

    @Override
    public void getVerifyCode(Map<String, String> map) {

    }
}
