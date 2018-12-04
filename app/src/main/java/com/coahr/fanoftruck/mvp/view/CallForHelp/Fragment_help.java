package com.coahr.fanoftruck.mvp.view.CallForHelp;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_help_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_help_P;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/26
 * on 17:05
 */
public class Fragment_help extends BaseFragment<Fragment_help_C.Presenter> implements Fragment_help_C.View {

    @Inject
    Fragment_help_P p;
    @BindView(R.id.help_recycler)
    RecyclerView help_recycler;

    public static Fragment_help newInstance(int status) {
        Fragment_help fragment_help = new Fragment_help();
        Bundle bundle = new Bundle();
        bundle.putInt("status", status);
        fragment_help.setArguments(bundle);
        return fragment_help;
    }

    @Override
    public Fragment_help_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_callforhelp;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
