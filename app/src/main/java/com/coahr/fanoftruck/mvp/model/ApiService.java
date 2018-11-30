package com.coahr.fanoftruck.mvp.model;

import android.support.annotation.Nullable;

import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 16:01
 */
public interface ApiService {

    /**
     * 获取门店信息
     *  order
     *  page
     *  length
     *  city
     *  longitude
     *  latitude
     *
     */
    @FormUrlEncoded
    @POST(ApiContact.getStoreList)
    Call<StoreBean> getStoreList(@Field("order") String order, @Field("page") String page, @Field("length") String length, @Field("city") String city, @Field("longitude") String longitude, @Field("latitude") String latitude);

    /**
     * 门店详情
     * @param S_id
     * @param longitude
     * @param latitude
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getStoreDetail)
    Call<StoreDetailBean> getStoreDetail(@Field("s_id") String S_id, @Field("longitude") String longitude, @Field("latitude") String latitude);

    /**
     * 城市
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getCity)
    Call<CityInfoBean> getCity(@Field("token") @Nullable String token);

    /**
     * 搜索
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getSearch)
    Call<SearchBean> searchAll(@Field("search_key") String token);

    /**
     * 商品信息
     * @param brand
     * @param order
     * @param sort
     * @param page
     * @param length
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getShoppingList)
    Call<ShoppingMallBean> getShoppingList(@Field("brand") String brand, @Field("order") String order,@Field("price") String price,
                                           @Field("sort") String sort, @Field("page") String page, @Field("length") String length);

    /**
     * 商品详情
     * @param c_id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getShoppingDetail)
    Call<ShoppingMallDetailBean> getShoppingDetail(@Field("c_id") String c_id, @Field("token") String token);


    /**
     * 维修视频列表
     * @param video_name
     * @param video_type
     * @param start
     * @param length
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getMaintenanceVideoList)
    Call<MaintenanceVideoList> getMaintenanceVideoList(@Field("video_name") String video_name,@Field("video_type") String video_type,@Field("start") String start
    ,@Field("length") String length);


    /**
     * 视频点赞
     * @param video_id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getVideo_dz)
    Call<MaintenanceVideo_dz> getMaintenanceVideo_dz(@Field("video_id") String video_id,@Field("token")String token);


    /**
     * 视频点赞
     * @param video_id
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.getVideo_discuss_list)
    Call<MaintenanceVideoDiscussList> getMaintenanceVideo_discuss_list(@Field("video_id") String video_id, @Field("token")String token);

    /**
     * 视频上传
     * @param map
     * @param parts
     * @return
     */
    @Multipart
    @POST(ApiContact.getSaveVideo)
    Call<Video_upload> getSaveSuggest(@PartMap Map<String, RequestBody> map, @Part() List<MultipartBody.Part> parts);

    /**
     * 视频评论
     */
    @FormUrlEncoded
    @POST(ApiContact.getAdddiscuss)
    Call<AddDiscuss> getAddDiscuss(@Field("video_id") String video_id, @Field("token") String token,@Field("discuss_content") String discuss_content);


    /**
     * 登录接口
     */
    @FormUrlEncoded
    @POST(ApiContact.login)
    Call<LoginBean> login(@Field("phone") String phone, @Field("pwd") String pwd);

    /**
     * 注册接口
     * @param userName
     * @param email
     * @param device_token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.registerAccount)
    Call<RegisterBean> register(@Field("phone") String phone, @Field("userName") String userName,@Field("pwd") String pwd
            ,@Field("email") String email,@Field("device_token") String device_token,@Field("verify_code") String verify_code);

    /**
     * 注册短信接口
     */
    @FormUrlEncoded
    @POST(ApiContact.getRegisterVerifyCode)
    Call<VerifyCode> getRegisterCode(@Field("phone") String phone);


    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(ApiContact.forgertPass)
    Call<ForgetPass> forgertPass(@Field("phone") String phone, @Field("pwd") String pwd, @Field("verify_code") String verify_code);

    /**
     * 忘记密码短信接口
     */
    @FormUrlEncoded
    @POST(ApiContact.forgertPass_VerifyCode)
    Call<VerifyCode> forgertPass_VerifyCode(@Field("phone") String phone);
}
