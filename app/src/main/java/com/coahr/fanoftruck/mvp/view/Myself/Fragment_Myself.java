package com.coahr.fanoftruck.mvp.view.Myself;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myself_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;

import javax.inject.Inject;

import androidx.cardview.widget.CardView;
import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:12
 */
public class Fragment_Myself extends BaseFragment<Fragment_myself_C.Presenter> implements Fragment_myself_C.View, View.OnClickListener {
    @Inject
    Fragment_myself_P p;
    @BindView(R.id.my_userInfo)
    RelativeLayout my_userInfo;
    @BindView(R.id.mycar)
    ImageView mycar;
    @BindView(R.id.iv_about_us)
    ImageView iv_about_us;
    @BindView(R.id.rl_by_order)
    RelativeLayout rl_by_order;
    @BindView(R.id.tv_wxyy_order)
    ImageView tv_wxyy_order;
    @BindView(R.id.rel_shoppingCart)
    RelativeLayout rel_shoppingCart;
    @BindView(R.id.cardview_help_center)
    CardView cardviewHelpCenter;

    public static Fragment_Myself newInstance() {
        return new Fragment_Myself();
    }

    @Override
    public Fragment_myself_C.Presenter getPresenter() {
        return p;
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_myself;
    }

    @Override
    public void initView() {
     /*   tv_my_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(_mActivity,ContainerActivity.class);
                intent.putExtra("tofragment",Constants.Fragment_login);
                startActivity(intent);
            }
        });*/
        my_userInfo.setOnClickListener(this);
        mycar.setOnClickListener(this);
        iv_about_us.setOnClickListener(this);
        rl_by_order.setOnClickListener(this);
        tv_wxyy_order.setOnClickListener(this);
        rel_shoppingCart.setOnClickListener(this);
        cardviewHelpCenter.setOnClickListener(this);
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

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.my_userInfo:  //个人中心
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_userInfo);
                break;
            case R.id.mycar: //我的爱车
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_mycar);
                break;
            case R.id.iv_about_us: //关于我们
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_about_us);
                break;
            case R.id.rl_by_order:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MaintenanceOder);
                break;
            case R.id.tv_wxyy_order:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_ReservationOrder);
                break;
            case R.id.rel_shoppingCart: //购物车
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_ShoppingCart);
                break;
            case R.id.cardview_help_center: //帮助中心
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.FRAGMENT_HELP_CENTER);
                break;
        }

        startActivity(intent);
    }
}
