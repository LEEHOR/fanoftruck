package com.coahr.fanoftruck.dagger.components;

import com.coahr.fanoftruck.dagger.modules.AllActivityModule;
import com.coahr.fanoftruck.dagger.modules.AllFragmentModule;
import com.coahr.fanoftruck.dagger.modules.BaiduLocationModule;
import com.coahr.fanoftruck.dagger.modules.retrofit.ApiModule;
import com.coahr.fanoftruck.dagger.modules.retrofit.OkHttpModule;
import com.coahr.fanoftruck.dagger.modules.retrofit.RetrofitModule;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;

import javax.inject.Singleton;

import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 13:35
 */

@Singleton
@Component(modules = {
        AndroidInjectionModule.class,
        AndroidSupportInjectionModule.class,
        AllActivityModule.class,
        AllFragmentModule.class,
        ApiModule.class,
        OkHttpModule.class,
        RetrofitModule.class,
        BaiduLocationModule.class
})
public  interface ApplicationComponents {

        void inject(BaseApplication application);

}
