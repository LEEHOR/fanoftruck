package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.dagger.components.BaseActivityComponents;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.MainActivity;
import com.coahr.fanoftruck.mvp.view.TribuneActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:38
 */
@Module(subcomponents = {
        BaseActivityComponents.class
})
public abstract class AllActivityModule {

    @ContributesAndroidInjector(modules = MainActivity_Module.class)
    abstract MainActivity    MainActivityInjector();
    @ContributesAndroidInjector(modules = ContainerActivity_Module.class)
    abstract ContainerActivity ContainerActivityInjector();

    @ContributesAndroidInjector(modules = TribuneAcyivity_Module.class)
    abstract TribuneActivity TribuneActivityInjector();

}
