package com.coahr.fanoftruck.mvp.constract;

import android.net.Uri;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;

import java.util.List;
import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_recorder_preview_C {

    interface View extends BaseContract.View {

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void uploadVideoSuccess(Video_upload video_upload);

        void uploadVideoFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void startLocation();

        void onLocationSuccess(BDLocation location);

        void onLocationFailure(int failure);

        void uploadVideo(Map<String ,String> map , List<String> FilePath);

        void uploadVideoSuccess(Video_upload video_upload);

        void uploadVideoFailure(String failure);

    }

    interface Model extends BaseContract.Model {

        void startLocation();

        void uploadVideo(Map<String ,String> map , List<String> FilePath);

    }
}
