package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:23
 */
public class Fragment_addCar_M extends BaseModel<Fragment_addCar_C.Presenter> implements Fragment_addCar_C.Model {
    @Inject
    public Fragment_addCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }
}
