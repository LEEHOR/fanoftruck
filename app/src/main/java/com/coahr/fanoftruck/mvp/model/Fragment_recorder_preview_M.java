package com.coahr.fanoftruck.mvp.model;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.Utils.GpsLocation.BaiduLocationHelper;
import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_recorder_preview_C;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:53
 */
public class Fragment_recorder_preview_M extends BaseModel<Fragment_recorder_preview_C.Presenter> implements Fragment_recorder_preview_C.Model {
    @Inject
    public Fragment_recorder_preview_M() {
        super();
    }

    @Inject
    BaiduLocationHelper baiduLocationHelper;
    private BaiduLocationHelper.OnLocationCallBack onLocationCallBack = new BaiduLocationHelper.OnLocationCallBack() {
        @Override
        public void onLocationSuccess(BDLocation location) {
            if (getPresenter() != null) {

                baiduLocationHelper.stopLocation();
                baiduLocationHelper.stopLocation();
            }
        }

        @Override
        public void onLocationFailure(int locType) {
            if (getPresenter() != null) {

            }
        }
    };


    private void initlocation() {
        baiduLocationHelper.registerLocationCallback(onLocationCallBack);
    }

    @Override
    public void detachPresenter() {
        super.detachPresenter();
        baiduLocationHelper.unRegisterLocationCallback(onLocationCallBack);
    }

    @Override
    public void startLocation() {
        initlocation();
        baiduLocationHelper.startLocation();
    }

    @Override
    public void uploadVideo(Map<String, String> map, List<String> FilePath) {
        Map<String, RequestBody> map1 = new HashMap<>();
        map1.put("token", RequestBody.create(null, map.get("token")));
        map1.put("video_describe", RequestBody.create(null, map.get("video_describe")));
        map1.put("video_type",RequestBody.create(null,map.get("video_type")));
        List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < FilePath.size(); i++) {
            if (i==0) {  //视频
                File file = new File(FilePath.get(i));
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("video", file.getName(), requestFile);
                parts.add(part);
            } else {
                File file = new File(FilePath.get(i));
                RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
                MultipartBody.Part part = MultipartBody.Part.createFormData("video_cover", file.getName(), requestFile);
                parts.add(part);
            }
        }
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Video_upload>(getApiService().getSaveSuggest(map1,parts)))
        .subscribeWith(new SimpleDisposableSubscriber<Video_upload>() {
            @Override
            public void _onNext(Video_upload video_upload) {
                if (getPresenter() != null) {
                    if (video_upload.getCode()==0) {
                        getPresenter().uploadVideoSuccess(video_upload);
                    } else {
                        getPresenter().uploadVideoFailure(video_upload.getMsg());
                    }
                }
            }
        }));
    }

}
