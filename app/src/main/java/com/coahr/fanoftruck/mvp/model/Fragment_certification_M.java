package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_certification_C;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;
import com.coahr.fanoftruck.mvp.model.Bean.save_identity_pic;

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
 * on 2018/12/4
 * on 14:36
 */
public class Fragment_certification_M extends BaseModel<Fragment_certification_C.Presenter> implements Fragment_certification_C.Model {

    private MultipartBody.Part part1, part2;

    @Inject
    public Fragment_certification_M() {
        super();
    }

    @Override
    public void Save_identity_pic(Map<String, String> map, List<String> list) {
        Map<String, RequestBody> map1 = new HashMap<>();
        map1.put("token", RequestBody.create(null, map.get("token")));
        map1.put("realname", RequestBody.create(null, map.get("realname")));
        List<MultipartBody.Part> parts = new ArrayList<>();
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                if (i == 0) {
                    String s = list.get(i);
                    if (s != null) {
                        File file_img_Z = new File(s);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file_img_Z);
                        part1 = MultipartBody.Part.createFormData("pic1", file_img_Z.getName(), requestBody);
                        parts.add(part1);
                    }
                }
                if (i == 1) {
                    String s = list.get(i);
                    if (s != null) {
                        File file_img_F = new File(s);
                        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file_img_F);
                        part2 = MultipartBody.Part.createFormData("pic2", file_img_F.getName(), requestBody);
                        parts.add(part2);
                    }
                }
            }
        }
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<save_identity_pic>(getApiService().save_identity_pic(map1,parts)))
                .subscribeWith(new SimpleDisposableSubscriber<save_identity_pic>() {
                    @Override
                    public void _onNext(save_identity_pic save_identity_pic) {
                        if (getPresenter() != null) {
                            if (save_identity_pic.getCode()==0) {
                                getPresenter().Save_identity_picSuccess(save_identity_pic);
                            } else {
                                getPresenter().Save_identity_picFailure(save_identity_pic.getMsg());
                            }
                        }
                    }
                }));

    }
}
