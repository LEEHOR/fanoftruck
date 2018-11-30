package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 16:58
 */
public class Fragment_appointment_M extends BaseModel<Fragment_appointment_C.Presenter> implements Fragment_appointment_C.Model {

    @Inject
    public Fragment_appointment_M() {
        super();
    }

    @Override
    public void startLocation() {

    }
}
