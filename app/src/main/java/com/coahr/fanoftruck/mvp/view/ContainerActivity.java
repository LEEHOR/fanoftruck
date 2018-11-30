package com.coahr.fanoftruck.mvp.view;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;
import com.coahr.fanoftruck.mvp.Base.EventBusBean;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;
import com.coahr.fanoftruck.mvp.view.CallForHelp.CallForHelp_viewPager;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myCar;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_ShoppingDetail;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 16:55
 * 中间跳转页面
 */
public class ContainerActivity extends BaseSupportActivity {

    @Override
    public int binLayout() {
        return R.layout.activity_container;
    }

    @Override
    public void initView() {
        switch (getIntent().getIntExtra("tofragment", 0)) {

            case Constants.Fragment_store://门店列表页面
                loadRootFragment(R.id.con_fragment, Fragment_Store.newInstance(getIntent().getIntExtra("type",0)));
                break;
            case Constants.Fragment_maintenance: //维修视频
                loadRootFragment(R.id.con_fragment, Fragment_MaintenanceVideo_viewPage.newInstance());
                break;
            case Constants.Fragment_shoppingDetail: //商品详情页
                loadRootFragment(R.id.con_fragment, Fragment_ShoppingDetail.newInstance(getIntent().getStringExtra("c_id")));
                break;
            case Constants.Fragment_help:  //一键呼叫
                loadRootFragment(R.id.con_fragment, CallForHelp_viewPager.newInstance());
                break;
            case Constants.Fragment_login:
                loadRootFragment(R.id.con_fragment, Fragment_login.newInstance());
                break;
            case Constants.Fragment_userInfo:
                loadRootFragment(R.id.con_fragment, Fragment_myUerInfo.newInstance());
                break;
            case Constants.Fragment_recommendCar:
                loadRootFragment(R.id.con_fragment, Fragment_RecommendCar.newInstance());
                break;
            case Constants.Fragment_mycar:
                loadRootFragment(R.id.con_fragment, Fragment_myCar.newInstance());
                break;
        }
    }

    @Override
    public void initData() {

    }
}
