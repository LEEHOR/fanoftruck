package com.coahr.fanoftruck.mvp.view.Mycoupon;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_coupon_main_C;
import com.coahr.fanoftruck.mvp.model.Bean.CouponBean;
import com.coahr.fanoftruck.mvp.model.Bean.Coupon_Used;
import com.coahr.fanoftruck.mvp.model.Bean.MessageEvent_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.model.Bean.MessageEvent_coupon;
import com.coahr.fanoftruck.mvp.presenter.Fragment_coupon_P;
import com.coahr.fanoftruck.mvp.view.Mycoupon.adapter.CouponMainAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PrimitiveIterator;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/12
 * on 19:05
 */
public class Fragment_coupon_main extends BaseFragment<Fragment_coupon_main_C.Presenter> implements Fragment_coupon_main_C.View {
    @Inject
    Fragment_coupon_P p;
    @BindView(R.id.coupon_swipe)
    SwipeRefreshLayout coupon_swipe;
    @BindView(R.id.coupon_recycler)
    RecyclerView coupon_recycler;
    private LinearLayoutManager linearLayoutManager;
    private CouponMainAdapter couponMainAdapter;
    private int fromFragment;
    private double orderPrice;
    private int currentPage = 0;  //加载的页数
    private int length = 10; //每一页数据数目
    private boolean isLoading;
    private int order_status;
    private int left = 0, right = 0;

    public static Fragment_coupon_main newInstance(int order_status, int fromFragment, double orderPrice) {
        Fragment_coupon_main fragment = new Fragment_coupon_main();
        Bundle arg = new Bundle();
        arg.putInt("order_status", order_status);
        arg.putInt("fromFragment", fromFragment);
        arg.putDouble("orderPrice", orderPrice);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public Fragment_coupon_main_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_main_coupon;
    }

    @Override
    public void initView() {
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        couponMainAdapter = new CouponMainAdapter(BaseApplication.mContext);
        coupon_recycler.setLayoutManager(linearLayoutManager);
        coupon_recycler.setAdapter(couponMainAdapter);
        coupon_recycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_250)));
        for (int i = 0; i < coupon_recycler.getItemDecorationCount(); i++) {
            if (i != 0) {
                coupon_recycler.removeItemDecorationAt(i);
            }
        }
        coupon_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    getCouponList();
                } else {
                    coupon_swipe.setRefreshing(false);
                }

            }
        });
        couponMainAdapter.setListener(new CouponMainAdapter.onItemClickListener() {
            @Override
            public void StandbyCouponListener(CouponBean.JdataBean.CouponEnableBean enableBean) {
                if (fromFragment == Constants.Fragment_confirmCommodityOrder) {
                    use_coupon(enableBean.getId());
                } else {
                    Toast.makeText(BaseApplication.mContext, "当前页面无法使用", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void OverdueCouponListener(CouponBean.JdataBean.CouponDisableBean disableBean) {

            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            order_status = getArguments().getInt("order_status");
            fromFragment = getArguments().getInt("fromFragment");
            orderPrice = getArguments().getDouble("orderPrice");
            getCouponList();
        }
    }

    @Override
    public void onGetCouponOrderListSuccess(CouponBean couponBean) {
        couponMainAdapter.setCouponBean(couponBean);

        List<CouponBean.JdataBean.CouponEnableBean> coupon_enable = couponBean.getJdata().getCoupon_enable();
        List<CouponBean.JdataBean.CouponDisableBean> coupon_disable = couponBean.getJdata().getCoupon_disable();
        if (coupon_disable != null && coupon_disable.size() > 0) {
            right = coupon_disable.size();
        } else {
            right = 0;
        }
        if (coupon_enable != null && coupon_enable.size() > 0) {
            left = coupon_enable.size();
        } else {
            left = 0;
        }
        EventBus.getDefault().post(new MessageEvent_coupon(left, right));

        if (order_status == 0) {

            couponMainAdapter.setType(order_status);
        }
        if (order_status == 1) {

            couponMainAdapter.setType(order_status);
        }
        couponMainAdapter.notifyDataSetChanged();
        coupon_swipe.setRefreshing(false);
        isLoading = false;
    }

    @Override
    public void onGetCouponOrderListFailure(String failure) {
        ToastUtils.showLong(failure);
        coupon_swipe.setRefreshing(false);
        isLoading = false;
    }

    @Override
    public void onUsedCouponSuccess(Coupon_Used coupon_used) {
        if (fromFragment==Constants.Fragment_confirmCommodityOrder){      //商品订单确认页面
            if (coupon_used.getJdata().getCoupon().getCoupon_type().equals("1")){
                if (orderPrice>=Double.parseDouble(coupon_used.getJdata().getCoupon().getAmount())){
                    EventBus.getDefault().post(new MessageEvent_confirmCommodityOrder(coupon_used.getJdata().getCoupon().getId(),coupon_used.getJdata().getCoupon().getName()
                            ,coupon_used.getJdata().getCoupon().getDiscount(),coupon_used.getJdata().getCoupon().getAmount()
                            ,coupon_used.getJdata().getCoupon().getType()));
                    _mActivity.onBackPressed();
                } else {
                    Toast.makeText(BaseApplication.mContext, "条件不满足无法使用", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(BaseApplication.mContext, "条件不满足无法使用", Toast.LENGTH_LONG).show();
            }

        }
    }

    @Override
    public void onUsedCouponFailure(String failure) {
        ToastUtils.showLong(failure);
    }

    @Override
    public void LoadMoreSuccess(CouponBean coupon_used) {

    }

    @Override
    public void LoadMoreFailure(String failure) {

    }

    /**
     * 获取列表
     */
    private void getCouponList() {
        Map map = new HashMap();
        map.put("page", String.valueOf(currentPage));
        map.put("length", String.valueOf(length));
        map.put("token", Constants.token);
        p.getCouponOrderList(map);
    }

    /**
     * 使用
     *
     * @param coupon_id
     */
    private void use_coupon(String coupon_id) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("coupon_id", coupon_id);
        p.onUsedCoupon(map);
    }
}
