package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.CarDefaultBean;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_myCar_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);


        void getCarListSuccess(CarListBean carListBean);

        void getCarListFailure(String failure);


        void setCarDefaultSuccess(CarDefaultBean carDefaultSuccess);

        void setCarDefaultFailure(String failure);


        void deleteCarSuccess(CarDefaultBean carDefaultBean);

        void deleteCarFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getCarList(Map<String, String> map);

        void getCarListSuccess(CarListBean carListBean);

        void getCarListFailure(String failure);

        void setCarDefault(Map<String, String> map);

        void setCarDefaultSuccess(CarDefaultBean carDefaultSuccess);

        void setCarDefaultFailure(String failure);

        void deleteCar(Map<String, String> map);

        void deleteCarSuccess(CarDefaultBean carDefaultBean);

        void deleteCarFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getCarList(Map<String, String> map);

        void setCarDefault(Map<String, String> map);

        void deleteCar(Map<String, String> map);
    }
}
