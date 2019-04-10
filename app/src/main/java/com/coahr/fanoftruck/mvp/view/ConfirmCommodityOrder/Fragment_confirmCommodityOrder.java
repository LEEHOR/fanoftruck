package com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_confirmCommodityOrder_C;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.Confirm_order;
import com.coahr.fanoftruck.mvp.model.Bean.MessageEvent_confirmCommodityOrder;
import com.coahr.fanoftruck.mvp.presenter.Fragment_confirmCommodityOrder_P;
import com.coahr.fanoftruck.mvp.view.ConfirmCommodityOrder.adapter.ConfirmCommodityOrderAdapter;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_address_list;
import com.coahr.fanoftruck.mvp.view.MyCoupon.Fragment_CouponViewPager;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.AltDialog.PayTypeSelectDialogFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/12
 * on 17:13
 * 商品订单确认页
 */
public class Fragment_confirmCommodityOrder extends BaseFragment<Fragment_confirmCommodityOrder_C.Presenter> implements Fragment_confirmCommodityOrder_C.View {
    @Inject
    Fragment_confirmCommodityOrder_P p;
    @BindView(R.id.mytittle)
    MyTittleBar mytittle;
    @BindView(R.id.rl_reciever_address)
    RelativeLayout rl_reciever_address; //地址
    @BindView(R.id.tv_receiver_name)
    TextView tv_receiver_name;  //收货人
    @BindView(R.id.tv_receiver_phone)
    TextView tv_receiver_phone; //收货人号码
    @BindView(R.id.tv_receiver_address)
    TextView tv_receiver_address; //收货人地址
    @BindView(R.id.rv_order_list)
    RecyclerView rv_order_list;  //列表
    @BindView(R.id.rl_coupon)
    RelativeLayout rl_coupon; //优惠券
    @BindView(R.id.tv_coupon_money)
    TextView tv_coupon_money;  //优惠金额
    @BindView(R.id.tv_totalprice)
    TextView tv_totalprice;  //底部合计金额
    @BindView(R.id.tv_submit_order)
    TextView tv_submit_order;  //提交订单
    private LinearLayoutManager linearLayoutManager;
    private ConfirmCommodityOrderAdapter commodityOrderAdapter;
    private String commodity;
    private String ua_id;
    private float parseFloat;
    private String total_price;  //保留两位小数的总价
    //    private String address_id;
    private String coupon_id;
    private float discount_price = 0;

    private String mPayType;

    public static Fragment_confirmCommodityOrder newInstance(String commodity, String ua_id) {
        Fragment_confirmCommodityOrder confirmCommodityOrder = new Fragment_confirmCommodityOrder();
        Bundle bundle = new Bundle();
        bundle.putString("commodity", commodity);
        bundle.putString("ua_id", ua_id);
        confirmCommodityOrder.setArguments(bundle);
        return confirmCommodityOrder;
    }

    @Override
    public Fragment_confirmCommodityOrder_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_confirm_commodity_order;
    }

    @Override
    public void initView() {
        rl_reciever_address.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startForResult(Fragment_address_list.newInstance(Constants.Fragment_confirmCommodityOrder), 11);
            }
        });
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        commodityOrderAdapter = new ConfirmCommodityOrderAdapter();
        rv_order_list.setLayoutManager(linearLayoutManager);
        rv_order_list.setAdapter(commodityOrderAdapter);
        rv_order_list.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext, 4), getResources().getColor(R.color.material_grey_250)));
        for (int i = 0; i < rv_order_list.getItemDecorationCount(); i++) {
            if (i != 0) {
                rv_order_list.removeItemDecorationAt(i);
            }
        }
        rl_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_CouponViewPager.newInstance(Constants.Fragment_confirmCommodityOrder, Float.parseFloat(total_price)));
            }
        });



        /**
         * 提交订单
         */
        tv_submit_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSelect(String payType) {
                        KLog.e("lizhiguo", "payType == " + payType +"支付方式回来了");
                        mPayType = payType;
                        saveConfirmO5rder(payType);
                    }
                });
                payTypeSelectDialogFragment.show(getChildFragmentManager(), TAG);
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            commodity = getArguments().getString("commodity");
            ua_id = getArguments().getString("ua_id");
        }
        getOrderList();
    }

    @Override
    public void getCommodityOrderSuccess(Confirm_order confirmOrder) {
        KLog.e("lizhiguo", "confirmOrder == " + confirmOrder.toString());
        Confirm_order.JdataBean jdata = confirmOrder.getJdata();
        List<Confirm_order.JdataBean.CommodityBean> commodity = jdata.getCommodity();
        if (commodity != null && commodity.size() > 0) {
            commodityOrderAdapter.setNewData(commodity);

//            for (int i = 0; i < commodity.size(); i++) {
//                String c_price = commodity.get(i).getC_price();
//                parseFloat = +Float.parseFloat(c_price);
//            }
//            total_price = ValidateUtils.getDouble(parseFloat);
            total_price = String.valueOf(jdata.getTotal());

            tv_totalprice.setText(total_price);
        }

        Confirm_order.JdataBean.UserinfoBean userinfo = confirmOrder.getJdata().getUserinfo();
        if (userinfo != null) {
            tv_receiver_address.setText(userinfo.getAddress());
            ua_id = userinfo.getUa_id();
            tv_receiver_name.setText(userinfo.getUsername());
            tv_receiver_phone.setText(userinfo.getTelephone());
        }


    }

    @Override
    public void getCommodityOrderFailure(String failure) {

    }

    @Override
    public void onSaveCommodityOrderSuccess(ConfirmOrderBean bean) {
        KLog.e("lizhiguo", "bean == " + bean.toString());
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
    public void onSaveCommodityOrderFailure(String failure) {

    }

    /**
     * 获取列表
     */
    private void getOrderList() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("commodity", commodity);
        map.put("ua_id", ua_id);
        p.getCommodityOrderList(map);
    }

    /**
     * 发送订单到后台
     *
     * @param payType
     */
    private void saveConfirmO5rder(String payType) {
        //计算打折后的价格
        String last_price = ValidateUtils.getDouble((Float.parseFloat(total_price) - discount_price));
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("commodity", commodity);
        map.put("ua_id", ua_id);
        map.put("fee", "");
        map.put("total", last_price);
        map.put("payment", payType);
        map.put("coupon_id", coupon_id == null?"": coupon_id);

        KLog.e("lizhiguo",
                "\ntoken == " + Constants.token
                + "\ncommodity == " + commodity
                + "\nua_id == " + ua_id
                + "\nfee == " + ""
                + "\ntotal == " + last_price
                + "\npayment == " + payType
                + "\ncoupon_id == " + coupon_id
        );
        p.saveCommodityOrder(map);
    }

    @Override
    public void onFragmentResult(int requestCode, int resultCode, Bundle data) {
        super.onFragmentResult(requestCode, resultCode, data);
        if (requestCode == 11 && resultCode == 11) {
            if (data != null) {
                tv_receiver_address.setText((String) data.get("address"));
                ua_id = (String) data.get("address_id");
                tv_receiver_name.setText((String) data.get("address_user"));
                tv_receiver_phone.setText((String) data.get("address_phone"));
            }
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent_confirmCommodityOrder messageEvent) {
        coupon_id = messageEvent.getCoupon_id();
        String discount = messageEvent.getDiscount();
        tv_coupon_money.setText(discount);
        discount_price = Float.parseFloat(discount);
        tv_totalprice.setText(ValidateUtils.getDouble((Float.parseFloat(total_price) - Float.parseFloat(discount))));
    }
}
