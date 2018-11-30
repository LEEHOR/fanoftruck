package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.BaiduLocationHelper;
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
    BaiduLocationHelper baiduLocationHelper;
    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {

                baiduLocationHelper.stopLocation();
            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {

            }
        }
    };


    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void startLocation() {
        initlocation();
        baiduLocationHelper.startLocation();
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
