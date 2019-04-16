package com.coahr.fanoftruck.mvp.view.MyOrder;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.NeedToPayContract;
import com.coahr.fanoftruck.mvp.model.Bean.CommodityOrderDetailBean;
import com.coahr.fanoftruck.mvp.model.Bean.ConfirmOrderBean;
import com.coahr.fanoftruck.mvp.model.Bean.ResultBean;
import com.coahr.fanoftruck.mvp.presenter.NeedToPayPresenter;
import com.coahr.fanoftruck.mvp.view.MyOrder.adapter.CommodityOrderDetailCommodityAdapter;
import com.coahr.fanoftruck.mvp.view.decoration.SpacesItemDecoration;
import com.coahr.fanoftruck.widgets.AltDialog.PayTypeSelectDialogFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;

/**
 * Author： hengzwd on 2018/8/10.
 * Email：hengzwdhengzwd@qq.com
 * 我的商品订单待支付
 */
public class NeedToPayFragment extends BaseFragment<NeedToPayContract.Presenter> implements NeedToPayContract.View {
    @Inject
    NeedToPayPresenter needToPayPresenter;

    @BindView(R.id.tb_tittle)
    MyTittleBar tbTittle;
    @BindView(R.id.image3)
    ImageView image3;
    @BindView(R.id.tv_receiver)
    TextView tvReceiver;
    @BindView(R.id.tv_phone_number)
    TextView tvPhoneNumber;
    @BindView(R.id.iv_location)
    ImageView ivLocation;
    @BindView(R.id.tv_recieve_address)
    TextView tvRecieveAddress;
    @BindView(R.id.rv_commodity_list)
    RecyclerView rvCommodityList;
    @BindView(R.id.tv_order_number)
    TextView tvOrderNumber;
    @BindView(R.id.tv_copy_order_number)
    TextView tvCopyOrderNumber;
    @BindView(R.id.tv_order_status)
    TextView tvOrderStatus;
    @BindView(R.id.tv_order_time)
    TextView tvOrderTime;
    @BindView(R.id.tv_need_to_pay)
    TextView tvNeedToPay;
    @BindView(R.id.tv_goto_pay)
    TextView tvGotoPay;
    @BindView(R.id.tv_cancel_order)
    TextView tvCancelOrder;


    private LinearLayoutManager linearLayoutManager;
    private CommodityOrderDetailCommodityAdapter commodityOrderDetailCommodityAdapter;
    private String order_Id;

    private static final String ORDER_TYPE = "commodity";
    private String mPayType;
    private String order_status;

    private List<CommodityOrderDetailBean.JdataEntity.CommodityEntity> commodityEntityList = new ArrayList<>();

