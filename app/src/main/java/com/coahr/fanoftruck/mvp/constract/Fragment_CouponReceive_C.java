package com.coahr.fanoftruck.mvp.constract;



import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponDown;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponList;

import java.util.Map;

public interface Fragment_CouponReceive_C {
    interface  View extends BaseContract.View{

        void getCouponListSuccess(GetCouponList getCoupon);

        void getCouponListFailure(String failure);

        void  getReceiveCouponSuccess(GetCouponDown getCouponDown);

        void  getReceiveCouponFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter{

        void getCouponList(Map<String, String> map);

        void getCouponListSuccess(GetCouponList getCoupon);

        void getCouponListFailure(String failure);




        void  getReceiveCoupon(Map<String, String> map);

        void  getReceiveCouponSuccess(GetCouponDown getCouponDown);

        void  getReceiveCouponFailure(String failure);

    }

    interface Model extends BaseContract.Model{

        void getCouponList(Map<String, String> map);

        void  getReceiveCoupon(Map<String, String> map);
    }
}
