package com.coahr.fanoftruck.mvp.view.Myself;

import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ThirdLoginUtil;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.model.ApiContact;
import com.coahr.fanoftruck.mvp.model.Bean.BindWXData;
import com.coahr.fanoftruck.mvp.model.Bean.MyselfData;
import com.coahr.fanoftruck.mvp.model.Bean.UnsetWXData;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myself_P;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.MainActivity;
import com.coahr.fanoftruck.widgets.CircleImageView;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.HashMap;
import java.util.Map;

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
    CircleImageView userHeadImg;
    @BindView(R.id.userName)
    TextView userName;
    @BindView(R.id.userPhone)
    TextView userPhone;
    @BindView(R.id.my_wx_bind)
    TextView myWxBind;

    private String openid;
    private String wxid;

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
        /*tv_my_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(),ContainerActivity.class);
                intent.putExtra("tofragment",Constants.Fragment_login);
                startActivity(intent);
            }
        });*/
        EventBus.getDefault().register(this);

        myWxBind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                KLog.e("lizhiguo", "wxid == " + wxid + "－－－" + "openid == " + openid);
                ThirdLoginUtil.bindWx(wxid, openid, _mActivity, mBaseUMAuthListener);
            }
        });
    }

    @Subscribe
    public void onEventMainThread(String unsetWx) {
        HashMap map = new HashMap();
        map.put("token", Constants.token);
        getPresenter().unsetWX(map);
    }

    @Override
    public void initData() {
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        getMySelfData();
    }

    private void getMySelfData() {
        HashMap map = new HashMap<String, String>();
        map.put("token", Constants.token);
        p.getMySelfData(map);
    }

    @Override
    public void bindWXSuccess(BindWXData bindWXData) {
        if (bindWXData != null) {
            ToastUtils.showShort(_mActivity, bindWXData.getJdata().getBdwx_msg());
            getMySelfData();
        }
    }

    @Override
    public void bindWXFailure(String failure) {
        ToastUtils.showShort(_mActivity, failure);
    }

    @Override
    public void unsetWXSuccess(UnsetWXData unsetWXData) {
        if (unsetWXData != null) {
            ToastUtils.showShort(_mActivity, unsetWXData.getJdata().getJmsg());
            getMySelfData();
        }
    }

    @Override
    public void unsetWXFailure(String failure) {
        ToastUtils.showShort(_mActivity, failure);
    }

    @Override
    public void onLocationSuccess(AMapLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }

    @Override
    public void getMySelfDataSuccess(MyselfData myselfData) {
        if (myselfData != null && 0 == myselfData.getCode()) {
            MyselfData.JdataBean.UserBean user = myselfData.getJdata().getUser();
            if (user != null) {
                String userheadimg = (String) user.getUserheadimg();
                if (userheadimg != null) {
                    Imageloader.loadCircularImage(userheadimg, userHeadImg);
                }

                String nickname = user.getNickname();
                if (user != null) {
                    userName.setText(nickname);
                }
                String phone = user.getPhone();
                if (phone != null) {
                    userPhone.setText(phone);
                }

                openid = (String) user.getOpenid();
                wxid = (String) user.getWxid();
                KLog.e("lizhiguo", "openid == " + openid + "wxid == " + wxid);
                if (TextUtils.isEmpty(openid) && TextUtils.isEmpty(wxid)) {
                    myWxBind.setText("未绑定");
                } else {
                    myWxBind.setText("已绑定");
                }
            }
        }
    }

    @Override
    public void getMySelfDataFailure(String failure) {
        ToastUtils.showShort(getActivity(), failure);
    }

    @OnClick({R.id.rl_my_userInfo, /*R.id.my_wx_bind,*/ R.id.rl_my_order, R.id.rl_manage_invoices, R.id.rel_shoppingCart, R.id.rl_my_coupon, R.id.rl_my_car, R.id.rl_wxyy_order, R.id.rl_by_order, R.id.rl_business_order, R.id.rl_invite_truck_fans, R.id.rl_service_join, R.id.rl_help_center, R.id.rl_about_us})
    public void onViewClicked(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_my_userInfo:
                //个人中心
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_userInfo);
                break;
            /*case R.id.my_wx_bind:
                ThirdLoginUtil.bindWX(wxid, openid, _mActivity, mBaseUMAuthListener);
                break;*/
            case R.id.rl_my_order:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.FRAGMENT_MY_ORDER);
                intent.putExtra("pagerCount", 0);
                break;
            case R.id.rl_manage_invoices:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MyWebView);
                intent.putExtra("url", ApiContact.SHOW_INVOICE + "?token=" + Constants.token);
                intent.putExtra("title", "添加发票信息");
                intent.putExtra("type", 1);
                break;
            case R.id.rel_shoppingCart:
                //购物车
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_ShoppingCart);
                break;
            case R.id.rl_my_coupon:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("FormFragment", Constants.Fragment_myself);
                intent.putExtra("tofragment", Constants.FRAGMEN_MY_COUPON);
                break;
            case R.id.rl_my_car:
                //我的爱车
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_mycar);
                break;
            case R.id.rl_wxyy_order:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("showPosition", 0);
                intent.putExtra("tofragment", Constants.Fragment_ReservationOrder);
                break;
            case R.id.rl_by_order:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MaintenanceOder);
                break;
            case R.id.rl_business_order:
                intent.setClass(getActivity(), MainActivity.class);
                intent.putExtra("page", 2);
                break;
            case R.id.rl_invite_truck_fans:

                break;
            case R.id.rl_service_join:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_MyWebView);
                intent.putExtra("url", ApiContact.SERVICE_JOIN + "?token=" + Constants.token);
                intent.putExtra("title", "服务商加盟");
                intent.putExtra("type", 0);
                break;
            case R.id.rl_help_center:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.FRAGMENT_HELP_CENTER);
                break;
            case R.id.rl_about_us:
                intent.setClass(getActivity(), ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_about_us);
                break;
        }

        startActivity(intent);
    }

    private ThirdLoginUtil.BaseUMAuthListener mBaseUMAuthListener = new ThirdLoginUtil.BaseUMAuthListener() {
        @Override
        protected void _onComplete(Map<String, String> data) {
            Map map = new HashMap();
            map.put("openid", data.get("openid"));
            map.put("unionid", data.get("unionid"));
            map.put("nickname", data.get("screen_name"));
            map.put("headimgurl", data.get("profile_image_url"));
            map.put("token", Constants.token);
            KLog.e("lizhiguo", map.get("openid"), map.get("uid"), map.get("unionid"), map.get("nickname"), map.get("headimgurl"), map.get("token"));
            getPresenter().bindWX(map);
        }
    };

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }
}