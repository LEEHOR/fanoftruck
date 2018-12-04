package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_recommendCar_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_recommendCar_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:09
 * 推荐购车
 */
public class Fragment_RecommendCar extends BaseFragment<Fragment_recommendCar_C.Presenter> implements Fragment_recommendCar_C.View {

    @Inject
    Fragment_recommendCar_P p;

    public static  Fragment_RecommendCar newInstance() {
        return new Fragment_RecommendCar();
    }

    @Override
    public Fragment_recommendCar_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_recommentcar;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
