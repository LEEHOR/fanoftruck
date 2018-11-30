package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:08
 */
public class Fragment_myCar_M extends BaseModel<Fragment_myCar_C.Presenter> implements Fragment_myCar_C.Model {
    @Inject
    public Fragment_myCar_M() {
        super();
    }

    @Override
    public void startLocation() {

    }
}
