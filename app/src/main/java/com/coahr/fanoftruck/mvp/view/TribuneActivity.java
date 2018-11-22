package com.coahr.fanoftruck.mvp.view;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;
import com.mob.bbssdk.API;
import com.mob.bbssdk.APICallback;
import com.mob.bbssdk.BBSSDK;
import com.mob.bbssdk.api.ForumAPI;
import com.mob.bbssdk.gui.views.MainViewInterface;
import com.mob.bbssdk.model.ForumForum;
import com.mob.tools.utils.ResHelper;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 14:47
 */
public class TribuneActivity extends BaseSupportActivity {

    private long exitTime = 0;
    private static final long INTERVAL_TIME = 2000;
    @Override
    public int binLayout() {
        return R.layout.activity_tribune;
    }

    @Override
    public void initView() {
        MainViewInterface mainView = (MainViewInterface) findViewById(ResHelper.getIdRes(this, "mainView"));
        mainView.loadData();

    }

    @Override
    public void initData() {

    }
        @Override
        public void onBackPressedSupport() {
            if ((System.currentTimeMillis() - exitTime) > INTERVAL_TIME) {
                ToastUtils.showLong(getResources().getString(R.string.carsuper_exit));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
}
