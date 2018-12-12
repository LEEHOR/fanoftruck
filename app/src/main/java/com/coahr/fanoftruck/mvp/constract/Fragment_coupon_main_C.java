package com.coahr.fanoftruck.mvp.constract;


import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.CouponBean;
import com.coahr.fanoftruck.mvp.model.Bean.Coupon_Used;

import java.util.Map;

public interface Fragment_coupon_main_C {

    interface View extends BaseContract.View{

        void onGetCouponOrderListSuccess(CouponBean couponBean);

        void onGetCouponOrderListFailure(String failure);

        void onUsedCouponSuccess(Coupon_Used coupon_used);

        void onUsedCouponFailure(String failure);

        void LoadMoreSuccess(CouponBean coupon_used);

        void LoadMoreFailure(String failure);


    }

    interface  Presenter extends BaseContract.Presenter{

        void getCouponOrderList(Map<String, String> map);

        void onGetCouponOrderListSuccess(CouponBean couponBean);

        void onGetCouponOrderListFailure(String failure);

        void onUsedCoupon(Map<String, String> map);

        void onUsedCouponSuccess(Coupon_Used coupon_used);

        void onUsedCouponFailure(String failure);

        void LoadMore(Map<String, String> map);

        void LoadMoreSuccess(CouponBean couponBean);

        void LoadMoreFailure(String failure);


    }

    interface  Model extends BaseContract.Model{

        void getCouponOrderList(Map<String, String> map);

        void onUsedCoupon(Map<String, String> map);

        void LoadMore(Map<String, String> map);
    }

}
