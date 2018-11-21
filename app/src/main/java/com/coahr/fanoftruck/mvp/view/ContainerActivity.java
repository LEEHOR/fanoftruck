package com.coahr.fanoftruck.mvp.view;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseSupportActivity;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_Store;
import com.coahr.fanoftruck.mvp.view.Services.Fragment_store_detail;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 16:55
 */
public class ContainerActivity extends BaseSupportActivity {

    @Override
    public int binLayout() {
        return R.layout.activity_container;
    }

    @Override
    public void initView() {
        switch (getIntent().getIntExtra("tofragment", 0)) {
            //搜索页面
            case Constants.Fragment_store:
                loadRootFragment(R.id.con_fragment, Fragment_Store.newInstance(getIntent().getIntExtra("type",0)));
                break;
                //门店详情
            case Constants.Fragment_Store_Detail:
            loadRootFragment(R.id.con_fragment,Fragment_store_detail.newInstance(getIntent().getStringExtra("s_id")));
                break;
        }
    }

    @Override
    public void initData() {

    }
}
