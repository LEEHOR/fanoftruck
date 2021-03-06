package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_videoPlay_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.model.Bean.View_videoBean;
import com.socks.library.KLog;

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

    @Override
    public void getVideo_one(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<View_videoBean>(getApiService().getOneVideo(map.get("video_id"))))
                .subscribeWith(new SimpleDisposableSubscriber<View_videoBean>() {
                    @Override
                    public void _onNext(View_videoBean view_videoBean) {
                        if (getPresenter() != null) {
                            if (view_videoBean.getCode()==0) {
                                KLog.d("请求","成功1");
                                getPresenter().getVideo_oneSuccess(view_videoBean);
                            }else {
                                getPresenter().getVideo_oneFailure(view_videoBean.getMsg());
                            }
                        }
                    }
                }));
    }

}