    public static NeedToPayFragment newInstance(String order_Id) {
        NeedToPayFragment fragment = new NeedToPayFragment();
        Bundle arg = new Bundle();
        arg.putString("order_id", order_Id);
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public NeedToPayContract.Presenter getPresenter() {
        return needToPayPresenter;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_order_need_to_pay;
    }

    @Override
    public void initView() {
        tbTittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _mActivity.onBackPressed();
            }
        });
        tvGotoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //弹出支付方式选择
                PayTypeSelectDialogFragment payTypeSelectDialogFragment = new PayTypeSelectDialogFragment();
                payTypeSelectDialogFragment.setOnpayTypeSelectListener(new PayTypeSelectDialogFragment.OnPayTypeSelectListener() {
                    @Override
                    public void onItemSelect(String payType) {
                        mPayType = payType;
                        Map<String, String> map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("order_id", order_Id == null? "": order_Id);
                        map.put("order_type", ORDER_TYPE);
                        map.put("payment", mPayType);

//                        map.put("token", Constants.token);
//                        map.put("order_id", order_id == null ? "" : order_id);
//                        map.put("order_type", ORDER_TYPE);
//                        map.put("payment", payType);

                        getPresenter().quickPay(map);
                    }
                });
                payTypeSelectDialogFragment.show(getFragmentManager(), TAG);

            }
        });

        tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CancleReasonInputDialogFragment dialogFragment = CancleReasonInputDialogFragment.newInstance(CancleReasonInputDialogFragment.TYPE_COMMODIITY_ORDER);
                dialogFragment.setOnInputCallback(new CancleReasonInputDialogFragment.InputCallback() {
                    @Override
                    public void onInputSend(String input) {
                        Map map = new HashMap();
                        map.put("token", Constants.token);
                        map.put("order_id", order_Id);
                        map.put("reason", input);
                        getPresenter().cancelCommodityOrder(map);
                    }
                });
                dialogFragment.show(_mActivity.getSupportFragmentManager(), TAG);
            }
        });
        tvCopyOrderNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager cm = (ClipboardManager) _mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                cm.setPrimaryClip(ClipData.newPlainText("text", tvOrderNumber.getText()));
                //cm.setText(tvOrderNumber.getText());
                Toast.makeText(BaseApplication.mContext,"已复制到剪切板",Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
        order_Id = getArguments().getString("order_id");
        KLog.e("lizhiguo", "initData == " + order_Id);
        linearLayoutManager = new LinearLayoutManager(BaseApplication.mContext);
        rvCommodityList.setLayoutManager(linearLayoutManager);
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("order_id", order_Id);
        getPresenter().getCommodityOrderDetail(map);

    }


    @Override
    public void onGetCommodityOrderDetialSuccess(CommodityOrderDetailBean bean) {
        order_status= bean.getJdata().getOrder().getO_status();
        commodityEntityList = bean.getJdata().getCommodity();
        commodityOrderDetailCommodityAdapter = new CommodityOrderDetailCommodityAdapter(bean.getJdata().getCommodity());
        rvCommodityList.setAdapter(commodityOrderDetailCommodityAdapter);
        rvCommodityList.addItemDecoration(new SpacesItemDecoration(0, DensityUtils.dp2px(BaseApplication.mContext,1),getResources().getColor(R.color.material_grey_200)));
        tvOrderNumber.setText(bean.getJdata().getOrder().getOrder_id());
        tvOrderStatus.setText("待付款");
        tvOrderTime.setText(bean.getJdata().getOrder().getCreate_time());

        tvReceiver.setText(bean.getJdata().getAddress().getUsername());
        tvPhoneNumber.setText(bean.getJdata().getAddress().getTelephone());
        tvRecieveAddress.setText(bean.getJdata().getAddress().getAddress());
        double total = Double.parseDouble(bean.getJdata().getOrder().getTotal() != null && !bean.getJdata().getOrder().getTotal().equals("") ? bean.getJdata().getOrder().getTotal() : "0");
        int fee=bean.getJdata().getOrder().getFee();
        double fee_d=(double)fee;
        double topay=fee_d+total;
        String twoDecimal = ValidateUtils.getDouble(topay);
        tvNeedToPay.setText("¥" + twoDecimal);

    }

    @Override
    public void onGetCommodityOrderDetailFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCancelOrderSuccess(ResultBean resultBean) {
        if (resultBean.getJdata().getJmsg() != null) {
            Toast.makeText(BaseApplication.mContext, resultBean.getJdata().getJmsg(), Toast.LENGTH_SHORT).show();
            //发送广播
            LocalBroadcastManager.getInstance(_mActivity).sendBroadcast(new Intent(Fragment_Order_pager.RECEIVER_ACTION));
            _mActivity.onBackPressed();
        } else {
            Toast.makeText(BaseApplication.mContext, resultBean.getMsg(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCancelOrderFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onQuickPaySuccess(ConfirmOrderBean bean) {
        if (mPayType.equals("ali")) {
            toAliPay(bean.getJdata().getOrder_string());
        } else if (mPayType.equals("wx")) {
            if (bean.getJdata().getOrder_json().getReturn_code().equals("SUCCESS")) {
                toWXPay(bean.getJdata().getOrder_json());
            }else {
                Toast.makeText(BaseApplication.mContext,bean.getJdata().getOrder_json().getReturn_msg(),Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onQuickPayFailure(String failure) {
        Toast.makeText(BaseApplication.mContext, failure, Toast.LENGTH_SHORT).show();

    }
}
