package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.model.Fragment_appointment_M;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:00
 */
public class Fragment_appointment_P extends BasePresenter<Fragment_appointment_C.View,Fragment_appointment_C.Model> implements Fragment_appointment_C.Presenter
{
    @Inject
    public Fragment_appointment_P(Fragment_appointment mview, Fragment_appointment_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void startLocation() {


    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
