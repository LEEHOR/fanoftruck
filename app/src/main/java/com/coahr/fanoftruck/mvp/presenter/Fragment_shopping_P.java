package com.coahr.fanoftruck.mvp.presenter;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.model.Bean.SearchBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_shopping_C;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.mvp.model.Fragment_shopping_M;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:31
 */
public class Fragment_shopping_P extends BasePresenter<Fragment_shopping_C.View,Fragment_shopping_C.Model> implements Fragment_shopping_C.Presenter {
    @Inject
    public Fragment_shopping_P(Fragment_Shopping mview, Fragment_shopping_M mModel) {
        super(mview, mModel);
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
    public void getShoppingList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getShoppingList(map);
        }
    }

    @Override
    public void getShoppingListSuccess(ShoppingMallBean shoppingMallBean) {
        if (getView() != null) {
            getView().getShoppingListSuccess(shoppingMallBean);
        }
    }

    @Override
    public void getShoppingListFailure(String failure) {
        if (getView() != null) {
            getView().getShoppingListFailure(failure);
        }
    }

    @Override
    public void getShoppingMore(Map<String, String> map) {
        if (mModle != null) {
            mModle.getShoppingMore(map);
        }
    }

    @Override
    public void getShoppingMoreSuccess(ShoppingMallBean  shoppingMallBean) {
        if (getView() != null) {
            getView().getShoppingMoreSuccess(shoppingMallBean);
        }
    }

    @Override
    public void getShoppingMoreFailure(String failure) {
        if (getView() != null) {
            getView().getShoppingMoreFailure(failure);
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
}
