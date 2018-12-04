package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentBean;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentDefaultBean;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;

import java.util.Map;

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

    @Override
    public void saveOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<AppointmentBean>(getApiService().appointment_order(map.get("token"),map.get("car_frameno"),
                map.get("car_no"),map.get("username"),map.get("telephone"),map.get("appoint_time"),map.get("service_item"),map.get("description"),map.get("s_type"))))
                .subscribeWith(new SimpleDisposableSubscriber<AppointmentBean>() {
                    @Override
                    public void _onNext(AppointmentBean appointmentBean) {
                        if (getPresenter() != null) {
                            if (appointmentBean.getCode()==0) {
                                getPresenter().saveOrderSuccess(appointmentBean);
                            }else {
                                getPresenter().saveOrderFailure(appointmentBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getAppointmentDefault(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<AppointmentDefaultBean>(getApiService().appointmentDefault(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<AppointmentDefaultBean>() {
                    @Override
                    public void _onNext(AppointmentDefaultBean appointmentDefaultBean) {
                        if (getPresenter() != null) {
                            if (appointmentDefaultBean.getCode()==0) {
                                getPresenter().getAppointmentDefaultSuccess(appointmentDefaultBean);
                            }else {
                                getPresenter().getAppointmentDefaultFailure(appointmentDefaultBean.getMsg());
                            }
                        }
                    }
                }));
    }

}
