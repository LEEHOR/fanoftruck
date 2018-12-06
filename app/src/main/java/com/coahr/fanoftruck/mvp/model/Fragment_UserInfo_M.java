package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.model.Bean.Center_Initial_Data;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Bean.LoginOutBean;
import com.coahr.fanoftruck.mvp.model.Bean.Save_Identity_Info;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:12
 */
public class Fragment_UserInfo_M extends BaseModel<Fragment_userInfo_C.Presenter> implements Fragment_userInfo_C.Model{

    private MultipartBody.Part part;

    @Inject
    public Fragment_UserInfo_M() {
        super();
    }

    @Override
    public void getCenter_Initial_Data(Map<String, String> map) {

        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Center_Initial_Data>(getApiService().Center_Initial_Data(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<Center_Initial_Data>() {
                    @Override
                    public void _onNext(Center_Initial_Data center_initial_data) {
                        if (getPresenter() != null) {
                            if (center_initial_data.getCode()==0) {
                                getPresenter().getCenter_Initial_DataSuccess(center_initial_data);
                            } else {
                                getPresenter().getCenter_Initial_DataFailure(center_initial_data.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void Save_Identity_Info(Map<String, String> map) {
       Map<String,RequestBody> map1=new HashMap<>();
        map1.put("token",RequestBody.create(null,map.get("token")));
        map1.put("nickname",RequestBody.create(null,map.get("nickname")));
        map1.put("sex",RequestBody.create(null,map.get("sex")));
        map1.put("postal_address",RequestBody.create(null,map.get("postal_address")));
        map1.put("detail_address",RequestBody.create(null,map.get("detail_address")));
       if (map.get("userheadimg")!=null) {
           File headImg_file = new File(map.get("userheadimg"));
           RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), headImg_file);
           part = MultipartBody.Part.createFormData("userheadimg", headImg_file.getName(), requestBody);
       } else {
           part=null;
       }
//        RequestBody body=new MultipartBody.Builder()
//                .addFormDataPart("token",map.get("token"))
//                .addFormDataPart("nickname",map.get("nickname"))
//                .addFormDataPart("sex",map.get("sex"))
//                .addFormDataPart("postal_address",map.get("postal_address"))
//                .addFormDataPart("detail_address",map.get("detail_address"))
//                .addFormDataPart("userheadimg",headImg_file.getName(),requestBody)
//                .build();
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<Save_Identity_Info>(getApiService().Save_Identity_Info(map1,part)))
                .subscribeWith(new SimpleDisposableSubscriber<Save_Identity_Info>() {
                    @Override
                    public void _onNext(Save_Identity_Info save_identity_info) {
                        if (getPresenter() != null) {
                            if (save_identity_info.getCode()==0) {
                                getPresenter().Save_Identity_InfoSuccess(save_identity_info);
                            } else {
                                getPresenter().Save_Identity_InfoFailure(save_identity_info.getJdata().getJmsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void LoginOut(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<LoginOutBean>(getApiService().LoginOut(map.get("token"))))
                .subscribeWith(new SimpleDisposableSubscriber<LoginOutBean>() {
                    @Override
                    public void _onNext(LoginOutBean loginOutBean) {
                        if (getPresenter() != null) {
                            if (loginOutBean.getCode()==0) {
                                getPresenter().LoginOutSuccess(loginOutBean);
                            } else {
                                getPresenter().LoginOutFailure(loginOutBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
