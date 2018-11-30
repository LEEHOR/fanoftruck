package com.coahr.fanoftruck.mvp.constract;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_shopping_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void getShoppingListSuccess(ShoppingMallBean shoppingMallBean);

        void getShoppingListFailure(String failure);

        void getShoppingMoreSuccess(ShoppingMallBean shoppingMallBean);

        void getShoppingMoreFailure(String failure);

        void getSearchSuccess(SearchBean searchBean);

        void getSearchFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void getShoppingList(Map<String,String> map);

        void getShoppingListSuccess(ShoppingMallBean shoppingMallBean);

        void getShoppingListFailure(String failure);


        void  getShoppingMore(Map<String,String> map);

        void getShoppingMoreSuccess(ShoppingMallBean shoppingMallBean);

        void getShoppingMoreFailure(String failure);

        void getSearchMap(Map<String,String> map);

        void getSearchSuccess(SearchBean searchBean);

        void getSearchFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getShoppingList(Map<String,String> map);

        void  getShoppingMore(Map<String,String> map);

        void getSearchMap(Map<String,String> map);
    }
}
