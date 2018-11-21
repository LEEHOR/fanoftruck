package com.coahr.fanoftruck.mvp.view.Services;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_services_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_services_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:12
 */
public class Fragment_Services extends BaseFragment<Fragment_services_C.Presenter> implements Fragment_services_C.View {
    @Inject
    Fragment_services_P p;
    @BindView(R.id.brn)
    Button brn;

    public static Fragment_Services newInstance() {
        return new Fragment_Services();
    }

    @Override
    public Fragment_services_C.Presenter getPresenter() {
        return p;
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_service;
    }

    @Override
    public void initView() {
        brn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_mActivity, ContainerActivity.class);
                intent.putExtra("type", 1);
                intent.putExtra("tofragment", Constants.Fragment_store);
                startActivity(intent);
            }
        });
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
