package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.Fragment_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_add_address;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/25
 * on 23:01
 */
@Module
public class Fragment_confirmCommoityOrder_Module {
    @Provides
    public  String provideName() {
        return Fragment_confirmCommodityOrder.class.getName();
    }
}
