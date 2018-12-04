package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.model.Bean.CarDefaultBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;
import com.coahr.fanoftruck.mvp.model.Fragment_myCar_M;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_myCar;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:09
 */
public class Fragment_myCar_P extends BasePresenter<Fragment_myCar_C.View,Fragment_myCar_C.Model> implements Fragment_myCar_C.Presenter {
    @Inject
    public Fragment_myCar_P(Fragment_myCar mview, Fragment_myCar_M mModel) {
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
    public void getCarList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarList(map);
        }
    }

    @Override
    public void getCarListSuccess(CarListBean carListBean) {
        if (getView() != null) {
            getView().getCarListSuccess(carListBean);
        }
    }

    @Override
    public void getCarListFailure(String failure) {
        if (getView() != null) {
            getView().getCarListFailure(failure);
        }
    }

    @Override
    public void setCarDefault(Map<String, String> map) {
        if (mModle != null) {
            mModle.setCarDefault(map);
        }
    }

    @Override
    public void setCarDefaultSuccess(CarDefaultBean carDefaultSuccess) {
        if (getView() != null) {
            getView().setCarDefaultSuccess(carDefaultSuccess);
        }
    }

    @Override
    public void setCarDefaultFailure(String failure) {
        if (getView() != null) {
            getView().setCarDefaultFailure(failure);
        }
    }

    @Override
    public void deleteCar(Map<String, String> map) {
        if (mModle != null) {
            mModle.deleteCar(map);
        }
    }

    @Override
    public void deleteCarSuccess(CarDefaultBean carDefaultBean) {
        if (getView() != null) {
            getView().deleteCarSuccess(carDefaultBean);
        }
    }

    @Override
    public void deleteCarFailure(String failure) {
        if (getView() != null) {
            getView().deleteCarFailure(failure);
        }
    }
}
