package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.NeedToPayContract;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.ResultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 */
public class NeedToPayModel extends BaseModel<NeedToPayContract.Presenter> implements NeedToPayContract.Model {


    @Inject
    public NeedToPayModel(){
        super();
    }

    @Override
    public void getCommodityOrderDetail(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderDetailBean>(getApiService().getCommodityOrderDetail(map.get("token"),map.get("order_id"))))
            .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderDetailBean>() {
                @Override
                public void _onNext(CommodityOrderDetailBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onGetCommodityOrderDetialSuccess(bean);
                        }else {
                            getPresenter().onGetCommodityOrderDetailFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void cancelCommodityOrder(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ResultBean>(getApiService().cancelCommodityOrder(map.get("token"),map.get("order_id"),map.get("reason"))))
                .subscribeWith(new SimpleDisposableSubscriber<ResultBean>() {
                    @Override
                    public void _onNext(ResultBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onCancelOrderSuccess(bean);
                            }else {
                                getPresenter().onCancelOrderFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void quickPay(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmOrderBean>(getApiService().payImmediatelyOrder(map)))
                .subscribeWith(new SimpleDisposableSubscriber<ConfirmOrderBean>() {
                    @Override
                    public void _onNext(ConfirmOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().onQuickPaySuccess(bean);
                            }else {
                                getPresenter().onQuickPayFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
