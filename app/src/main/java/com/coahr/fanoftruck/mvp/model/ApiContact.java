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

    //注册验证码接口
    public static final String getRegisterVerifyCode="/APP/Basic/get_verify_code";

    //注册接口
    public static final String registerAccount="/APP/Basic/register_user";

    //找回密码验证码
    public static final String forgertPass_VerifyCode="/APP/Basic/get_modify_code";

    //忘记密码
    public static final String forgertPass="/APP/Basic/retrieve_password";

    //保存车辆
    public static final String Save_use_Car="/APP/User/save_user_car";

    //设置默认车辆
    public static final String set_car_default="/APP/User/set_user_car_default";

    //获取车辆列表
    public static final String Car_list="/APP/User/get_user_car_list";

    //删除车辆

    public static final String del_car="/APP/User/del_user_car";

    //预约保养生成订单
    public static final String  appointment="/APP/User/save_service_order";

    //预约保养默认加载
    public static final String appointmentDefault="/APP/Query/service_initial_data";

}
