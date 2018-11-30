package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_appointment;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:02
 */
@Module
public class Fragment_appointment_Module {
    @Provides
    public  String provideName() {
        return Fragment_appointment.class.getName();
    }
}
