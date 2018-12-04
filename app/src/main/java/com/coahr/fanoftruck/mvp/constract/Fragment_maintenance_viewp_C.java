package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:28
 */
public interface Fragment_maintenance_viewp_C {
    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getVideoSearchSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoSearchFailure(String failure);

        void getVideoSearchMoreSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoSearchMoreFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getVideoSearch(Map<String, String> map);

        void getVideoSearchSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoSearchFailure(String failure);

        void getVideoSearchMore(Map<String, String> map);

        void getVideoSearchMoreSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoSearchMoreFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getVideoSearch(Map<String, String> map);

        void getVideoSearchMore(Map<String, String> map);
    }
}
