package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_address_list;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/27
 * on 16:02
 */
@Module
public class Fragment_address_list_module {
    @Provides
    public  String provideName() {
        return Fragment_address_list.class.getName();
    }
}
