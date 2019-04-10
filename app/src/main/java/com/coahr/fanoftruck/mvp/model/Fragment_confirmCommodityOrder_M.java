package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_confirmCommodityOrder_C;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.Confirm_order;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;
import com.socks.library.KLog;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 18:23
 */
public class Fragment_confirmCommodityOrder_M extends BaseModel<Fragment_confirmCommodityOrder_C.Presenter> implements Fragment_confirmCommodityOrder_C.Model {
    @Inject
    public Fragment_confirmCommodityOrder_M() {
        super();
    }


    @Override
    public void getCommodityOrderList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Confirm_order>(getApiService().Confirm_order(map.get("token"),map.get("commodity"),map.get("ua_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<Confirm_order>() {
                    @Override
                    public void _onNext(Confirm_order confirmOrder) {
                        if (getPresenter() != null) {
                            if (confirmOrder.getCode()==0) {
                                getPresenter().getCommodityOrderSuccess(confirmOrder);
                            }else {
                                getPresenter().getCommodityOrderFailure(confirmOrder.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void saveCommodityOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmOrderBean>(getApiService().payCommodityOrder(map)))
                .subscribeWith(new SimpleDisposableSubscriber<ConfirmOrderBean>() {
                    @Override
                    public void _onNext(ConfirmOrderBean bean) {
                        KLog.e("lizhiguo", bean.toString());
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onSaveCommodityOrderSuccess(bean);
                            }else {
                                getPresenter().onSaveCommodityOrderFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
