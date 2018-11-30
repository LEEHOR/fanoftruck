package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;
import com.coahr.fanoftruck.mvp.model.Fragment_addCar_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_addCar;

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
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
