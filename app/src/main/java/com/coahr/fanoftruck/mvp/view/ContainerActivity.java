package com.coahr.fanoftruck.mvp.view;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;
import com.coahr.fanoftruck.mvp.view.CallForHelp.CallForHelp_viewPager;
import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_MaintenanceOrder;
import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_ReservationOrder;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponViewPager;
import com.coahr.fanoftruck.mvp.view.MyWebView.Fragment_myWebView;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_about_us;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_myCar;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;
import com.coahr.fanoftruck.mvp.view.Myself.HelpCenterFragment;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_shoppingCart;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_ShoppingDetail;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoList;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoPlay;

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
                loadRootFragment(R.id.con_fragment, Fragment_Store.newInstance(getIntent().getIntExtra("type", 0)));
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
            case Constants.Fragment_login:  //登录
                loadRootFragment(R.id.con_fragment, Fragment_login.newInstance(getIntent().getIntExtra("fromfragment", 0)));
                break;
            case Constants.Fragment_userInfo://我的个人中心
                loadRootFragment(R.id.con_fragment, Fragment_myUerInfo.newInstance());
                break;
            case Constants.Fragment_recommendCar://推荐购车
                loadRootFragment(R.id.con_fragment, Fragment_RecommendCar.newInstance());
                break;
            case Constants.Fragment_mycar: //我的车辆
                loadRootFragment(R.id.con_fragment, Fragment_myCar.newInstance(getIntent().getIntExtra("tofragment", 0)));
                break;
            case Constants.Fragment_about_us: //关于我们
                loadRootFragment(R.id.con_fragment, Fragment_about_us.newInstance());
                break;
            case Constants.Fragment_videoPlay: //视频播放
                loadRootFragment(R.id.con_fragment, Fragment_home_videoPlay.newInstance());
                break;
            case Constants.Fragment_videoList: //视频列表
                loadRootFragment(R.id.con_fragment, Fragment_home_videoList.newInstance());
                break;
            case Constants.Fragment_MaintenanceOder: //维修订单
                loadRootFragment(R.id.con_fragment, Fragment_MaintenanceOrder.newInstance());
                break;
            case Constants.Fragment_ReservationOrder: //保养订单
                loadRootFragment(R.id.con_fragment, Fragment_ReservationOrder.newInstance());
            break;
            case Constants.Fragment_ShoppingCart: //购物车
                loadRootFragment(R.id.con_fragment, Fragment_shoppingCart.newInstance());
                break;
            case Constants.Fragment_MyWebView:
                loadRootFragment(R.id.con_fragment, Fragment_myWebView.newInstance(getIntent().getStringExtra("url"),
                        getIntent().getStringExtra("title"),
                        getIntent().getIntExtra("type", 0)));
                break;
            case Constants.FRAGMENT_HELP_CENTER://帮助中心
                loadRootFragment(R.id.con_fragment, HelpCenterFragment.newInstance());
                break;
                case Constants.FRAGMEN_MY_COUPON://我的优惠券
                loadRootFragment(R.id.con_fragment, Fragment_CouponViewPager.newInstance(Constants.Fragment_myself, 0));
                break;
        }
    }

    @Override
    public void initData() {

    }
}