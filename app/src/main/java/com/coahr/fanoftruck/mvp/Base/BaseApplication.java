package com.coahr.fanoftruck.mvp.Base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.coahr.fanoftruck.BuildConfig;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.dagger.components.DaggerApplicationComponents;
import com.coahr.fanoftruck.widgets.MyVideo.MyFileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDexApplication;
import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;
import dagger.android.support.HasSupportFragmentInjector;


/**
 * Created by Leehor
 * on 2018/11/6
 * on 11:45
 */
public class BaseApplication extends MultiDexApplication implements HasActivityInjector, HasSupportFragmentInjector {

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
        mContext=getApplicationContext();
        initX5WebView();
//        UMConfigure.init(this, UMConfigure.DEVICE_TYPE_PHONE, "a2ef9522bf3a1c5c206c1e8dac62e363");
        UMConfigure.init(this,"5c121050f1f556ac7c000327","umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wx5441f32a77ce5750", "69870b0cd66594cf4a6c9cc66adf1087");//微信APPID和AppSecret

        if (PreferenceUtils.contains(mContext, "token")) {
            Constants.token = PreferenceUtils.getPrefString(mContext, Constants.token_key, null);
            KLog.d("token", Constants.token);
        }
        if (PreferenceUtils.contains(mContext, "sessionId")) {
            Constants.sessionId = PreferenceUtils.getPrefString(mContext, Constants.uid_key, null);
        }
        initPush();
        if (BuildConfig.DEBUG){
          //  UMConfigure.setLogEnabled(true);
        } else {
           // UMConfigure.setLogEnabled(false);
        }
    }

    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidActivityInjector;
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidFragmentInjector;
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

    private void initPush() {

    /*    PushAgent mPushAgent = PushAgent.getInstance(this);
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                KLog.d(TAG, "device token: " + deviceToken);
                Constants.devicestoken = deviceToken;
                PreferenceUtils.setPrefString(mContext, Constants.devicestoken_key, deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                KLog.d(TAG, "register failed: " + s + " " + s1);
            }
        });
        PushAgent.getInstance(mContext).onAppStart();

        //自定义消息处理
        mPushAgent.setPushIntentServiceClass(UmengNotificationService.class);*/
    }
}
