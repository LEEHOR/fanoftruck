package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.TruckVideoListData;

import java.util.Map;

/**
 * Created by lizhiguo
 * on 2019/4/16
 */
public interface VideoListFragment_C {

    interface View extends BaseContract.View {
        void getVideoListSuccess(TruckVideoListData truckVideoListData);

        void getVideoListFailure(String failure);

        void getVideoMoreSuccess(TruckVideoListData truckVideoListData);

        void getVideoMoreFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {
        void getTruckVideoList();

        void getVideoListSuccess(TruckVideoListData truckVideoListData);

        void getVideoListFailure(String failure);

        void getTruckVideoListMore();

        void getVideoMoreSuccess(TruckVideoListData truckVideoListData);

        void getVideoMoreFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void getTruckVideoList();

        void getTruckVideoListMore();
    }
}