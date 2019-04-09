package com.coahr.fanoftruck.mvp.Base;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.util.Log;

import com.blankj.utilcode.util.ToastUtils;
import com.blankj.utilcode.util.Utils;
import com.coahr.fanoftruck.BuildConfig;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.dagger.components.DaggerApplicationComponents;
import com.coahr.fanoftruck.widgets.MyVideo.MyFileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.socks.library.KLog;
import com.tencent.smtt.sdk.QbSdk;
import com.umeng.commonsdk.UMConfigure;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.MsgConstant;
import com.umeng.message.PushAgent;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.PlatformConfig;

import java.util.Map;

import javax.inject.Inject;

import androidx.fragment.app.Fragment;
import androidx.multidex.MultiDex;
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
    public static final String TAG="fanoftruck";
    //内部封装map管理众多activity的component
    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidActivityInjector;
    //内部封装map管理众多fragment的component
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidFragmentInjector;

    //视频缓存
    private HttpProxyCacheServer proxy;
    private Handler handler;

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
        mContext=getApplicationContext();
        DaggerApplicationComponents.create().inject(this);
        MultiDex.install(mContext);
        UMConfigure.setLogEnabled(true);
        UMConfigure.init(mContext,"5c121050f1f556ac7c000327","umeng",UMConfigure.DEVICE_TYPE_PHONE,"a2ef9522bf3a1c5c206c1e8dac62e363");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        initUpush();
        initX5WebView();
        if (PreferenceUtils.contains(mContext, "token")) {
            Constants.token = PreferenceUtils.getPrefString(mContext, Constants.token_key, null);
            KLog.d("token", Constants.token);
        }
        if (PreferenceUtils.contains(mContext, "sessionId")) {
            Constants.sessionId = PreferenceUtils.getPrefString(mContext, Constants.uid_key, null);
        }

     /*   if (BuildConfig.DEBUG){
            UMConfigure.setLogEnabled(true);
        } else {
            UMConfigure.setLogEnabled(false);
        }*/
        Utils.init(this); //工具类初始化
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


    private void initUpush() {
        PushAgent mPushAgent = PushAgent.getInstance(this);
        handler = new Handler(getMainLooper());
        //sdk开启通知声音
        mPushAgent.setNotificationPlaySound(MsgConstant.NOTIFICATION_PLAY_SDK_ENABLE);


        /**
         * 自定义行为的回调处理，参考文档：高级功能-通知的展示及提醒-自定义通知打开动作
         * UmengNotificationClickHandler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {

            @Override
            public void launchApp(Context context, UMessage msg) {

                super.launchApp(context, msg);
            }

            @Override
            public void openUrl(Context context, UMessage msg) {

                super.openUrl(context, msg);
            }

            @Override
            public void openActivity(Context context, UMessage msg) {

                super.openActivity(context, msg);
            }

            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
            super.dealWithCustomAction(context, msg);
            }
        };
        //使用自定义的NotificationHandler
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
        //注册推送服务 每次调用register都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                PreferenceUtils.setPrefString(BaseApplication.mContext,Constants.devicestoken_key,deviceToken);
                Constants.devicestoken=deviceToken;
                KLog.d(TAG, "deviceToken: " + deviceToken);
                ToastUtils.showLong(deviceToken);
                //sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }

            @Override
            public void onFailure(String s, String s1) {
                KLog.d(TAG, "registerFailed: " + s + " " + s1);
                //  sendBroadcast(new Intent(UPDATE_STATUS_ACTION));
            }
        });

        PlatformConfig.setWeixin("wx5441f32a77ce5750", "69870b0cd66594cf4a6c9cc66adf1087");//微信APPID和AppSecret

    }
}
