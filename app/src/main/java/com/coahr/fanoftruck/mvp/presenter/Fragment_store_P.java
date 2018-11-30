package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_store_C;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;
import com.coahr.fanoftruck.mvp.model.Fragment_home_M;
import com.coahr.fanoftruck.mvp.model.Fragment_store_M;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_store_P extends BasePresenter<Fragment_store_C.View,Fragment_store_C.Model> implements Fragment_store_C.Presenter {

    @Inject
    public Fragment_store_P(Fragment_Store mview, Fragment_store_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
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
    public void getStoreList(Map<String,String> map) {
        if (mModle != null) {
            mModle.getStoreList(map);
        }
    }

    @Override
    public void getStoreListSuccess(StoreBean storeBean) {
        if (getView() != null) {
            getView().getStoreListSuccess(storeBean);
        }
    }

    @Override
    public void getStoreListFailure(String failure) {
        if (getView() != null) {
            getView().getStoreListFailure(failure);
        }
    }

    @Override
    public void getStoreMore(Map<String,String> map) {
        if (mModle != null) {
            mModle.getStoreMore(map);
        }
    }

    @Override
    public void getStoreMoreSuccess(StoreBean storeBean) {
        if (getView() != null) {
            getView().getStoreMoreSuccess(storeBean);
        }
    }

    @Override
    public void getStoreMoreFailure(String failure) {
        if (getView() != null) {
            getView().getStoreMoreFailure(failure);
        }
    }

    @Override
    public void getSearchMap(Map<String, String> map) {
        if (mModle != null) {
            mModle.getSearchMap(map);
        }
    }

    @Override
    public void getSearchSuccess(SearchBean searchBean) {
        if (getView() != null) {
            getView().getSearchSuccess(searchBean);
        }
    }

    @Override
    public void getSearchFailure(String failure) {
        if (getView() != null) {
            getView().getSearchFailure(failure);
        }
    }

    @Override
    public void getCityList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getCityList(map);
        }
    }

    @Override
    public void getCityListSuccess(CityInfoBean cityInfoBean) {
        if (getView() != null) {
            getView().getCityListSuccess(cityInfoBean);
        }
    }

    @Override
    public void getCityListFailure(String failure) {
        if (getView() != null) {
            getView().getCityListFailure(failure);
        }
    }
}
