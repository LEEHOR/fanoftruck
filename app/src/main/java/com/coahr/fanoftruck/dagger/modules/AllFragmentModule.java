package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.dagger.components.BaseFragmentComponents;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_Business_viewPager;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;
import com.coahr.fanoftruck.mvp.view.CallForHelp.CallForHelp_viewPager;
import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;
import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.Fragment_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_MaintenanceOrder;
import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_ReservationOrder;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_add_address;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_address_list;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_addCar;
import com.coahr.fanoftruck.mvp.view.MyCar.Fragment_myCar;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponReceive;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponViewPager;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_coupon_main;
import com.coahr.fanoftruck.mvp.view.MyOrder.Fragment_OrderViewPager;
import com.coahr.fanoftruck.mvp.view.MyOrder.Fragment_Order_pager;
import com.coahr.fanoftruck.mvp.view.MyWebView.Fragment_myWebView;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_about_us;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_certification;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_forgetPass;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_register;
import com.coahr.fanoftruck.mvp.view.Myself.HelpCenterFragment;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.FragmentRecorder;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.Fragment_recorder_Preview;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Services;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_storeOrder;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_ShoppingDetail;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_dialog_shopping;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_shoppingCart;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_discuss;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoList;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_home_videoPlay;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_child;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_videoPlay;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:39
 */
@Module(subcomponents = {
        BaseFragmentComponents.class
})
public abstract class AllFragmentModule {
    @ContributesAndroidInjector(modules = Fragment_home_Module.class)
    abstract Fragment_Home Fragment_HomeInjector();

    @ContributesAndroidInjector(modules = Fragment_business_viewPager_Module.class)
    abstract Fragment_Business_viewPager Fragment_BusinessInjector();

    @ContributesAndroidInjector(modules = Fragment_shopping_Module.class)
    abstract Fragment_Shopping Fragment_ShoppingInjector();

    @ContributesAndroidInjector(modules = Fragment_services_Module.class)
    abstract Fragment_Services Fragment_ServicesInjector();

    @ContributesAndroidInjector(modules = Fragment_myself_Module.class)
    abstract Fragment_Myself Fragment_MyselfInjector();

    @ContributesAndroidInjector(modules = Fragment_store_Module.class)
    abstract Fragment_Store Fragment_StoreInjector();

    @ContributesAndroidInjector(modules = Fragment_store_detail_Module.class)
    abstract Fragment_store_detail Fragment_store_detailInjector();

    @ContributesAndroidInjector(modules = Fragment_maintenance_viewp_Module.class)
    abstract Fragment_MaintenanceVideo_viewPage Fragment_MaintenanceVideo_viewPageInjector();

    @ContributesAndroidInjector(modules = Fragment_maintenance_child_Module.class)
    abstract Fragment_maintenance_child Fragment_maintenance_childInjector();

    @ContributesAndroidInjector(modules = Fragment_maintenance_videoPlay_Module.class)
    abstract Fragment_maintenance_videoPlay Fragment_maintenance_videoPlayInjector();

    @ContributesAndroidInjector(modules = Fragment_recorder_Module.class)
    abstract FragmentRecorder FragmentRecorderInjector();

    @ContributesAndroidInjector(modules = Fragment_shoppingDetail_Module.class)
    abstract Fragment_ShoppingDetail Fragment_ShoppingDetailInjector();

    @ContributesAndroidInjector(modules = Fragment_store_order_Module.class)
    abstract Fragment_storeOrder Fragment_storeOrderInjector();

    @ContributesAndroidInjector(modules = Fragment_home_Module.class)
    abstract Fragment_help  Fragment_helpInjector();


    @ContributesAndroidInjector(modules = Fragment_recorder_preview_Module.class)
    abstract Fragment_recorder_Preview  Fragment_recorder_PreviewInjector();

    @ContributesAndroidInjector(modules = Fragment_CallForHelp_viewPager_Module.class)
    abstract CallForHelp_viewPager CallForHelp_viewPagerInjector();

    @ContributesAndroidInjector(modules = Fragment_Login_module.class)
    abstract Fragment_login  Fragment_loginInjector();

