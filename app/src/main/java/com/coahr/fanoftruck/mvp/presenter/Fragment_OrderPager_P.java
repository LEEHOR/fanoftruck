package com.coahr.fanoftruck.mvp.presenter;



import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_MyOrder_Pager_C;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderBean;
import com.coahr.fanoftruck.mvp.model.Fragment_OrderPager_M;
import com.coahr.fanoftruck.mvp.view.MyOrder.Fragment_Order_pager;

import java.util.Map;

import javax.inject.Inject;

/**
 * Author： hengzwd on 2018/6/22.
 * Email：hengzwdhengzwd@qq.com
 */
public class Fragment_OrderPager_P extends BasePresenter<Fragment_MyOrder_Pager_C.View, Fragment_MyOrder_Pager_C.Model> implements Fragment_MyOrder_Pager_C.Presenter {
    @Inject
    public Fragment_OrderPager_P(Fragment_Order_pager mview, Fragment_OrderPager_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void showError(Throwable t) {

    }

    @Override
    public void getCommodityOrderList(Map<String, Object> map) {
        if (mModle != null) {
            mModle.getCommodityOrderList(map);
        }
    }

    @Override
    public void onGetCommodityOrderListSuccess(CommodityOrderBean bean) {
        if (getView() != null) {
            getView().onGetCommodityOrderListSuccess(bean);
        }
    }

    @Override
    public void onGetCommodityOrderListFailure(String failure) {
        if (getView() != null) {
            getView().onGetCommodityOrderListFailure(failure);
        }
    }

    @Override
    public void loadMore(Map<String, Object> map) {
        if (mModle != null) {
            mModle.loadMore(map);
        }
    }

    @Override
    public void loadMoreSuccess(CommodityOrderBean bean) {
        if (getView() != null) {
            getView().loadMoreSuccess(bean);
        }
    }

    @Override
    public void loadMoreFailure(String failure) {
        if (getView() != null) {
            getView().loadMoreFailure(failure);
        }
    }
}
