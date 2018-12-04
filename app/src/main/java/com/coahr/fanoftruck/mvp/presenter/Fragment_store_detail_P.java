package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_detail_C;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;
import com.coahr.fanoftruck.mvp.model.Fragment_store_detail_M;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:50
 */
public class Fragment_store_detail_P extends BasePresenter<Fragment_store_detail_C.View,Fragment_store_detail_C.Model> implements Fragment_store_detail_C.Presenter
{
    @Inject
    public Fragment_store_detail_P(Fragment_store_detail mview, Fragment_store_detail_M mModel) {
       super(mview,mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        if (getView() != null) {
            getView().onLocationFailure(failure);
        }
    }

    @Override
    public void getStoreDetail(Map<String, String> map) {
        if (mModle != null) {
            mModle.getStoreDetail(map);
        }
    }

    @Override
    public void getStoreDetailSuccess(StoreDetailBean storeDetailBean) {
        if (getView() != null) {
            getView().getStoreDetailSuccess(storeDetailBean);
        }
    }

    @Override
    public void getStoreDeailFailure(String failure) {
        if (getView() != null) {
            getView().getStoreDeailFailure(failure);
        }
    }
}
