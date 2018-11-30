package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_Shopping;
import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_ShoppingDetail;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_shoppingDetail_Module {
    @Provides
    public  String provideName() {
        return Fragment_ShoppingDetail.class.getName();
    }
}
