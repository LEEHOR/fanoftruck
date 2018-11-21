package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.model.Fragment_myself_M;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:33
 */
public class Fragment_myself_P extends BasePresenter<Fragment_myself_C.View,Fragment_myself_C.Model> implements Fragment_myself_C.Presenter {
    @Inject
    public Fragment_myself_P(Fragment_Myself mview, Fragment_myself_M mModel) {
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
