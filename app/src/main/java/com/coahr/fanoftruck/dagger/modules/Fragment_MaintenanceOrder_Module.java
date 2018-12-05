package com.coahr.fanoftruck.dagger.modules;

import com.coahr.fanoftruck.mvp.view.MaintenanceOrder.Fragment_MaintenanceOrder;
import com.coahr.fanoftruck.mvp.view.VideoPlay.Fragment_MaintenanceVideo_viewPage;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leehor
 * on 2018/12/5
 * on 17:52
 */
@Module
public class Fragment_MaintenanceOrder_Module {
    @Provides
    public String provideName() {
        return Fragment_MaintenanceOrder.class.getName();
    }
}
