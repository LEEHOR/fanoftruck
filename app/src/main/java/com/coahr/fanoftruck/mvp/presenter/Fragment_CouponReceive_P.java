package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_CouponReceive_C;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponDown;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponList;
import com.coahr.fanoftruck.mvp.model.Fragment_CouponReceive_M;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponReceive;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:34
 */
public class Fragment_CouponReceive_P extends BasePresenter<Fragment_CouponReceive_C.View,Fragment_CouponReceive_C.Model> implements Fragment_CouponReceive_C.Presenter {
    @Inject
    public Fragment_CouponReceive_P(Fragment_CouponReceive mview, Fragment_CouponReceive_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void getCouponList(Map<String,String> map) {
        if (mModle != null) {
            mModle.getCouponList(map);
        }
    }

    @Override
    public void getCouponListSuccess(GetCouponList getCoupon) {
        if (getView() != null) {
            getView().getCouponListSuccess(getCoupon);
        }
    }

    @Override
    public void getCouponListFailure(String failure) {
        if (getView() != null) {
            getView().getCouponListFailure(failure);
        }
    }

    @Override
    public void getReceiveCoupon(Map<String ,String> map) {
        if (mModle != null) {
            mModle.getReceiveCoupon(map);
        }
    }

    @Override
    public void getReceiveCouponSuccess(GetCouponDown getCouponDown) {
        if (getView() != null) {
            getView().getReceiveCouponSuccess(getCouponDown);
        }
    }

    @Override
    public void getReceiveCouponFailure(String failure) {
        if (getView() != null) {
            getView().getReceiveCouponFailure(failure);
        }
    }

}
