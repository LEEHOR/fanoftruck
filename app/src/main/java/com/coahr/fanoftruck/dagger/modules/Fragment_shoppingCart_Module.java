package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.Shopping.Fragment_shoppingCart;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 12:55
 */
@Module
public class Fragment_shoppingCart_Module {
    @Provides
    public  String provideName() {
        return Fragment_shoppingCart.class.getName();
    }
}
