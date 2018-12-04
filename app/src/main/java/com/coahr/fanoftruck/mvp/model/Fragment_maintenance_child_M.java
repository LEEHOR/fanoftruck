package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:56
 */
public class Fragment_maintenance_child_M extends BaseModel<Fragment_maintenance_child_C.Presenter> implements Fragment_maintenance_child_C.Model {

   @Inject
    public Fragment_maintenance_child_M() {
        super();
    }


    @Override
    public void startLocation() {

    }

    @Override
    public void getVideoList(Map<String, String> map) {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoList>(getApiService().getMaintenanceVideoList(map.get("video_name"),
                    map.get("video_type"),map.get("start"),map.get("length"))))
                    .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoList>() {
                        @Override
                        public void _onNext(MaintenanceVideoList maintenanceVideoList) {
                            if (getPresenter() != null) {
                                if (maintenanceVideoList.getCode()==0) {
                                    getPresenter().getVideoListSuccess(maintenanceVideoList);
                                }else {
                                    getPresenter().getVideoListFailure(maintenanceVideoList.getMsg());
                                }
                            }
                        }
                    }));
    }

    @Override
    public void getVideoMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoList>(getApiService().getMaintenanceVideoList(map.get("video_name"),
                map.get("video_type"),map.get("start"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoList>() {
                    @Override
                    public void _onNext(MaintenanceVideoList maintenanceVideoList) {
                        if (getPresenter() != null) {
                            if (maintenanceVideoList.getCode()==0) {
                                getPresenter().getVideoMoreSuccess(maintenanceVideoList);
                            }else {
                                getPresenter().getVideoMoreFailure(maintenanceVideoList.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getVideoSearch(Map<String, String> map) {

    }

}
