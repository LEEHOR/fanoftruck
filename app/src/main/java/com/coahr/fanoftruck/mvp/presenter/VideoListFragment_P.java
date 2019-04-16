package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.VideoListFragment_C;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.TruckVideoListData;
import com.coahr.fanoftruck.mvp.model.VideoListFragment_M;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoList;

import javax.inject.Inject;

/**
 * Created by lizhiguo
 * on 2019/4/16
 */
public class VideoListFragment_P extends BasePresenter<VideoListFragment_C.View,VideoListFragment_C.Model> implements VideoListFragment_C.Presenter
{
    @Inject
    public VideoListFragment_P(Fragment_home_videoList mview, VideoListFragment_M mModel) {
       super(mview,mModel);
    }

    @Override
    public void getTruckVideoList() {
        if (mModle != null) {
            mModle.getTruckVideoList();
        }
    }

    @Override
    public void getVideoListSuccess(TruckVideoListData truckVideoListData) {
        if (getView() != null) {
            getView().getVideoListSuccess(truckVideoListData);
        }
    }

    @Override
    public void getVideoListFailure(String failure) {
        if (getView() != null) {
            getView().getVideoListFailure(failure);
        }
    }

    @Override
    public void getTruckVideoListMore() {
        if (mModle != null) {
            mModle.getTruckVideoListMore();
        }
    }

    @Override
    public void getVideoMoreSuccess(TruckVideoListData truckVideoListData) {
        if (getView() != null) {
            getView().getVideoMoreSuccess(truckVideoListData);
        }
    }

    @Override
    public void getVideoMoreFailure(String failure) {
        if (getView() != null) {
            getView().getVideoMoreFailure(failure);
        }
    }
}
