package com.coahr.fanoftruck.mvp.constract;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:28
 */
public interface Fragment_maintenance_viewp_C {
    interface View extends BaseContract.View {

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);


    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();


    }
}
