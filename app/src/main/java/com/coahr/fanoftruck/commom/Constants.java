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
    //ht_projectId
    public static String ht_ProjectId;  //服务器端项目id
    //user_id;
    public static String user_id;
    //DbProjectId 数据库中的id
    public static String DbProjectId;

    //网络访问超时时间
    public static int timeout=15;
    //首页定位次数
    public static int location_counts;

    //SDCard路径
    public static String SDCARD_PATH = StoreSpaceUtils.getSDCardPath();

    /**
     * 本地存储总目录
     */
    public static String SAVE_DIR_BASE = SDCARD_PATH.concat("/com.thoughtRui.coahr/");
    /**
     *
     */
    public static String SAVE_DIR_GLIDE_CACHE=SAVE_DIR_BASE.concat("GlideCache/");
    /**
     * 相机拍摄图片存储位置
     */
    public static String SAVE_DIR_TAKE_PHOTO = SAVE_DIR_BASE.concat("takePhoto/");
    /**
     * 工程图片位置
     */
    public static String SAVE_DIR_PROJECT_PHOTO = SAVE_DIR_BASE.concat("projectPhoto/");
    /**
     * 压缩图片存储位置
     */
    public static String SAVE_DIR_ZIP_PHOTO = SAVE_DIR_BASE.concat("zipPhoto/");
    /**
     * 头像存储位置
     */
    public static String SAVE_DIR_ICON = SAVE_DIR_BASE.concat("icon/");
    /**
     * 视频存储位置
     */
    public static String SAVE_DIR_VIDEO = SAVE_DIR_BASE.concat("video/");
    /**
     * 语音存储位置
     */
    public static String SAVE_DIR_VOICE = SAVE_DIR_BASE.concat("voice/");
    /**
     * 奔溃存储路径
     */
    public static String SAVE_DIR_CRASH = SAVE_DIR_BASE.concat("crash/");

    //跳转常数
    public  static final int Fragment_home=1;

    public  static final int Fragment_business=2;

    public  static final int Fragment_shopping=3;

    public  static final int Fragment_services=4;

    public static  final int Fragment_myself=5;

    public static  final int Fragment_store=6;

    public static  final  int Fragment_Store_Detail=7;

    public static  final int Fragment_maintenance=8; //维修视频




    /**
     * PreferenceUtils键
     */
    //sessionId_key
    public static  String sessionId_key;
    //user_key
    public static  String user_key;
}
