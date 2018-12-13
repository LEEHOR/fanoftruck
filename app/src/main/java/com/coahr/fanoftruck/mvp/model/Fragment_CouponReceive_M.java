package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_CouponReceive_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponDown;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponList;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:32
 */
public class Fragment_CouponReceive_M extends BaseModel<Fragment_CouponReceive_C.Presenter> implements Fragment_CouponReceive_C.Model{
    @Inject
    public Fragment_CouponReceive_M() {
        super();
    }
    @Override
    public void getCouponList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<GetCouponList>(getApiService().get_coupon_all(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<GetCouponList>() {
                    @Override
                    public void _onNext(GetCouponList couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().getCouponListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().getCouponListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }



    @Override
    public void getReceiveCoupon(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<GetCouponDown>(getApiService().get_coupon_byself(map.get("token"),map.get("coupon_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<GetCouponDown>() {
                    @Override
                    public void _onNext(GetCouponDown getCouponDown) {
                        if (getPresenter() != null) {
                            if (getCouponDown.getCode()!=0) {
                                getPresenter().getReceiveCouponFailure(getCouponDown.getJdata().getJmsg());
                            }else {
                                getPresenter().getReceiveCouponSuccess(getCouponDown);
                            }
                        }
                    }
                }));
    }
}
