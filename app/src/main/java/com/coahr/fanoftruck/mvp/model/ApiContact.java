package com.coahr.fanoftruck.mvp.model;

/**
 * Created by Leehor
 * on 2018/11/12
 * on 17:09
 */
public class ApiContact {
    //根路径
    public static String baseUrl="http://app.cvfans.net/";
    //测试路径
   // public static String baseUrl="http://192.168.191.1:8080/three_research/";
    //门店列表
    public static final String getStoreList="/APP/Query/get_station_list";
    //门店详情
    public static final String getStoreDetail="/APP/Query/get_station_detail";

    //城市
    public  static  final String getCity="/APP/Query/get_city";

    //搜索
    public  static  final String getSearch="/APP/Query/search_station_general";

}
