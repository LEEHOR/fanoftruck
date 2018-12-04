package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_services_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_services_M extends BaseModel<Fragment_services_C.Presenter> implements Fragment_services_C.Model {
    @Inject
    public Fragment_services_M() {
        super();
    }



    @Override
    public void startLocation() {

    }
}
