package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_confirmCommodityOrder_C;
import com.coahr.fanoftruck.mvp.model.Bean.Confirm_order;
import com.coahr.fanoftruck.mvp.model.Fragment_confirmCommodityOrder_M;
import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.Fragment_confirmCommodityOrder;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:34
 */
public class Fragment_confirmCommodityOrder_P extends BasePresenter<Fragment_confirmCommodityOrder_C.View,Fragment_confirmCommodityOrder_C.Model> implements Fragment_confirmCommodityOrder_C.Presenter {
    @Inject
    public Fragment_confirmCommodityOrder_P(Fragment_confirmCommodityOrder mview, Fragment_confirmCommodityOrder_M mModel) {
        super(mview, mModel);
    }


    @Override
    public void getCommodityOrderList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCommodityOrderList(map);
        }
    }

    @Override
    public void getCommodityOrderSuccess(Confirm_order confirmOrder) {
        if (getView() != null) {
            getView().getCommodityOrderSuccess(confirmOrder);
        }
    }

    @Override
    public void getCommodityOrderFailure(String failure) {
        if (getView() != null) {
            getView().getCommodityOrderFailure(failure);
        }
    }
}
