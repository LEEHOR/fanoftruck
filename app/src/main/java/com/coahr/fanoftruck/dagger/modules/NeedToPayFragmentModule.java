package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MyOrder.NeedToPayFragment;

import dagger.Module;
import dagger.Provides;

@Module
public class NeedToPayFragmentModule {
    @Provides
    public String provideName(){
        return NeedToPayFragment.class.getName();
    }
}