package com.coahr.fanoftruck.mvp.view.Myself;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_addCar_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_addCar_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:15
 */
public class Fragment_addCar extends BaseFragment<Fragment_addCar_C.Presenter> implements Fragment_addCar_C.View {
   @Inject
    Fragment_addCar_P p;

    public static Fragment_addCar newInstance() {
        return new Fragment_addCar();
    }

    @Override
    public Fragment_addCar_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_addcar;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
