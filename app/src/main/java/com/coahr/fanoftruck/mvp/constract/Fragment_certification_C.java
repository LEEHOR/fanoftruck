package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.save_identity_pic;

import java.util.List;
import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_certification_C {

    interface View extends BaseContract.View {
        void Save_identity_picSuccess(save_identity_pic save_identity_pic);

        void Save_identity_picFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {

        void Save_identity_pic(Map<String,String> map, List<String> list);

        void Save_identity_picSuccess(save_identity_pic save_identity_pic);

        void Save_identity_picFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void Save_identity_pic(Map<String,String> map, List<String> list);

    }
}
