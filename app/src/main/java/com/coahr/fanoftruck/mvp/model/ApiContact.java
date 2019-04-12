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

    //单个视频
    public static final String getOneVideo="/APP/Video/view_video";

    //视频点赞
    public static final  String getVideo_dz="APP/Video/video_dz";

    //视频评论
    public static final  String getVideo_discuss_list="APP/Video/video_discuss_list";

    //视频上传
    public static final String video_add="/APP/Video/video_add";

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

    //推荐购车初始化
    public static final String  buyCar_initial_data="/APP/Query/buyCar_initial_data";

    //推荐购车获取验证码
    public static final String  get_buyCar_code="/APP/Basic/get_buyCar_code";

    //推荐购车订单提交
    public static final String save_business_order="/APP/User/save_business_order";

    //个人中心首页数据
    public static final String myself_data="/APP/User/get_userinfo";

    //绑定微信
    public static final String bind_wx="/APP/User/bind_wx";

    //解除绑定微信
    public static final String unset_wx="/APP/User/unset_wx";

    //个人中心初始化数据
    public static final String center_initial_data="/APP/Query/center_initial_data";

    //个人中心数据保存
    public static final String save_identity_info="/APP/User/save_identity_info";

    //实名认证照片上传
    public static final String save_identity_pic="/APP/User/save_identity_pic";

    //用户登出
    public  static final String logout="/APP/Basic/logout";

    //加入购物车
    public  static final String addShoppingCar="/APP/User/add_to_shopping_cart";

    //从购物车中删除
    public static final String delFormShoppingCar="/APP/User/del_from_shopping_cart";

    //购物车数据
    public static final String MyShoppingCar="/APP/User/shopping_cart";

    //获取收货地址列表
    public static final String getAddressList="/APP/User/get_user_address_list";

    //添加
    public static final String add_address="/APP/User/save_user_address";

    //设为默认地址
    public static final String setAddressDefault="/APP/User/set_user_address";

    //删除地址
    public static final String del_address="/APP/User/del_user_address";

    //订单确认接口
    public static final String confirm_order="/APP/User/confirm_commodity_order";

    //优惠券列表
    public static final String couponList="/APP/User/get_coupon_list";

    //优惠券使用
    public static final String used_coupon="/APP/User/get_coupon_selected";

    //优惠券领取列表
    public static final String CouponCollectionList="/APP/User/get_coupon_all";

    //优惠券领取
    public static final String receive_coupon="/APP/User/get_coupon_byself";

    //支付接口
    public static final String payCommodityOrder="/APP/User/save_commodity_order";

    //订单支付接口
    public static final String payImmediatelyOrder="/APP/User/quick_pay";

    //添加发票
    public static final String ADD_INVOICE="http://app.cvfans.net/H5/invoice1.html";

    //个人、企业发票
    public static final String SHOW_INVOICE="http://app.cvfans.net/H5/invoice.html";

    //服务商加盟
    public static final String SERVICE_JOIN="http://app.cvfans.net/H5/user_ready.html";

    //我的订单
    public  static final String MY_ORDER="/APP/User/get_commodity_order_list";
}
