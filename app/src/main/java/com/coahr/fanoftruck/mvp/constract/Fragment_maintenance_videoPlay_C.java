package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_maintenance_videoPlay_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoListFailure(String failure);

        void getVideo_dzSuccess(MaintenanceVideo_dz maintenanceVideo_dz);

        void getVideo_dzFailure(String failure);


    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);


        void getVideoList(Map<String, String> map);

        void getVideoListSuccess(MaintenanceVideoList maintenanceVideoList);

        void getVideoListFailure(String failure);

        void getVideo_dz(Map<String, String> map);

        void getVideo_dzSuccess(MaintenanceVideo_dz maintenanceVideo_dz);

        void getVideo_dzFailure(String failure);
    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getVideoList(Map<String, String> map);

        void getVideo_dz(Map<String, String> map);
    }
}
