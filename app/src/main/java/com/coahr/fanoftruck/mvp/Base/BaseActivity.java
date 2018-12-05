package com.coahr.fanoftruck.mvp.Base;

import android.Manifest;
import android.app.Dialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.Permission.OnRequestPermissionListener;
import com.coahr.fanoftruck.Utils.Permission.RequestPermissionUtils;
import com.coahr.fanoftruck.commom.ActivityManager;
import com.gyf.barlibrary.ImmersionBar;


import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.AndroidInjection;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 16:44
 */
public abstract class BaseActivity<P extends BaseContract.Presenter> extends SupportActivity {
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();


    private Dialog dialog;

    private Unbinder unbinder;

    public abstract P getPresenter();

    public abstract int binLayout();

    public abstract void initView();

    public abstract void initData();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
       AndroidInjection.inject(this);  //一处声明，处处依赖注入
        super.onCreate(savedInstanceState);
        setContentView(binLayout());
        KeyBoardUtils.UpdateUI(getWindow().getDecorView().getRootView(), this);
        //手机顶部状态栏颜色适配
        ImmersionBar.with(this)
                .statusBarDarkFont(true, 0.2f) //原理：如果当前设备支持状态栏字体变色，会设置状态栏字体为黑色，如果当前设备不支持状态栏字体变色，会使当前状态栏加上透明度，否则不执行透明度
                .init();
        unbinder = ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        getLocationPermission();
        initView();
        initData();
    }
    public void showLoading() {

    }
    public void dismissLoading() {

    }
    public void showError(@Nullable Throwable e) {
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        ActivityManager.getInstance().removeActivity(this);
        ImmersionBar.with(this).destroy();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }
    }
    /**
     * 动态获取定位
     */
    private void getLocationPermission() {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            RequestPermissionUtils.requestPermission(BaseActivity.this, new OnRequestPermissionListener() {
                @Override
                public void PermissionSuccess(List<String> permissions) {

                }

                @Override
                public void PermissionFail(List<String> permissions) {
                    Toast.makeText(BaseActivity.this, "获取权限失败", Toast.LENGTH_LONG).show();
                }

                @Override
                public void PermissionHave() {
                }
            }, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_WIFI_STATE
                    ,Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE);

        } else {
        }
    }
}
