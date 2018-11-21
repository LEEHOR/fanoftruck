package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_shopping_C;
import com.coahr.fanoftruck.mvp.model.Fragment_shopping_M;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:31
 */
public class Fragment_shopping_P extends BasePresenter<Fragment_shopping_C.View,Fragment_shopping_C.Model> implements Fragment_shopping_C.Presenter {
    @Inject
    public Fragment_shopping_P(Fragment_Shopping mview, Fragment_shopping_M mModel) {
        super(mview, mModel);
    }
    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        if (getView() != null) {
            getView().onLocationFailure(failure);
        }
    }
}
