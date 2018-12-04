package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;
import com.coahr.fanoftruck.mvp.model.Fragment_addCar_M;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_addCar;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:24
 */
public class Fragment_addCar_P extends BasePresenter<Fragment_addCar_C.View,Fragment_addCar_C.Model> implements Fragment_addCar_C.Presenter {
    @Inject
    public Fragment_addCar_P(Fragment_addCar mview, Fragment_addCar_M mModel) {
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
    public void OnSaveUserCar(Map<String, String> map) {
        if (mModle != null) {
            mModle.OnSaveUserCar(map);
        }
    }

    @Override
    public void OnSaveUserCarSuccess(SaveUserCar saveUserCar) {
        if (getView() != null) {
            getView().OnSaveUserCarSuccess(saveUserCar);
        }
    }

    @Override
    public void OnSaveUserCarFailure(String failure) {
        if (getView() != null) {
            getView().OnSaveUserCarFailure(failure);
        }
    }

}
