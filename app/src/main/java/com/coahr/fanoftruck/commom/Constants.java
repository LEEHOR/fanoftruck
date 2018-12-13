package com.coahr.fanoftruck.commom;

import com.coahr.fanoftruck.Utils.StoreSpaceUtils;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 11:23
 * 基础配置
 */
public class Constants {
    //是否需要自杀进程以便打补丁
    public static boolean isKill=true;
    //Latitude
    public  static  double Latitude;
    //Longitude
    public static double Longitude;
    //sessionId
    public static String sessionId;
    //token
    public static String token;
    //uid
    public static String uid;
    //devicestoken
    public static String devicestoken;

    //网络访问超时时间
    public static int timeout=15;
    //首页定位次数
    public static int location_counts;

    //微信开放平台
    public static final String wx_app_id = "wx89f3b1477df1aa39";

    public static final String API_KEY = "carsuper1201wechat905appweixinpy";

    //正在分享
    public  static String ShareNow="";

    //SDCard路径
    public static String SDCARD_PATH = StoreSpaceUtils.getSDCardPath();

    /**
     * 本地存储总目录
     */
    public static String SAVE_DIR_BASE = SDCARD_PATH.concat("/com.fanoftruck.coahr/");
    /**
     *
     */
    public static String SAVE_DIR_GLIDE_CACHE=SAVE_DIR_BASE.concat("GlideCache/");
    /**
     * 相机拍摄图片存储位置
     */
    public static String SAVE_DIR_TAKE_PHOTO = SAVE_DIR_BASE.concat("takePhoto/");
    /**
     * 上传视频时封面图片位置
     */
    public static String SAVE_DIR_PHOTO = SAVE_DIR_BASE.concat("Photo/");
    /**
     * 头像存储位置
     */
    public static String SAVE_DIR_ICON = SAVE_DIR_BASE.concat("icon/");
    /**
     * 视频存储位置
     */
    public static String SAVE_DIR_VIDEO = SAVE_DIR_BASE.concat("video/");

    /**
     * 压缩视频存储位置
     */
    public static String SAVE_DIR_ZIPVIDEO=SAVE_DIR_BASE.concat("zipVideo/");
    /**
     * 语音存储位置
     */
    public static String SAVE_DIR_VOICE = SAVE_DIR_BASE.concat("voice/");
    /**
     * 奔溃存储路径
     */
    public static String SAVE_DIR_CRASH = SAVE_DIR_BASE.concat("DebugCrash/");

    public static  String LOG_DIR_CRASH=SAVE_DIR_BASE.concat("LogCrash");

    //跳转常数
    public  static final int Fragment_home=1;

    public  static final int Fragment_business=2;



    public  static final int Fragment_shopping=3;

    public  static final int Fragment_services=4;

    public static  final int Fragment_myself=5;

    public static  final int Fragment_store=6;

    public static  final  int Fragment_Store_Detail=7;

    public static  final int Fragment_maintenance=8; //维修视频

    public static  final int Fragment_recorder=9;  //录制视频

    public static final int Fragment_shoppingDetail=10;

    public static final int Fragment_help=11;  //一键呼救

    public  static final int Fragment_login=12;

    public static final int Fragment_userInfo=13;

    public static final int Fragment_register=14;

    public static final int Fragment_forgetPass=15;

    public static final int Fragment_recommendCar=16;

    public static final int Fragment_mycar=17;


    public static final int Fragment_appointment=18;


    public static final int MainActivity=19;

    public static final int Fragment_about_us=20;

    public static final int Fragment_videoPlay=21;

    public static final int Fragment_videoList=22;

    public static final int Fragment_MaintenanceOder=23;

    public static final  int Fragment_ReservationOrder=24;

    public static final int Fragment_MaintenanceVideo_viewPage=25;

    public static final int Fragment_myUserInfo=26;

    public static final int Fragment_ShoppingCart=27;

    public static final int Fragment_confirmCommodityOrder=28; //订单确认面



    /**
     * PreferenceUtils键
     */
    //sessionId_key
    public static  String sessionId_key="sessionId";
    //user_key
    public static  String user_key="user";
    //token_key
    public static  String token_key="token";
    //uid
    public static  String uid_key="uid";
    //devicestoken
    public static  String devicestoken_key="devicestoken";
}
