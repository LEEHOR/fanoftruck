package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_discuss_dialog_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_discuss_dialog_M extends BaseModel<Fragment_discuss_dialog_C.Presenter> implements Fragment_discuss_dialog_C.Model {
    @Inject
    public Fragment_discuss_dialog_M() {
        super();
    }


    @Override
    public void startLocation() {
    }

    @Override
    public void getVideoDiscussList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoDiscussList>(getApiService().getMaintenanceVideo_discuss_list(map.get("video_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoDiscussList>() {
                    @Override
                    public void _onNext(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {
                        if (getPresenter() != null) {
                            if (maintenanceVideoDiscussList.getCode()==0) {
                                getPresenter().getVideoDiscussSuccess(maintenanceVideoDiscussList);
                            }else {
                                getPresenter().getVideoDiscussFailure(maintenanceVideoDiscussList.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getVideoDiscussMoreList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<MaintenanceVideoDiscussList>(getApiService().getMaintenanceVideo_discuss_list(map.get("video_id"),map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<MaintenanceVideoDiscussList>() {
                    @Override
                    public void _onNext(MaintenanceVideoDiscussList maintenanceVideoDiscussList) {
                        if (getPresenter() != null) {
                            if (maintenanceVideoDiscussList.getCode()==0) {
                                getPresenter().getVideoDiscussMoreSuccess(maintenanceVideoDiscussList);
                            }else {
                                getPresenter().getVideoDiscussMoreFailure(maintenanceVideoDiscussList.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void getAddDiscuss(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<AddDiscuss>(getApiService().getAddDiscuss(map.get("video_id"),map.get("token"),map.get("discuss_content"))))
                .subscribeWith(new SimpleDisposableSubscriber<AddDiscuss>() {
                    @Override
                    public void _onNext(AddDiscuss addDiscuss) {
                        if (getPresenter() != null) {
                            if (addDiscuss.getCode()==0) {
                                getPresenter().getAddDiscussSuccess(addDiscuss);
                            }else {
                                getPresenter().getAddDiscussFailure(addDiscuss.getMsg());
                            }
                        }
                    }
                }));
    }
}
