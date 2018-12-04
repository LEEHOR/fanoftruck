package com.coahr.fanoftruck.mvp.view.Home;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_home_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_home_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;

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

    @BindView(R.id.lin_1)
    LinearLayout lin_1;
    @BindView(R.id.lin_2)
    LinearLayout lin_2;
    @BindView(R.id.lin_3)
    LinearLayout lin_3;
    @BindView(R.id.lin_4)
    LinearLayout lin_4;
    @BindView(R.id.lin_5)
    LinearLayout lin_5;
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

        lin_1.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getActivity(),ContainerActivity.class);
            intent.putExtra("tofragment",Constants.Fragment_maintenance);
            startActivity(intent);
        }
    });
        lin_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_help);
                startActivity(intent);
            }
        });
        lin_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });
        lin_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });
        lin_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_maintenance);
                startActivity(intent);
            }
        });

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
