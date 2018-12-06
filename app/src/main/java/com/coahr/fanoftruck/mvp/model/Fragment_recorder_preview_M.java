package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.Utils.ToastUtils;
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

    private File image_file;
    private RequestBody image_requestFile;
    private File video_file;
    private RequestBody video_requestFile;

    @Inject
    public Fragment_recorder_preview_M() {
        super();
    }




    @Override
    public void startLocation() {

    }

    @Override
    public void uploadVideo(Map<String, String> map, List<String> FilePath) {
        ToastUtils.showLong(map.get("token"));
        Map<String, RequestBody> map1 = new HashMap<>();
       // map1.put("video_describe", RequestBody.create(null, map.get("video_describe")));
       // map1.put("video_type",RequestBody.create(null,map.get("video_type")));
       // map1.put("token", RequestBody.create(null, map.get("token")));
     //   List<MultipartBody.Part> parts = new ArrayList<>();
        for (int i = 0; i < FilePath.size(); i++) {
            if (i==0) {  //视频
                video_file = new File(FilePath.get(i));
                video_requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), video_file);
              //  MultipartBody.Part part = MultipartBody.Part.createFormData("video", video_file.getName(), video_requestFile);
               // parts.add(part);
            } else {  //图片
                image_file = new File(FilePath.get(i));
                image_requestFile = RequestBody.create(MediaType.parse("multipart/form-data"), image_file);
              //  MultipartBody.Part part = MultipartBody.Part.createFormData("video_cover", image_file.getName(), image_requestFile);
               // parts.add(part);
            }
        }
        RequestBody body=new MultipartBody.Builder()
                .addFormDataPart("video_describe",map.get("video_describe"))
                .addFormDataPart("video_type",map.get("video_type"))
                .addFormDataPart("token",map.get("token"))
                .addFormDataPart("video",video_file.getName(),video_requestFile)
                .addFormDataPart("video_cover",image_file.getName(),image_requestFile)
                .build();
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Video_upload>(getApiService().getSaveSuggest(body)))
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
