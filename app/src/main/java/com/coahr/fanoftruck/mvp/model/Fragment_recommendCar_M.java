package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_recommendCar_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:44
 */
public class Fragment_recommendCar_M extends BaseModel<Fragment_recommendCar_C.Presenter> implements Fragment_recommendCar_C.Model {
    @Inject
    public Fragment_recommendCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }
}
