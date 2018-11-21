package com.coahr.fanoftruck.mvp.view;

import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ActivityManagerUtils;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseActivity;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.mob.bbssdk.gui.views.MainViewInterface;
import com.mob.tools.utils.ResHelper;

import butterknife.BindView;

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
