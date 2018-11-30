package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.BaiDuLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_viewp_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 10:29
 */
public class Fragment_maintenance_viewp_M extends BaseModel<Fragment_maintenance_viewp_C.Presenter> implements Fragment_maintenance_viewp_C.Model {
    @Inject
    public Fragment_maintenance_viewp_M() {
        super();
    }
    @Inject
    BaiduLocationHelper baiduLocationHelper;
    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {
                getPresenter().onLocationSuccess(location);
                baiduLocationHelper.stopLocation();

            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {
                getPresenter().onLocationFailure(locType);
            }
        }
    };
    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }
    @Override
    public void startLocation() {

    }

    @Override
    public void getVideoSearch(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoList>(getApiService().getMaintenanceVideoList(map.get("video_name"),
                map.get("video_type"),map.get("start"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoList>() {
                    @Override
                    public void _onNext(MaintenanceVideoList maintenanceVideoList) {
                        if (getPresenter() != null) {
                            if (maintenanceVideoList.getCode()==0) {
                                getPresenter().getVideoSearchSuccess(maintenanceVideoList);
                            }else {
                                getPresenter().getVideoSearchFailure(maintenanceVideoList.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getVideoSearchMore(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoList>(getApiService().getMaintenanceVideoList(map.get("video_name"),
                map.get("video_type"),map.get("start"),map.get("length"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoList>() {
                    @Override
                    public void _onNext(MaintenanceVideoList maintenanceVideoList) {
                        if (getPresenter() != null) {
                            if (maintenanceVideoList.getCode()==0) {
                                getPresenter().getVideoSearchMoreSuccess(maintenanceVideoList);
                            }else {
                                getPresenter().getVideoSearchMoreFailure(maintenanceVideoList.getMsg());
                            }
                        }
                    }
                }));
    }
}
