package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.BaiDuLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:56
 */
public class Fragment_maintenance_videoPlay_M extends BaseModel<Fragment_maintenance_videoPlay_C.Presenter> implements Fragment_maintenance_videoPlay_C.Model {

   @Inject
    public Fragment_maintenance_videoPlay_M() {
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
    public void getVideo_dz(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideo_dz>(getApiService().getMaintenanceVideo_dz(map.get("video_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideo_dz>() {
                    @Override
                    public void _onNext(MaintenanceVideo_dz maintenanceVideo_dz) {
                        if (getPresenter() != null) {
                            if (maintenanceVideo_dz.getCode()==0) {
                                getPresenter().getVideo_dzSuccess(maintenanceVideo_dz);
                            }else {
                                getPresenter().getVideo_dzFailure(maintenanceVideo_dz.getMsg());
                            }
                        }
                    }
                }));
    }

    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        baiduLocationHelper.unRegisterLocationCallback(onLocationCallBack);
    }
}
