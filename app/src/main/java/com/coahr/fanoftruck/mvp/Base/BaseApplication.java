package com.coahr.fanoftruck.mvp.Base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.multidex.MultiDexApplication;
import android.support.v4.app.Fragment;
import android.support.v7.widget.AppCompatImageView;

import com.coahr.fanoftruck.dagger.components.DaggerApplicationComponents;
import com.coahr.fanoftruck.widgets.MyVideo.MyFileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.mob.MobSDK;
import com.mob.bbssdk.theme1.BBSTheme1;
import com.taobao.sophix.PatchStatus;
import com.taobao.sophix.SophixManager;
import com.taobao.sophix.listener.PatchLoadStatusListener;
import com.tencent.smtt.sdk.QbSdk;
import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 11:45
 */
public class BaseApplication extends Application implements HasActivityInjector, HasSupportFragmentInjector {
    public interface MsgDisplayListener {
        void CODE_DOWNLOAD_SUCCESS(String Info,int HandlePatchVersion );
        void CODE_LOAD_RELAUNCH(String Info,int HandlePatchVersion);
        void CODE_LOAD_MFITEM(String Info,int HandlePatchVersion);
        void Other(int code,String Info,int HandlePatchVersion);
    }
    private static MsgDisplayListener listeners;
    public static Context mContext;
    //内部封装map管理众多activity的component
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;
    //内部封装map管理众多fragment的component
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

    //视频缓存
    private HttpProxyCacheServer proxy;

    public static HttpProxyCacheServer getProxy(Context context) {
        BaseApplication app = (BaseApplication) context.getApplicationContext();
        return app.proxy == null ? (app.proxy = app.newProxy()) : app.proxy;
    }

    private HttpProxyCacheServer newProxy() {
        return new HttpProxyCacheServer.Builder(this)
                .maxCacheSize(1024 * 1024 * 1024)       // 1 Gb for cache
                .fileNameGenerator(new MyFileNameGenerator())
                .build();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        DaggerApplicationComponents.create().inject(this);
        MobSDK.init(this);
        BBSTheme1.init();
        mContext=getApplicationContext();
        initX5WebView();
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
    }


