package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_MyOrder_Pager_C {

    interface View extends BaseContract.View {
        void onGetCommodityOrderListSuccess(CommodityOrderBean bean);

        void onGetCommodityOrderListFailure(String failure);

        void loadMoreSuccess(CommodityOrderBean bean);

        void loadMoreFailure(String failure);

        void payImmediatelySuccess(ConfirmOrderBean bean);

        void payImmediatelyFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void getCommodityOrderList(Map<String, Object> map);

        void onGetCommodityOrderListSuccess(CommodityOrderBean bean);

        void onGetCommodityOrderListFailure(String failure);

        void loadMore(Map<String, Object> map);

        void loadMoreSuccess(CommodityOrderBean bean);

        void loadMoreFailure(String failure);

        void payImmediately(Map<String, String> map);

        void payImmediatelySuccess(ConfirmOrderBean bean);

        void payImmediatelyFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getCommodityOrderList(Map<String, Object> map);

        void loadMore(Map<String, Object> map);

        void payImmediately(Map<String, String> map);
    }
}
