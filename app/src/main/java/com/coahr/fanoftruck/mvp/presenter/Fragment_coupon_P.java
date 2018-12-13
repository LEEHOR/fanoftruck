package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_coupon_main_C;
import com.coahr.fanoftruck.mvp.model.Bean.CouponBean;
import com.coahr.fanoftruck.mvp.model.Bean.Coupon_Used;
import com.coahr.fanoftruck.mvp.model.Fragment_coupon_main_M;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_coupon_main;

import java.util.Map;

import javax.inject.Inject;

public class Fragment_coupon_P extends BasePresenter<Fragment_coupon_main_C.View,Fragment_coupon_main_C.Model> implements Fragment_coupon_main_C.Presenter {

   @Inject
    public Fragment_coupon_P(Fragment_coupon_main mview, Fragment_coupon_main_M mModel) {
        super(mview, mModel);
    }


    @Override
    public void getCouponOrderList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCouponOrderList(map);
        }
    }

    @Override
    public void onGetCouponOrderListSuccess(CouponBean couponBean) {
        if (getView() != null) {
            getView().onGetCouponOrderListSuccess(couponBean);
        }
    }

    @Override
    public void onGetCouponOrderListFailure(String failure) {
        if (getView() != null) {
            getView().onGetCouponOrderListFailure(failure);
        }
    }

    @Override
    public void onUsedCoupon(Map<String, String> map) {
        if (mModle != null) {
            mModle.onUsedCoupon(map);
        }
    }

    @Override
    public void onUsedCouponSuccess(Coupon_Used coupon_used) {
        if (getView() != null) {
            getView().onUsedCouponSuccess(coupon_used);
        }
    }

    @Override
    public void onUsedCouponFailure(String failure) {
        if (getView() != null) {
            getView().onUsedCouponFailure(failure);
        }
    }

    @Override
    public void LoadMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.LoadMore(map);
        }
    }

    @Override
    public void LoadMoreSuccess(CouponBean couponBean) {
        if (getView() != null) {
            getView().LoadMoreSuccess(couponBean);
        }
    }

    @Override
    public void LoadMoreFailure(String failure) {
        if (getView() != null) {
            getView().LoadMoreFailure(failure);
        }
    }
}
