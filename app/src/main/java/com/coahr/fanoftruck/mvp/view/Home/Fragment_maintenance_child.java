package com.coahr.fanoftruck.mvp.view.Home;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseChildFragment;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_child_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_child_P;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 15:21
 * 维修视频列表
 */
public class Fragment_maintenance_child extends BaseChildFragment<Fragment_maintenance_child_C.Presenter> implements Fragment_maintenance_child_C.View{
    @Inject
    Fragment_maintenance_child_P p;
    @BindView(R.id.recycler)
    RecyclerView recyclerView;
    @BindView(R.id.swipe)
    SwipeRefreshLayout swipeRefreshLayout;
    public static Fragment_maintenance_child newInstance(int status){
        Fragment_maintenance_child child=new Fragment_maintenance_child();
        Bundle bundle=new Bundle();
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
        return R.layout.fragment_maintance_child;
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
