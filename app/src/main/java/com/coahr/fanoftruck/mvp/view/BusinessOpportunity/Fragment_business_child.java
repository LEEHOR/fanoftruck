package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import android.os.Bundle;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.constract.Fragment_business_child_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_business_child_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 15:28
 */
public class Fragment_business_child extends BaseChildFragment<Fragment_business_child_C.Presenter> implements Fragment_business_child_C.View {

    @Inject
    Fragment_business_child_P p;
    public static Fragment_business_child newInstance(int status) {
        Fragment_business_child child=new Fragment_business_child();
        Bundle bundle =new Bundle();
        bundle.putInt("status",status);
        child.setArguments(bundle);
        return child;
    }
    @Override
    public BaseContract.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_business_child;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
