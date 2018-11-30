package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.dagger.components.BaseFragmentComponents;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_Business_viewPager;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_RecommendCar;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_business_child;
import com.coahr.fanoftruck.mvp.view.CallForHelp.CallForHelp_viewPager;
import com.coahr.fanoftruck.mvp.view.CallForHelp.Fragment_help;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_addCar;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_forgetPass;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myCar;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_myUerInfo;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_register;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.Fragment_recorder_Preview;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_discuss;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_child;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_maintenance_videoPlay;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;
import com.coahr.fanoftruck.mvp.view.RecorderVideo.FragmentRecorder;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Services;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_storeOrder;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_ShoppingDetail;

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

    @ContributesAndroidInjector(modules = Fragment_business_child_Module.class)
    abstract Fragment_business_child Fragment_business_childInjector();

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
}
