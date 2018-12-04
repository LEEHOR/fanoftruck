package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentBean;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentDefaultBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_appointment_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void saveOrderSuccess(AppointmentBean appointmentBean);

        void saveOrderFailure(String failure);

        void getAppointmentDefaultSuccess(AppointmentDefaultBean appointmentDefaultBean);

        void getAppointmentDefaultFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void saveOrder(Map<String,String> map);

        void saveOrderSuccess(AppointmentBean appointmentBean);

        void saveOrderFailure(String failure);

        void getAppointmentDefault(Map<String,String> map);

        void getAppointmentDefaultSuccess(AppointmentDefaultBean appointmentDefaultBean);

        void getAppointmentDefaultFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void saveOrder(Map<String,String> map);

        void getAppointmentDefault(Map<String,String> map);
    }
}
