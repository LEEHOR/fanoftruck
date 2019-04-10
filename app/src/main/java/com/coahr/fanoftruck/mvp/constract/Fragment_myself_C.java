package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.BindWXData;
import com.coahr.fanoftruck.mvp.model.Bean.MyselfData;
import com.coahr.fanoftruck.mvp.model.Bean.UnsetWXData;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_myself_C {

    interface View extends BaseContract.View {
        void bindWXSuccess(BindWXData bindWXData);

        void bindWXFailure(String failure);

        void unsetWXSuccess(UnsetWXData unsetWXData);

        void unsetWXFailure(String failure);

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getMySelfDataSuccess(MyselfData myselfData);

        void getMySelfDataFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void bindWX(Map<String,String> map);

        void unsetWX(Map<String,String> map);

        void getMySelfData(Map<String,String> map);

        void unsetWXSuccess(UnsetWXData unsetWXData);

        void unsetWXFailure(String failure);

        void bindWXSuccess(BindWXData bindWXData);

        void bindWXFailure(String failure);

        void getMySelfDataSuccess(MyselfData myselfData);

        void getMySelfDataFailure(String failure);

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

    }

    interface Model extends BaseContract.Model {
        void bindWX(Map<String,String> map);

        void unsetWX(Map<String,String> map);

        void startLocation();

        void getMySelfData(Map<String,String> map);
    }
}
