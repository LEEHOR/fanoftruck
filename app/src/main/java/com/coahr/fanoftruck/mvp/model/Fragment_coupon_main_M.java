package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_coupon_main_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.CouponBean;
import com.coahr.fanoftruck.mvp.model.Bean.Coupon_Used;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:32
 */
public class Fragment_coupon_main_M extends BaseModel<Fragment_coupon_main_C.Presenter> implements Fragment_coupon_main_C.Model{
    @Inject
    public Fragment_coupon_main_M() {
        super();
    }
    @Override
    public void getCouponOrderList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CouponBean>(getApiService().get_coupon_list(map.get("token"), map.get("page"), map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CouponBean>() {
                    @Override
                    public void _onNext(CouponBean couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().onGetCouponOrderListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().onGetCouponOrderListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }

    /**
     * 选择优惠券
     * @param map
     */
    @Override
    public void onUsedCoupon(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Coupon_Used>(getApiService().used_coupon(map.get("coupon_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<Coupon_Used>() {
                    @Override
                    public void _onNext(Coupon_Used coupon_used) {
                        if (getPresenter() != null) {
                            if (coupon_used.getCode()!=0) {
                                getPresenter().onUsedCouponFailure(coupon_used.getMsg());
                            }else {
                                getPresenter().onUsedCouponSuccess(coupon_used);
                            }
                        }
                    }
                }));
    }


    /**
     * 加载更多
     * @param map
     */
    @Override
    public void LoadMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CouponBean>(getApiService().get_coupon_list(map.get("token"), map.get("page"), map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CouponBean>() {
                    @Override
                    public void _onNext(CouponBean couponBean) {
                        if (getPresenter() != null) {
                            if (couponBean.getCode()!=0) {
                                getPresenter().onGetCouponOrderListFailure(couponBean.getMsg());
                            }else {
                                getPresenter().onGetCouponOrderListSuccess(couponBean);
                            }
                        }
                    }
                }));
    }
}
