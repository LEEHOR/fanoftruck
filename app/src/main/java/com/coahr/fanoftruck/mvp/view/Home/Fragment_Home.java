package com.coahr.fanoftruck.mvp.view.Home;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.view.View;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_home_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.TribuneActivity;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * 首页主页
 * Created by Leehor
 * on 2018/11/20
 * on 11:06
 */
public class Fragment_Home extends BaseFragment<Fragment_home_C.Presenter> implements Fragment_home_C.View {
    @Inject
    Fragment_home_P p;

    @BindView(R.id.cardview_home)
    CardView cardview_home;
    public static Fragment_Home newInstance() {
        return new Fragment_Home();
    }

    @Override
    public Fragment_home_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home;
    }

    @Override
    public void initView() {
    cardview_home.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getActivity(),ContainerActivity.class);
            intent.putExtra("tofragment",Constants.Fragment_maintenance);
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
