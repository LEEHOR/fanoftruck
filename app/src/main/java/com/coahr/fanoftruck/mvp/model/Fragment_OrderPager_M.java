package com.coahr.fanoftruck.mvp.model;



import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_MyOrder_Pager_C;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class Fragment_OrderPager_M extends BaseModel<Fragment_MyOrder_Pager_C.Presenter> implements Fragment_MyOrder_Pager_C.Model {
    @Inject
    public Fragment_OrderPager_M(){
        super();
    }

    @Override
    public void getCommodityOrderList(Map<String, Object> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderBean>(getApiService().getCommodityOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
            .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderBean>() {
                @Override
                public void _onNext(CommodityOrderBean bean) {
                    if (getPresenter() != null) {
                        if (bean.getCode()==0) {
                            getPresenter().onGetCommodityOrderListSuccess(bean);
                        }else {
                            getPresenter().onGetCommodityOrderListFailure(bean.getMsg());
                        }
                    }
                }
            }));
    }

    @Override
    public void loadMore(Map<String, Object> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CommodityOrderBean>(getApiService().getCommodityOrderList(map.get("token"),map.get("order_status"),map.get("page"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<CommodityOrderBean>() {
                    @Override
                    public void _onNext(CommodityOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().loadMoreSuccess(bean);
                            }else {
                                getPresenter().loadMoreFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void payImmediately(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ConfirmOrderBean>(getApiService().payImmediatelyOrder(map)))
                .subscribeWith(new SimpleDisposableSubscriber<ConfirmOrderBean>() {
                    @Override
                    public void _onNext(ConfirmOrderBean bean) {
                        if (getPresenter() != null) {
                            if (bean.getCode()==0) {
                                getPresenter().payImmediatelySuccess(bean);
                            }else {
                                getPresenter().payImmediatelyFailure(bean.getMsg());
                            }
                        }
                    }
                }));
    }
}
