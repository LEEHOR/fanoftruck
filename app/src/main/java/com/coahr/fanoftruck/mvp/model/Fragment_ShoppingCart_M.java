package com.coahr.fanoftruck.mvp.model;


import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_ShoppingCart_C;
import com.coahr.fanoftruck.mvp.model.Bean.DelFormShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingCart;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_ShoppingCart_M extends BaseModel<Fragment_ShoppingCart_C.Presenter> implements Fragment_ShoppingCart_C.Model {
    @Inject
    public Fragment_ShoppingCart_M() {
        super();
    }



    @Override
    public void getShoppingCarList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<ShoppingCart>(getApiService().getShoppingCarList(map)))
                .subscribeWith(new SimpleDisposableSubscriber<ShoppingCart>() {
                    @Override
                    public void _onNext(ShoppingCart shoppingCart) {
                        if (getPresenter() != null) {
                            if (shoppingCart.getCode()==0) {
                                getPresenter().getShoppingListSuccess(shoppingCart);
                            }else {
                                getPresenter().getShoppingListFailure(shoppingCart.getMsg());
                            }
                        }
                    }
                }));

    }

    @Override
    public void delShopping(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<DelFormShoppingCart>(getApiService().delFromShoppingCar(map)))
                .subscribeWith(new SimpleDisposableSubscriber<DelFormShoppingCart>() {
                    @Override
                    public void _onNext(DelFormShoppingCart delFormShoppingCart) {
                        if (getPresenter() != null) {
                            if (delFormShoppingCart.getCode()==0) {
                                getPresenter().delShoppingSuccess(delFormShoppingCart);
                            }else {
                                getPresenter().delShoppingFailure(delFormShoppingCart.getMsg());
                            }
                        }
                    }
                }));
    }


}
