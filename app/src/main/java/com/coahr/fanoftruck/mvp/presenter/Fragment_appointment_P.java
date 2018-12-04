package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentBean;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentDefaultBean;
import com.coahr.fanoftruck.mvp.model.Fragment_appointment_M;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;

import java.util.Map;

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
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void saveOrder(Map<String, String> map) {
        if (mModle != null) {
            mModle.saveOrder(map);
        }
    }

    @Override
    public void saveOrderSuccess(AppointmentBean appointmentBean) {
        if (getView() != null) {
            getView().saveOrderSuccess(appointmentBean);
        }
    }

    @Override
    public void saveOrderFailure(String failure) {
        if (getView() != null) {
            getView().saveOrderFailure(failure);
        }
    }

    @Override
    public void getAppointmentDefault(Map<String, String> map) {
        if (mModle != null) {
            mModle.getAppointmentDefault(map);
        }
    }

    @Override
    public void getAppointmentDefaultSuccess(AppointmentDefaultBean appointmentDefaultBean) {
        if (getView() != null) {
            getView().getAppointmentDefaultSuccess(appointmentDefaultBean);
        }
    }

    @Override
    public void getAppointmentDefaultFailure(String failure) {
        if (getView() != null) {
            getView().getAppointmentDefaultFailure(failure);
        }
    }
}
