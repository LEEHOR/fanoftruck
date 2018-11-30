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

    //商品列表
    public static final String getShoppingList = "/APP/Query/get_commodity_list";

    //商品详情
    public static  final String getShoppingDetail="/APP/Query/get_commodity_detail";

    //维修视频列表
    public static  final  String getMaintenanceVideoList="/APP/Video/get_video_list";

    //视频点赞
    public static final  String getVideo_dz="APP/Video/video_dz";

    //视频评论
    public static final  String getVideo_discuss_list="APP/Video/video_discuss_list";

    //视频上传
    public static final String getSaveVideo="/APP/User/save_suggest";

    //视频评论
    public  static final String getAdddiscuss="/APP/Video/video_discuss_add";

    //登录接口
    public static final String login="/APP/Basic/user_login";

}
