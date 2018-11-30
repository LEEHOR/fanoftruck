package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_detail_C;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:56
 */
public class Fragment_store_detail_M extends BaseModel<Fragment_store_detail_C.Presenter> implements Fragment_store_detail_C.Model {

   @Inject
    public Fragment_store_detail_M() {
        super();
    }
    @Inject
    BaiduLocationHelper baiduLocationHelper;
    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                baiduLocationHelper.stopLocation();

            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
        }
    };

    @Override
    public void startLocation() {
        initlocation();
        baiduLocationHelper.startLocation();
    }

    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        baiduLocationHelper.unRegisterLocationCallback(onLocationCallBack);
    }

    @Override
    public void getStoreDetail(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreDetailBean>(getApiService().getStoreDetail(map.get("s_id"),map.get("longitude"),map.get("latitude"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreDetailBean>() {
                    @Override
                    public void _onNext(StoreDetailBean storeDetailBean) {
                        if (getPresenter() != null) {
                            if (storeDetailBean.getCode()==0) {
                                getPresenter().getStoreDetailSuccess(storeDetailBean);
                            } else {
                                getPresenter().getStoreDeailFailure(storeDetailBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
