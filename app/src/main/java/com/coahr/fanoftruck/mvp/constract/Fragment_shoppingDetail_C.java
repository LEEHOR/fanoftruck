package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.AddShoppingCart;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_shoppingDetail_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getShoppingDetailSuccess(ShoppingMallDetailBean shoppingMallDetailBean);

        void getShoppingDetailFailure(String failure);

        void AddShoppingCarSuccess(AddShoppingCart addShoppingCart);

        void AddShoppingCarFailure(String failure);



    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getShoppingDetail(Map<String, String> map);

        void getShoppingDetailSuccess(ShoppingMallDetailBean shoppingMallDetailBean);

        void getShoppingDetailFailure(String failure);

        void AddShoppingCar(Map<String,String> map);

        void AddShoppingCarSuccess(AddShoppingCart addShoppingCart);

        void AddShoppingCarFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getShoppingDetail(Map<String, String> map);

        void AddShoppingCar(Map<String,String> map);
    }
}
