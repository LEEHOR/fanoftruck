package com.coahr.fanoftruck.mvp.model;

import android.support.annotation.Nullable;

import com.coahr.fanoftruck.mvp.Base.SearchBean;
import com.coahr.fanoftruck.mvp.model.Bean.CityInfoBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreBean;
import com.coahr.fanoftruck.mvp.model.Bean.StoreDetailBean;


import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

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
}
