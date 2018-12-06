package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_recommendCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.Business_car;
import com.coahr.fanoftruck.mvp.model.Bean.SaveBusinessCarBean;
import com.coahr.fanoftruck.mvp.model.Bean.getBuyCarCode;
import com.coahr.fanoftruck.mvp.model.Fragment_recommendCar_M;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:45
 */
public class Fragment_recommendCar_P extends BasePresenter<Fragment_recommendCar_C.View,Fragment_recommendCar_C.Model> implements Fragment_recommendCar_C.Presenter {
    @Inject
    public Fragment_recommendCar_P(Fragment_RecommendCar mview, Fragment_recommendCar_M mModel) {
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
    public void getBusiness_Car(Map<String, String> map) {

        if (mModle != null) {
            mModle.getBusiness_Car(map);
        }
    }

    @Override
    public void getBusiness_CarSuccess(Business_car business_car) {
        if (getView() != null) {
            getView().getBusiness_CarSuccess(business_car);
        }
    }

    @Override
    public void getBusiness_CarFailure(String failure) {
        if (getView() != null) {
            getView().getBusiness_CarFailure(failure);
        }
    }

    @Override
    public void getCarCode(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCarCode(map);
        }
    }

    @Override
    public void getCarCodeSuccess(getBuyCarCode carCode) {
        if (getView() != null) {
            getView().getCarCodeSuccess(carCode);
        }
    }

    @Override
    public void getCarCodeFailure(String failure) {
        if (getView() != null) {
            getView().getCarCodeFailure(failure);
        }
    }

    @Override
    public void SaveBusinessCar(Map<String, String> map) {
        if (mModle != null) {
            mModle.SaveBusinessCar(map);
        }
    }

    @Override
    public void SaveBusinessCarSuccess(SaveBusinessCarBean saveBusinessCarBean) {
        if (getView() != null) {
            getView().SaveBusinessCarSuccess(saveBusinessCarBean);
        }
    }

    @Override
    public void SaveBusinessCarFailure(String failure) {
        if (getView() != null) {
            getView().SaveBusinessCarFailure(failure);
        }
    }
}
