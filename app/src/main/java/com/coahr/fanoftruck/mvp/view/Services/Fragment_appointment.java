package com.coahr.fanoftruck.mvp.view.Services;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_appointment_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_appointment_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 16:13
 */
public class Fragment_appointment extends BaseFragment<Fragment_appointment_C.Presenter> implements  Fragment_appointment_C.View {

@Inject
    Fragment_appointment_P p;

    public static  Fragment_appointment newInstance() {
        return new Fragment_appointment();
    }

    @Override
    public Fragment_appointment_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_appointment;
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
