package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.BusinessOpportunity.Fragment_business_child;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:40
 */
@Module
public class Fragment_business_child_Module {
    @Provides
    public  String provideName() {
        return Fragment_business_child.class.getName();
    }
}
