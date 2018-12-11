package com.coahr.fanoftruck.mvp.constract;

import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.DelFormShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingCart;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_ShoppingCart_C {

    interface View extends BaseContract.View {
        void getShoppingListSuccess(ShoppingCart shoppingCart);

        void getShoppingListFailure(String failure);

        void delShoppingSuccess(DelFormShoppingCart delFormShoppingCart);

        void delShoppingFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void getShoppingCarList(Map<String, String> map);

        void getShoppingListSuccess(ShoppingCart shoppingCart);

        void getShoppingListFailure(String failure);

        void delShopping(Map<String,String> map);

        void delShoppingSuccess(DelFormShoppingCart delFormShoppingCart);

        void delShoppingFailure(String failure);
    }

    interface Model extends BaseContract.Model {

        void getShoppingCarList(Map<String, String> map);

        void delShopping(Map<String,String> map);
    }
}
