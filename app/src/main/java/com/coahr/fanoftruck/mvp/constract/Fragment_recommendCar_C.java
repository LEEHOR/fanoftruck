package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.Business_car;
import com.coahr.fanoftruck.mvp.model.Bean.SaveBusinessCarBean;
import com.coahr.fanoftruck.mvp.model.Bean.getBuyCarCode;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_recommendCar_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getBusiness_CarSuccess(Business_car business_car);

        void getBusiness_CarFailure(String failure);

        void getCarCodeSuccess(getBuyCarCode carCode);

        void getCarCodeFailure(String failure);

        void SaveBusinessCarSuccess(SaveBusinessCarBean saveBusinessCarBean);

        void SaveBusinessCarFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getBusiness_Car(Map<String,String> map);

        void getBusiness_CarSuccess(Business_car business_car);

        void getBusiness_CarFailure(String failure);

        void getCarCode(Map<String,String> map);

        void getCarCodeSuccess(getBuyCarCode carCode);

        void getCarCodeFailure(String failure);


        void SaveBusinessCar(Map<String,String> map);

        void SaveBusinessCarSuccess(SaveBusinessCarBean saveBusinessCarBean);

        void SaveBusinessCarFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getBusiness_Car(Map<String,String> map);

        void getCarCode(Map<String,String> map);

        void SaveBusinessCar(Map<String,String> map);
    }
}
