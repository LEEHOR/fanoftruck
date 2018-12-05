package com.coahr.fanoftruck.mvp.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ActivityManagerUtils;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseActivity;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.model.Bean.EventBusBean;
import com.coahr.fanoftruck.mvp.constract.MainActivityC;
import com.coahr.fanoftruck.mvp.presenter.MainActivityP;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_Business_viewPager;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Services;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;
import com.coahr.fanoftruck.widgets.MyBottomNavigation.MyBottomNavigation;
import com.socks.library.KLog;


import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import javax.inject.Inject;

import butterknife.BindView;
import me.yokeyword.fragmentation.SupportFragment;



public class MainActivity extends BaseActivity<MainActivityC.Presenter> implements MainActivityC.View {

    @Inject
    MainActivityP p;
    @BindView(R.id.myBottom)
    MyBottomNavigation myBottomNavigation;
    //正在展示的fragment的position
    private int bottomNavigationPreposition = 0;
    private SupportFragment[] mFragments = new SupportFragment[5];
    private long exitTime = 0;
    private static final long INTERVAL_TIME = 2000;

    @Override
    public MainActivityC.Presenter getPresenter() {
        return p;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            mFragments[0] = findFragment(Fragment_Home.class);
            mFragments[1] = findFragment(Fragment_Shopping.class);
            mFragments[2] = findFragment(Fragment_Business_viewPager.class);
            mFragments[3] = findFragment(Fragment_Services.class);
            mFragments[4] = findFragment(Fragment_Myself.class);
        } else {
            mFragments[0] = Fragment_Home.newInstance();
            mFragments[1] = Fragment_Shopping.newInstance();
            mFragments[2] = Fragment_Business_viewPager.newInstance();
            mFragments[3] = Fragment_Services.newInstance();
            mFragments[4] = Fragment_Myself.newInstance();
        }
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public int binLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
         loadMultipleRootFragment(R.id.Root_Fragment, 0, mFragments);

    }

    @Override
    public void initData() {
        myBottomNavigation.setOnTabPositionListener(new MyBottomNavigation.OnTabPositionListener() {
            @Override
            public void onPositionTab(int position) {
                if (position == 4 && !PreferenceUtils.contains(BaseApplication.mContext, Constants.token_key)) {
                    Intent intent = new Intent(MainActivity.this,ContainerActivity.class);
                    intent.putExtra("fromfragment",Constants.MainActivity);
                    intent.putExtra("tofragment",Constants.Fragment_login);
                    startActivity(intent);

                } else {
                    showFragment(position);
                }

            }
        });

    }
    private void showFragment(int position) {
        showHideFragment(mFragments[position], mFragments[bottomNavigationPreposition]);
        myBottomNavigation.beanSelect(position);
        bottomNavigationPreposition = position;
    }

    @Override
    public void onBackPressedSupport() {
        if ((System.currentTimeMillis() - exitTime) > INTERVAL_TIME) {
            ToastUtils.showLong(getResources().getString(R.string.carsuper_exit));
            exitTime = System.currentTimeMillis();
        } else {
            finish();
            if (Constants.isKill){
                ActivityManagerUtils.getInstance().appExit();
            } else {
                android.os.Process.killProcess(android.os.Process.myPid());
            }
        }
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == 100) {
            KLog.d("回来了");
            // ((Fragment_myUerInfo) mFragments[4])

        }
    }

    /**
     * 登陆成功回调
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void receiveMessage(EventBusBean event) {
        if (event.getCode() == 1) {
            KLog.d("接受");
            showFragment(4);
        }
    }

    @Override
    public void onDestroy() {

        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
        super.onDestroy();
    }
}
