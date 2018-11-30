package com.coahr.fanoftruck.mvp.presenter;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_recorder_preview_C;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;
import com.coahr.fanoftruck.mvp.model.Fragment_recorder_preview_M;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.Fragment_recorder_Preview;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:29
 */
public class Fragment_recorder_preview_P extends BasePresenter<Fragment_recorder_preview_C.View,Fragment_recorder_preview_C.Model> implements Fragment_recorder_preview_C.Presenter {

    @Inject
    public Fragment_recorder_preview_P(Fragment_recorder_Preview mview, Fragment_recorder_preview_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void startLocation() {
        if (mModle != null) {
            mModle.startLocation();
        }
    }

    @Override
    public void onLocationSuccess(BDLocation location) {
        if (getView() != null) {
            getView().onLocationSuccess(location);
        }
    }

    @Override
    public void onLocationFailure(int failure) {
        if (getView() != null) {
            getView().onLocationFailure(failure);
        }
    }

    @Override
    public void uploadVideo(Map<String, String> map, List<String> FilePath) {
        if (mModle != null) {
            mModle.uploadVideo(map,FilePath);
        }
    }

    @Override
    public void uploadVideoSuccess(Video_upload video_upload) {
        if (getView() != null) {
            getView().uploadVideoSuccess(video_upload);
        }
    }

    @Override
    public void uploadVideoFailure(String failure) {
        if (getView() != null) {
            getView().uploadVideoFailure(failure);
        }
    }
}
