package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;
import com.coahr.fanoftruck.mvp.model.Fragment_myCar_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myCar;

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
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
