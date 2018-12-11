package com.coahr.fanoftruck.mvp.Base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.Fragment;

import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.dagger.components.DaggerApplicationComponents;
import com.coahr.fanoftruck.widgets.MyVideo.MyFileNameGenerator;
import com.danikula.videocache.HttpProxyCacheServer;
import com.socks.library.KLog;
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

        if (PreferenceUtils.contains(mContext, "token")) {
            Constants.token = PreferenceUtils.getPrefString(mContext, Constants.token_key, "");
            KLog.d("token", Constants.token);
        }
        if (PreferenceUtils.contains(mContext, "sessionId")) {
            Constants.sessionId = PreferenceUtils.getPrefString(mContext, Constants.uid_key, "");
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
}
