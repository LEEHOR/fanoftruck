package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.Confirm_order;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_confirmCommodityOrder_C {

    interface View extends BaseContract.View {

        void getCommodityOrderSuccess(Confirm_order confirmOrder);

        void getCommodityOrderFailure(String failure);
        void onSaveCommodityOrderSuccess(ConfirmOrderBean bean);

        void onSaveCommodityOrderFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void getCommodityOrderList(Map<String,String> map);

        void getCommodityOrderSuccess(Confirm_order confirmOrder);

        void getCommodityOrderFailure(String failure);

        void saveCommodityOrder(Map<String, String> map);

        void onSaveCommodityOrderSuccess(ConfirmOrderBean bean);

        void onSaveCommodityOrderFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void getCommodityOrderList(Map<String,String> map);
        void saveCommodityOrder(Map<String, String> map);
    }
}
