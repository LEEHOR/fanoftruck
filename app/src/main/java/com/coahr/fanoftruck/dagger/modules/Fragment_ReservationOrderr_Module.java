package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_MaintenanceOrder;
import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_ReservationOrder;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/12/5
 * on 17:52
 */
@Module
public class Fragment_ReservationOrderr_Module {
    @Provides
    public String provideName() {
        return Fragment_ReservationOrder.class.getName();
    }
}