    @ContributesAndroidInjector(modules = Fragment_discuss_Module.class)
    abstract Fragment_discuss Fragment_discussInjector();

    @ContributesAndroidInjector(modules = Fragment_UserInfo_Module.class)
    abstract Fragment_myUerInfo Fragment_myUerInfoInjector();

    @ContributesAndroidInjector(modules = Fragment_register_module.class)
    abstract Fragment_register Fragment_registerInjector();

    @ContributesAndroidInjector(modules = Fragment_forgetPass_module.class)
    abstract Fragment_forgetPass Fragment_forgetPassInjector();

    @ContributesAndroidInjector(modules = Fragment_forgetPass_module.class)
    abstract Fragment_appointment Fragment_appointmentInjector();

    @ContributesAndroidInjector(modules = Fragment_recommendCar_Module.class)
    abstract Fragment_RecommendCar Fragment_RecommendCarInjector();

    @ContributesAndroidInjector(modules = Fragment_myCar_Module.class)
    abstract Fragment_myCar Fragment_myCarInjector();

    @ContributesAndroidInjector(modules = Fragment_addCar_Module.class)
    abstract Fragment_addCar Fragment_addCarInjector();

    @ContributesAndroidInjector(modules = Fragment_about_us_Module.class)
    abstract Fragment_about_us Fragment_about_usInjector();

    @ContributesAndroidInjector(modules = HelpCenterFragmentModule.class)
    abstract HelpCenterFragment HelpCenterFragmentInjector();

    @ContributesAndroidInjector(modules = Fragment_certification_Module.class)
    abstract Fragment_certification Fragment_certificationInjector();

    @ContributesAndroidInjector(modules = Fragment_home_videoList_Module.class)
    abstract Fragment_home_videoList Fragment_home_videoListInjector();

    @ContributesAndroidInjector(modules = Fragment_home_videoplay_Module.class)
    abstract Fragment_home_videoPlay Fragment_home_videoPlayInjector();

    @ContributesAndroidInjector(modules = Fragment_MaintenanceOrder_Module.class)
    abstract Fragment_MaintenanceOrder Fragment_MaintenanceOrderInjector();

    @ContributesAndroidInjector(modules = Fragment_ReservationOrderr_Module.class)
    abstract Fragment_ReservationOrder Fragment_ReservationOrderInjector();

    @ContributesAndroidInjector(modules = Fragment_dialog_Shoping_Module.class)
    abstract Fragment_dialog_shopping  Fragment_dialog_shoppingInjector();

    @ContributesAndroidInjector(modules = Fragment_shoppingCart_Module.class)
    abstract Fragment_shoppingCart Fragment_shoppingCarInjector();

    @ContributesAndroidInjector(modules = Fragment_address_list_module.class)
    abstract Fragment_address_list Fragment_address_listInjector();

    @ContributesAndroidInjector(modules = Fragment_add_address_Module.class)
    abstract Fragment_add_address Fragment_addressInjector();

    @ContributesAndroidInjector(modules = Fragment_confirmCommoityOrder_Module.class)
    abstract Fragment_confirmCommodityOrder  Fragment_confirmCommodityOrderInjector();

    @ContributesAndroidInjector(modules = Fragment_CouponViewPager_Module.class)
    abstract Fragment_CouponViewPager Fragment_CouponViewPagerInjector();

    @ContributesAndroidInjector(modules = Fragment_coupon_main_Module.class)
    abstract Fragment_coupon_main Fragment_coupon_mainInjector();

    @ContributesAndroidInjector(modules = Fragment_CouponReceive_Module.class)
    abstract Fragment_CouponReceive Fragment_CouponReceiveInjector();

    @ContributesAndroidInjector(modules = Fragment_MyWebView_Module.class)
    abstract Fragment_myWebView Fragment_myWebViewInjector();

    @ContributesAndroidInjector(modules = Fragment_Order_viewPage_Module.class)
    abstract Fragment_OrderViewPager Fragment_myOrderPage();

    @ContributesAndroidInjector(modules = Fragment_Order_pager_Module.class)
    abstract Fragment_Order_pager Fragment_Order_pager();
}
