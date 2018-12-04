package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:28
 */
public interface Fragment_discuss_dialog_C {
    interface View extends BaseContract.View {

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getVideoDiscussSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList);

        void getVideoDiscussFailure(String failure);

        void getVideoDiscussMoreSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList);

        void getVideoDiscussMoreFailure(String failure);



        void getAddDiscussSuccess(AddDiscuss addDiscuss);

        void getAddDiscussFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(AMapLocation location);

        void onLocationFailure(int failure);

        void getVideoDiscussList(Map<String, String> map);

        void getVideoDiscussSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList);

        void getVideoDiscussFailure(String failure);

        void getVideoDiscussMoreList(Map<String, String> map);

        void getVideoDiscussMoreSuccess(MaintenanceVideoDiscussList maintenanceVideoDiscussList);

        void getVideoDiscussMoreFailure(String failure);

        void getAddDiscuss(Map<String,String> map);

        void getAddDiscussSuccess(AddDiscuss addDiscuss);

        void getAddDiscussFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void startLocation();

        void getVideoDiscussList(Map<String, String> map);

        void getVideoDiscussMoreList(Map<String, String> map);

        void getAddDiscuss(Map<String,String> map);
    }
}
