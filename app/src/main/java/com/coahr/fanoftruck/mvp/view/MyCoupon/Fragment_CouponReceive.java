package com.coahr.fanoftruck.mvp.view.MyCoupon;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_CouponReceive_C;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponDown;
import com.coahr.fanoftruck.mvp.model.Bean.GetCouponList;
import com.coahr.fanoftruck.mvp.presenter.Fragment_CouponReceive_P;
import com.coahr.fanoftruck.mvp.view.MyCoupon.adapter.MyCouponRecevieAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/13
 * on 9:11
 */
public class Fragment_CouponReceive extends BaseFragment<Fragment_CouponReceive_C.Presenter> implements Fragment_CouponReceive_C.View {
    @Inject
    Fragment_CouponReceive_P p;
    @BindView(R.id.coupon_Receive_title)
    MyTittleBar coupon_Receive_title;
    @BindView(R.id.coupon_swipe)
    SwipeRefreshLayout coupon_swipe;
    @BindView(R.id.coupon_Receive_recycler)
    RecyclerView coupon_Receive_recycler;
    @BindView(R.id.go_receive_coupon)
    Button go_receive_coupon;
    private boolean isLoading;
    private MyCouponRecevieAdapter adapter;
    private LinearLayoutManager linearLayoutManager;

    public static  Fragment_CouponReceive newInstance() {
        return new Fragment_CouponReceive();
    }

    @Override
    public Fragment_CouponReceive_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon_receive;
    }

    @Override
    public void initView() {
        coupon_Receive_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        coupon_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                } else {
                    coupon_swipe.setRefreshing(false);
                }
            }
        });
        go_receive_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        adapter = new MyCouponRecevieAdapter();
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        coupon_Receive_recycler.setLayoutManager(linearLayoutManager);
        coupon_Receive_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 1), getResources().getColor(R.color.material_grey_300)));
        coupon_Receive_recycler.setAdapter(adapter);
        for (int i = 0; i < coupon_Receive_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                coupon_Receive_recycler.removeItemDecorationAt(i);
            }
        }
        adapter.setReceiverListener(new MyCouponRecevieAdapter.ReceiverListener() {
            @Override
            public void pull_down(GetCouponList.JdataBean.CouponBean item) {
                coupon_receive(item.getId());
            }
        });
    }

    @Override
    public void initData() {
        getCouponList();
    }

    @Override
    public void getCouponListSuccess(GetCouponList getCoupon) {
        adapter.setNewData(getCoupon.getJdata().getCoupon());
        isLoading=false;
        coupon_swipe.setRefreshing(false);
    }

    @Override
    public void getCouponListFailure(String failure) {
        ToastUtils.showLong(failure);
        isLoading=false;
        coupon_swipe.setRefreshing(false);
    }

    @Override
    public void getReceiveCouponSuccess(GetCouponDown getCouponDown) {
        ToastUtils.showLong(getCouponDown.getJdata().getJmsg());
        getCouponList();
        isLoading=true;
    }

    @Override
    public void getReceiveCouponFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    /**
     * 领取优惠券
     *
     * @param coupon_id
     */
    private void coupon_receive(String coupon_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("coupon_id", coupon_id);
        p.getReceiveCoupon(map);
    }

    /**
     * 领取优惠券
     */
    private void getCouponList() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        p.getCouponList(map);
    }
}
