package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_ShoppingCart_C;
import com.coahr.fanoftruck.mvp.model.Bean.DelFormShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingCart;
import com.coahr.fanoftruck.mvp.model.Fragment_ShoppingCart_M;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_shoppingCart;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_ShoppingCart_P extends BasePresenter<Fragment_ShoppingCart_C.View,Fragment_ShoppingCart_C.Model> implements Fragment_ShoppingCart_C.Presenter {

    @Inject
    public Fragment_ShoppingCart_P(Fragment_shoppingCart mview, Fragment_ShoppingCart_M mModel) {
        super(mview, mModel);
    }


    @Override
    public void getShoppingCarList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getShoppingCarList(map);
        }
    }

    @Override
    public void getShoppingListSuccess(ShoppingCart shoppingCart) {
        if (getView() != null) {
            getView().getShoppingListSuccess(shoppingCart);
        }
    }

    @Override
    public void getShoppingListFailure(String failure) {
        if (getView() != null) {
            getView().getShoppingListFailure(failure);
        }
    }

    @Override
    public void delShopping(Map<String, String> map) {
        if (mModle != null) {
            mModle.delShopping(map);
        }
    }

    @Override
    public void delShoppingSuccess(DelFormShoppingCart delFormShoppingCart) {
        if (getView() != null) {
            getView().delShoppingSuccess(delFormShoppingCart);
        }
    }

    @Override
    public void delShoppingFailure(String failure) {
        if (getView() != null) {
            getView().delShoppingFailure(failure);
        }
    }
}
