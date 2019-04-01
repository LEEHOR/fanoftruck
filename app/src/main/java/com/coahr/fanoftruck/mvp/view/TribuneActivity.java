package com.coahr.fanoftruck.mvp.view;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;



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


    }

    @Override
    public void initData() {

    }
        @Override
        public void onBackPressedSupport() {
            if ((System.currentTimeMillis() - exitTime) > INTERVAL_TIME) {
                ToastUtils.showLong(getResources().getString(R.string.tribuneActivity_exit));
                exitTime = System.currentTimeMillis();
            } else {
                finish();
            }
        }
}
