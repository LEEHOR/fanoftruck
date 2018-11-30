package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Services.Fragment_storeOrder;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_store_order_Module {
    @Provides
    public  String provideName() {
        return Fragment_storeOrder.class.getName();
    }
}
