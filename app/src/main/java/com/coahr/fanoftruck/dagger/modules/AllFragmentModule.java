package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.dagger.Fragment_store_detail_Module;
import com.coahr.fanoftruck.dagger.components.BaseFragmentComponents;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_Business;
import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_business_child;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_Home;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_MaintenanceVideo_viewPage;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_child;
import com.coahr.fanoftruck.mvp.view.Home.Fragment_maintenance_videoPlay;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_Myself;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Services;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;

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

    @ContributesAndroidInjector(modules = Fragment_business_Module.class)
    abstract Fragment_Business Fragment_BusinessInjector();

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
}
