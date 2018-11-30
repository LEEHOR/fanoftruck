package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_C;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_store_M extends BaseModel<Fragment_store_C.Presenter> implements Fragment_store_C.Model {
    @Inject
    public Fragment_store_M() {
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


    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        baiduLocationHelper.unRegisterLocationCallback(onLocationCallBack);
    }

    @Override
    public void startLocation() {
        initlocation();
        baiduLocationHelper.startLocation();
    }

    @Override
    public void getStoreList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreBean>(getApiService().getStoreList(map.get("order"), map.get("page"), map.get("length"), map.get("city"), map.get("longitude"), map.get("latitude"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreBean>() {
                    @Override
                    public void _onNext(StoreBean storeBean) {
                        if (getPresenter() != null) {
                            if (storeBean.getCode() == 0) {
                                getPresenter().getStoreListSuccess(storeBean);
                            } else {
                                getPresenter().getStoreListFailure(storeBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getStoreMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<StoreBean>(getApiService().getStoreList(map.get("order"), map.get("page"), map.get("length"), map.get("city"), map.get("longitude"), map.get("latitude"))))
                .subscribeWith(new SimpleDisposableSubscriber<StoreBean>() {
                    @Override
                    public void _onNext(StoreBean storeBean) {
                        if (getPresenter() != null) {
                            if (storeBean.getCode() == 0) {
                                getPresenter().getStoreMoreSuccess(storeBean);
                            } else {
                                getPresenter().getStoreMoreFailure(storeBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getSearchMap(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<SearchBean>(getApiService().searchAll(map.get("search_key"))))
                .subscribeWith(new SimpleDisposableSubscriber<SearchBean>() {
                    @Override
                    public void _onNext(SearchBean searchBean) {
                        if (getPresenter() != null) {
                            if (searchBean.getCode() == 0) {
                                getPresenter().getSearchSuccess(searchBean);
                            } else {
                                getPresenter().getSearchFailure(searchBean.getMsg());
                            }
                        }

                    }
                }));
    }

    @Override
    public void getCityList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<CityInfoBean>(getApiService().getCity(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<CityInfoBean>() {
                    @Override
                    public void _onNext(CityInfoBean cityInfoBean) {
                        if (getPresenter() != null) {
                            if (cityInfoBean.getCode() == 0) {
                                getPresenter().getCityListSuccess(cityInfoBean);
                            } else {
                                getPresenter().getCityListFailure(cityInfoBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
