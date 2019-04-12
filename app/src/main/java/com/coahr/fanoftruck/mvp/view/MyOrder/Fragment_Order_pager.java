package com.coahr.fanoftruck.mvp.view.MyOrder;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseLazyFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_MyOrder_Pager_C;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_OrderPager_P;
import com.coahr.fanoftruck.mvp.view.MyOrder.adapter.OrderPagerAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.AltDialog.PayTypeSelectDialogFragment;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import butterknife.BindView;

/**
 * @author Leehor
 * 版本：
 * 创建日期：2019/4/3
 * 描述：
 */
public class Fragment_Order_pager extends BaseLazyFragment<Fragment_MyOrder_Pager_C.Presenter> implements Fragment_MyOrder_Pager_C.View {
    protected final String TAG = this.getClass().getSimpleName();

    @Inject
    Fragment_OrderPager_P p;
    @BindView(R.id.myOrderPage_recycler)
    RecyclerView myOrderPageRecycler;
    @BindView(R.id.myOrderPage_swipe)
    SwipeRefreshLayout myOrderPageSwipe;

    private LocalBroadcastManager mLocalBroadcastManager;
    public static final String RECEIVER_ACTION ="order_page_refresh";

    private BroadcastReceiver mReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (RECEIVER_ACTION.equals(RECEIVER_ACTION)){
                KLog.e("lizhiguo", "刷新页面了。。。");
                current_page = 0;
                getOrderList(current_page);
            }
        }
    };

    private String mPayType;
    private String order_id;
    //订单类型
    private static final String ORDER_TYPE = "commodity";

    private List<CommodityOrderBean.JdataEntity.OrderListEntity> orderListEntities = new ArrayList<>();
    private int current_page = 0;
    private int length = 10;
    private int lastVisibleItemPosition;//最近展示的itemposition
    private boolean isLoading = false;
    private String pagerCount;
    private LinearLayoutManager linearLayoutManager;
    private OrderPagerAdapter adapter;
    private PayTypeSelectDialogFragment payTypeSelectDialogFragment;

    public static Fragment_Order_pager newInstance(String pagerCount) {
        Fragment_Order_pager order_pager = new Fragment_Order_pager();
        Bundle bundle = new Bundle();
        bundle.putString("pagerCount", pagerCount);
        order_pager.setArguments(bundle);
        return order_pager;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_myorder_pager;
    }

    @Override
    public void initView() {
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(_mActivity);
        IntentFilter intentFilter = new IntentFilter(RECEIVER_ACTION);
        mLocalBroadcastManager.registerReceiver(mReceiver, intentFilter);

        if (getArguments() != null) {
            pagerCount = getArguments().getString("pagerCount");
        }
        myOrderPageSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    isLoading = true;
                    current_page=0;
                    getOrderList(current_page);
                }
            }
        });
        myOrderPageRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItemPosition + 1 == adapter.getItemCount() && orderListEntities.size() >= 10) {
                    if (!isLoading) {
                        myOrderPageRecycler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getOrderMoreList(current_page++);
                            }
                        },500);
                    }

                }
                }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    public void initData() {
        current_page=0;
        getOrderList(current_page);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        adapter = new OrderPagerAdapter(_mActivity, orderListEntities);
        myOrderPageRecycler.setLayoutManager(linearLayoutManager);
        myOrderPageRecycler.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 5),getResources().getColor(R.color.material_grey_200)));
        myOrderPageRecycler.setAdapter(adapter);
        adapter.setOnItemClickListener(new OrderPagerAdapter.onItemClickListener() {
            @Override
            public void onItemCLick(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }
        });

        adapter.setOnCommodityOrderHandleListener(new OrderPagerAdapter.onCommodityOrderHandleListener() {
            @Override
            public void payImmediately(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {
                payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSelect(String payType) {
                        order_id = orderListEntity.getOrder_id();
                        mPayType = payType;
                        saveConfirmO5rder(payType);
                    }
                });
                payTypeSelectDialogFragment.show(getFragmentManager(), TAG);
            }

            @Override
            public void urgeMyOrder(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }

            @Override
            public void seeLogistics(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }

            @Override
            public void ensureToRecieve(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }

            @Override
            public void evaluateImmediately(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }

            @Override
            public void restoreToBuy(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }

            @Override
            public void haveEvaluate(CommodityOrderBean.JdataEntity.OrderListEntity orderListEntity) {

            }
        });
    }

    /**
     * 发送订单到后台
     * @param payType
     */
    private void saveConfirmO5rder(String payType) {
        Map<String, String> map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_id == null? "": order_id);
        map.put("order_type", ORDER_TYPE);
        map.put("payment", payType);
        p.payImmediately(map);
    }

    @Override
    public void onGetCommodityOrderListSuccess(CommodityOrderBean bean) {
        KLog.e("lizhiguo", "bean == " + bean.toString());
        current_page=0;
        isLoading = false;
        myOrderPageSwipe.setRefreshing(false);
        orderListEntities.clear();
        orderListEntities.addAll(bean.getJdata().getOrder_list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onGetCommodityOrderListFailure(String failure) {
        isLoading = false;
        myOrderPageSwipe.setRefreshing(false);
        current_page=0;
    }

    @Override
    public void loadMoreSuccess(CommodityOrderBean bean) {
        KLog.e("lizhiguo", "bean == " + bean.toString());
        isLoading = false;
        orderListEntities.addAll(bean.getJdata().getOrder_list());
        adapter.notifyDataSetChanged();
    }

    @Override
    public void loadMoreFailure(String failure) {
        isLoading = false;
        current_page--;
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_LONG).show();
    }

    @Override
    public void payImmediatelySuccess(ConfirmOrderBean bean) {
        payTypeSelectDialogFragment.dismiss();
        if (bean != null && bean.getJdata() != null) {
            switch (mPayType) {
                case "ali":
                    KLog.e("lizhiguo", "选择了支付宝");
                    toAliPay(bean.getJdata().getOrder_string());
                    break;
                case "wx":
                    KLog.e("lizhiguo", "选择了微信");
                    toWXPay(bean.getJdata().getOrder_json());
                    break;
            }
        }
    }

    @Override
    public void payImmediatelyFailure(String failure) {
        payTypeSelectDialogFragment.dismiss();
        ToastUtils.showShort(_mActivity, failure);
    }

    /**
     * 获取订单列表
     * @param pager
     */
    private void getOrderList(int pager) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_status", pagerCount);
        map.put("page", pager);
        map.put("length", String.valueOf(length));
        p.getCommodityOrderList(map);
    }

    /**
     * 获取订单列表
     *
     * @param pager
     */
    private void getOrderMoreList(int pager) {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_status", pagerCount);
        map.put("page", pager);
        map.put("length", String.valueOf(length));
        p.loadMore(map);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mLocalBroadcastManager.unregisterReceiver(mReceiver);
        mLocalBroadcastManager = null;
    }
}
