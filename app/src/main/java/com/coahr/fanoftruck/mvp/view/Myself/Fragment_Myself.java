package com.coahr.fanoftruck.mvp.view.Myself;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.model.ApiContact;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myself_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.MainActivity;
import com.coahr.fanoftruck.widgets.CircleImagewView;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:12
 */
public class Fragment_Myself extends BaseFragment<Fragment_myself_C.Presenter> implements Fragment_myself_C.View {
    @Inject
    Fragment_myself_P p;
    @BindView(R.id.userHeadImg)
    CircleImagewView userHeadImg;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userPhone)
    TextView userPhone;
    @BindView(R.id.my_wx_bind)
    TextView myWxBind;

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

    }

    @Override
    public void initData() {
    }

    @Override
    public void onResume() {
        super.onResume();
        Imageloader.loadCircularImage(Constants.headImg, userHeadImg);
        userName.setText(TextUtils.isEmpty(Constants.nickname) ? Constants.telephone : Constants.nickname);
        userPhone.setText(Constants.telephone);
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @OnClick({R.id.rl_my_userInfo, R.id.my_wx_bind, R.id.rl_my_order, R.id.rl_manage_invoices, R.id.rel_shoppingCart, R.id.rl_my_coupon, R.id.rl_my_car, R.id.rl_wxyy_order, R.id.rl_by_order, R.id.rl_business_order, R.id.rl_invite_truck_fans, R.id.rl_service_join, R.id.rl_help_center, R.id.rl_about_us})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_my_userInfo:
                //个人中心
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_userInfo);
                startActivity(intent);
                break;
            case R.id.my_wx_bind:
                break;
            case R.id.rl_my_order:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.FRAGMENT_MY_ORDER);
                intent.putExtra("pagerCount", 0);
                startActivity(intent);
                break;
            case R.id.rl_manage_invoices:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MyWebView);
                intent.putExtra("url", ApiContact.SHOW_INVOICE + "?token=" + Constants.token);
                intent.putExtra("title", "添加发票信息");
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.rel_shoppingCart:
                //购物车
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_ShoppingCart);
                startActivity(intent);
                break;
            case R.id.rl_my_coupon:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("FormFragment", Constants.Fragment_myself);
                intent.putExtra("tofragment", Constants.FRAGMEN_MY_COUPON);
                startActivity(intent);
                break;
            case R.id.rl_my_car:
                //我的爱车
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_mycar);
                startActivity(intent);
                break;
            case R.id.rl_wxyy_order:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("showPosition", 0);
                intent.putExtra("tofragment", Constants.Fragment_ReservationOrder);
                startActivity(intent);
                break;
            case R.id.rl_by_order:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MaintenanceOder);
                startActivity(intent);
                break;
            case R.id.rl_business_order:
                intent.setClass(_mActivity, MainActivity.class);
                intent.putExtra("page", 2);
                startActivity(intent);
                break;
            case R.id.rl_invite_truck_fans:

                break;
            case R.id.rl_service_join:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MyWebView);
                intent.putExtra("url", ApiContact.SERVICE_JOIN + "?token=" + Constants.token);
                intent.putExtra("title", "服务商加盟");
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.rl_help_center:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.FRAGMENT_HELP_CENTER);
                startActivity(intent);
                break;
            case R.id.rl_about_us:
                intent.setClass(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_about_us);
                startActivity(intent);
                break;
        }


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(_mActivity).release();
    }
}
