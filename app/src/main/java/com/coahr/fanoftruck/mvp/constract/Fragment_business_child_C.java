package com.coahr.fanoftruck.mvp.constract;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_business_child_C {

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
