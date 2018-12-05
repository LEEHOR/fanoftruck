package com.coahr.fanoftruck.mvp.model;

import android.support.annotation.Nullable;

import com.coahr.fanoftruck.mvp.model.Bean.AppointmentBean;
import com.coahr.fanoftruck.mvp.model.Bean.AppointmentDefaultBean;
import com.coahr.fanoftruck.mvp.model.Bean.Business_car;
import com.coahr.fanoftruck.mvp.model.Bean.CarDefaultBean;
import com.coahr.fanoftruck.mvp.model.Bean.SearchBean;
import com.coahr.fanoftruck.mvp.model.Bean.AddDiscuss;
import com.coahr.fanoftruck.mvp.model.Bean.CarListBean;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoDiscussList;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideo_dz;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallBean;
import com.coahr.fanoftruck.mvp.model.Bean.ShoppingMallDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.MaintenanceVideoList;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.model.Bean.Video_upload;
import com.coahr.fanoftruck.mvp.model.Bean.getBuyCarCode;


import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
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
    Call<Video_upload> getSaveSuggest(@FieldMap Map<String, RequestBody> map, @Part() List<MultipartBody.Part> parts);

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


    /**
     * 保存我的爱车
     */
    @FormUrlEncoded
    @POST(ApiContact.Save_use_Car)
    Call<SaveUserCar> Save_use_Car(@Field("token") String token, @Field("car_frameno") String car_frameno, @Field("car_no") String car_no);


    /**
     * 车辆列表
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.Car_list)
    Call<CarListBean> car_list(@Field("token") String token);


    /**
     * 设置默认车辆
     * @param token
     * @param car_id
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.set_car_default)
    Call<CarDefaultBean> set_car_default(@Field("token") String token, @Field("car_id") String car_id);


    /**
     * 删除车辆
     * @param token
     * @param car_id
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.del_car)
    Call<CarDefaultBean> del_car(@Field("token") String token,@Field("car_id") String car_id);


    /**
     * 预约提交订单
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.appointment)
    Call<AppointmentBean> appointment_order(@Field("token") String token, @Field("car_frameno") String car_frameno
            ,@Field("car_no") String car_no,@Field("username") String username
            ,@Field("telephone") String telephone,@Field("appoint_time") String appoint_time
            ,@Field("service_item") String service_item,@Field("description") String description
            ,@Field("s_type") String s_type,@Field("s_id") String s_id);

    /**
     * 预约默认加载
     * @param token
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.appointmentDefault)
    Call<AppointmentDefaultBean> appointmentDefault(@Field("token") String token);


    /**
     * 推荐购车默认数据
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.buyCar_initial_data)
    Call<Business_car> buyCar_initial_data(@Field("token") String token);

    /**
     * 推荐购车默认数据
     * @return
     */
    @FormUrlEncoded
    @POST(ApiContact.get_buyCar_code)
    Call<getBuyCarCode> get_buyCar_code(@Field("phone") String phone);
}