    /**
     * 初始化阿里热更新
     */
    private void initSophix(){
        SophixManager.getInstance().setContext(this)
                .setAppVersion(getAppVersion())
                .setAesKey(null)
                .setSecretMetaData("25246895-1","ae9c7e03af7eff1738413963c49dc6f8","MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCP+Qcw+Fo4eS0sBWyEI0DeVxpEes2QX7Fo1Bd9MU/KigDDmdJBa4eSwIwKHE6PzKPKjSiD6kevd3zQk+P4mthOfXWvEeTEARAPQfUh2Yn+/dZYiIIIlIwuFfdYclRIe6gIN/ikQl/I7UChqhNHXii4lAx/EA2E5s7s8mYg0jiBvrwM10ybLPbibCHhI2qfhDV0VZJl/3j6/9QyLizL/oNgcKq2iU9diQnym0i4Du/0H9pjoV+KDYorbFyFPsoxrqynlIQWmhMyARwsrDvEcuaHqN210WLmiU/Lsgs+K7DpKaKl+U0L/+JxVPxaLG4OHaLPY58YSdHwuzUnTRn8glkHAgMBAAECggEAGqLrJq88GeipZ9SVEBlpnfklfffuYkCiFQ/wTJQ7++/uVkH2OW5Ihekjv5cfHdGxl/7nuQg2PQtxRzGlYtBbJqJljH9WAMFllD0SujGCK3ARIQ4YaLf6+2fK/E0itMpT+zlHwjQZfpYOeBp8OJ7+D7AsY7G3npgJPqNWK8pY8//rwPXNX2e6Bm+qtupgchTarV+JaT0XPuSN8SAAc/p57fZDIcxb6gcLMSSzJnMFL2G1lLyg4+ryu08aHAVuff8Ngi2+03D60yJS0o60PlslIHopLJ31ooVb/t5aHh4HgNIkEqD8uMkaHYrc2Exy0R9+4Sr0v10WFEBc2xQOMD/dAQKBgQDE+2Qf7VTaqIWLl1oT2NNBCEA3mLrYQEXyGlY893cUb2KvGnlXOQCnXYh/f+FIkTRGJrgYoCv0U0osXomdosGQnE8a2RLDg6s5lwPHalN98qraa7OZZE2JdHN+OFoguPCupwOk2VCZwR+eObpFM5hp0eh766apXwg9EiwSoOvihwKBgQC7G84KIhescslnuIujUttVn53g5dVfAPQYqC0IYq/jenTjOOyx4+fQIsSIlpKgOHE6SRFxTDsXqkdcJiBQULxZGV6Zasy/Y70cA/xMzizDW0MabzsbvDttEYqqO9g+OrPFosGQF14gSOare5t/R6C7u0BxpWjVexPK+ZlZL9b1gQKBgCzKVOwFlUPbCfLByZO1u+OPsrpxmhrOEpmIb4+RXZxh73iNdoXkykdEN2N2eo17pv1ElJvU3+nAfp80J+qWNZliUYJzVZbre6WutwdDIBUFduAPmVkJu6/DsPdEbQn/w4qTI3r6hx9PEJdz3O0bXE7Dki+LV+wvVbo9RZQ+zyRFAoGARoY+bPdYsPk7DMs0ZsUOQwG8wk4e1IZJexm4j5aS8Uzwzcxg+tQKRhs0tL78WUOs+ekcl/XBuDPIXeVI/LzRoUu8qlrk/aacWvjtmg2ENcYqTsZqIxQZ2gxola2k3h/GLtIf3y3Pnp+bwjr+60SWQuxbZ/qOvxh652CY+lLb1QECgYAS4iG5KNm77NL4V7qurBwMV/bujiIh2IBR5VVXs+DsGfnSca9SRXskzuwyY6wiFbZuEDXt9Ch+szvZUJ1W9NVjTacb5HJJ7L1Py4O0FeLK2gcH9HoxmLKtBdvZKxq9AAZ+IGfQnGNTuRR0+b1R/zvTzvEOz0pawrPo3hfr71h2UA==")
                .setEnableDebug(true)
                .setPatchLoadStatusStub(new PatchLoadStatusListener() {
                    @Override
                    public void onLoad(final int mode, final int code, final String info, final int handlePatchVersion) {

                        // 补丁加载回调通知
                        if (code == PatchStatus.CODE_DOWNLOAD_SUCCESS) {
                            // 表明补丁加载成功
                            if (listeners != null) {
                                listeners.CODE_DOWNLOAD_SUCCESS(info,handlePatchVersion);
                            }
                        } else if (code == PatchStatus.CODE_LOAD_RELAUNCH) {
                            // 表明新补丁生效需要重启. 开发者可提示用户或者强制重启;
                            // 建议: 用户可以监听进入后台事件, 然后应用自杀
                            if (listeners != null) {
                                listeners.CODE_LOAD_RELAUNCH(info,handlePatchVersion);
                            }
                        } else if (code == PatchStatus.CODE_LOAD_MFITEM) {////补丁SOPHIX.MF文件解析异常
                            // 内部引擎异常, 推荐此时清空本地补丁, 防止失败补丁重复加载
                            //SophixManager.getInstance().cleanPatches();
                            if (listeners != null) {
                                listeners.CODE_LOAD_MFITEM(info,handlePatchVersion);
                            }
                        } else {
                            // 其它错误信息, 查看PatchStatus类说明
                            if (listeners != null) {
                                listeners.Other(code,info,handlePatchVersion);
                            }
                        }
                    }
                }).initialize();
    }
    private String getAppVersion() {
        String appVersion;
        try {
            appVersion= this.getPackageManager().getPackageInfo(this.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            appVersion = "1.0.0";
        }
        return  appVersion;
    }

    /**
     * 初始化X5Web
     */
    private void initX5WebView(){
        QbSdk.PreInitCallback cb = new QbSdk.PreInitCallback() {

            @Override
            public void onViewInitFinished(boolean arg0) {
                //x5內核初始化完成的回调，为true表示x5内核加载成功，否则表示x5内核加载失败，会自动切换到系统内核。

            }

            @Override
            public void onCoreInitFinished() {
            }
        };
        //x5内核初始化接口
        QbSdk.initX5Environment(getApplicationContext(), cb);

    }
    public static void setListener(MsgDisplayListener listener) {
        listeners = listener;
    }
}
