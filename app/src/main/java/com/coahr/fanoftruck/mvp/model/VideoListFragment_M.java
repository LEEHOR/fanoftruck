package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.constract.VideoListFragment_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.TruckVideoListData;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by lizhiguo
 * on 2019/4/16
 */
public class VideoListFragment_M extends BaseModel<VideoListFragment_C.Presenter> implements VideoListFragment_C.Model {
   @Inject
    public VideoListFragment_M() {
        super();
    }

    @Override
    public void getTruckVideoList() {
            mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<TruckVideoListData>(getApiService().getTruckVideoList()))
                    .subscribeWith(new SimpleDisposableSubscriber<TruckVideoListData>() {
                        @Override
                        public void _onNext(TruckVideoListData truckVideoListData) {
                            if (getPresenter() != null) {
                                if (truckVideoListData.getCode()==0) {
                                    getPresenter().getVideoListSuccess(truckVideoListData);
                                }else {
                                    getPresenter().getVideoListFailure(truckVideoListData.getMsg());
                                }
                            }
                        }
                    }));
    }

    @Override
    public void getTruckVideoListMore() {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<TruckVideoListData>(getApiService().getTruckVideoList()))
                .subscribeWith(new SimpleDisposableSubscriber<TruckVideoListData>() {
                    @Override
                    public void _onNext(TruckVideoListData truckVideoListData) {
                        if (getPresenter() != null) {
                            if (truckVideoListData.getCode()==0) {
                                getPresenter().getVideoMoreSuccess(truckVideoListData);
                            }else {
                                getPresenter().getVideoMoreFailure(truckVideoListData.getMsg());
                            }
                        }
                    }
                }));
    }
}
