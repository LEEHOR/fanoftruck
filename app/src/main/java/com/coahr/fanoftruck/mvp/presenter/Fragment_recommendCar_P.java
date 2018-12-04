package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_recommendCar_C;
import com.coahr.fanoftruck.mvp.model.Fragment_recommendCar_M;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;

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
}
