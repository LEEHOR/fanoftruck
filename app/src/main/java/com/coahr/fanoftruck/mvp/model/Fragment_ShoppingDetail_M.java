package com.coahr.fanoftruck.mvp.model;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.GaodeMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_shoppingDetail_C;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 10:20
 */
public class Fragment_ShoppingDetail_M extends BaseModel<Fragment_shoppingDetail_C.Presenter> implements Fragment_shoppingDetail_C.Model {
    @Inject
    public Fragment_ShoppingDetail_M() {
        super();
    }

    @Inject
    GaodeMapLocation gaodeMapLocation;

    private GaodeMapLocation.OnLocationCallBack locationCallBack=new GaodeMapLocation.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(AMapLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                gaodeMapLocation.stopLocation();

            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
            gaodeMapLocation.stopLocation();
        }
    };

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        gaodeMapLocation.unRegisterLocationCallback(locationCallBack);
    }

    private void initlocation() {
        gaodeMapLocation.registerLocationCallback(locationCallBack);
    }

    @Override
    public void startLocation() {
        initlocation();
        gaodeMapLocation.startLocation();
    }

    @Override
    public void getShoppingDetail(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ShoppingMallDetailBean>(getApiService().getShoppingDetail(map.get("c_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<ShoppingMallDetailBean>() {
                    @Override
                    public void _onNext(ShoppingMallDetailBean shoppingMallDetailBean) {
                        if (getPresenter() != null) {
                            if (shoppingMallDetailBean.getCode()==0) {
                                getPresenter().getShoppingDetailSuccess(shoppingMallDetailBean);
                            }else {
                                getPresenter().getShoppingDetailFailure(shoppingMallDetailBean.getMsg());
                            }
                        }
                    }
                }));
    }

}
