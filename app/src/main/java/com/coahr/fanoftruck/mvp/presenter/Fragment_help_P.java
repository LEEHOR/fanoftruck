package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_help_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.model.Fragment_help_M;
import com.coahr.fanoftruck.mvp.model.Fragment_home_M;
import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_help_P extends BasePresenter<Fragment_help_C.View,Fragment_help_C.Model> implements Fragment_help_C.Presenter {

    @Inject
    public Fragment_help_P(Fragment_help mview, Fragment_help_M mModel) {
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
