package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.HomeData;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_home_C {

    interface View extends BaseContract.View {
        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getHomeDataSuccess(HomeData homeData);

        void getHomeDataFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getHomeData();

        void getHomeDataSuccess(HomeData homeData);

        void getHomeDataFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getHomeData();
    }
}
