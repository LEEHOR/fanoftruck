package com.coahr.fanoftruck.mvp.Base;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.alipay.sdk.app.PayTask;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.MD5;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.model.Bean.AliPayResult;
import com.coahr.fanoftruck.mvp.model.Bean.WxPayJsonEntity;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;


import java.util.LinkedHashMap;
import java.util.Map;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import dagger.android.support.AndroidSupportInjection;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.PtrUIHandler;
import in.srain.cube.views.ptr.indicator.PtrIndicator;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 16:53
 */
public abstract class BaseFragment<P extends BaseContract.Presenter> extends SupportFragment  implements BaseContract.View {
    /**
     * 日志输出标志
     **/
    protected final String TAG = this.getClass().getSimpleName();
    Unbinder unbinder;
    private Dialog dialog;
    private Dialog dialog_wait;
    private View wait_dialog_view;
    private TextView tv_type;
    private ProgressBar zip_progress;

    public abstract P getPresenter();

    public abstract int bindLayout();

    public abstract void initView();

    public abstract void initData();

    public View addFooterView;
    private static final int SDK_PAY_FLAG = 1;//支付宝支付
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this); //一处声明，处处依赖注入

        super.onAttach(context);
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        View tittleView = ((ViewGroup) view.getRootView()).getChildAt(0);
        if (tittleView != null) {
            tittleView.setPadding(tittleView.getPaddingLeft(), ScreenUtils.getStatusBarHeight(BaseApplication.mContext), tittleView.getPaddingRight(), tittleView.getPaddingBottom());
        }
        UpdateUI(view.getRootView());
        addFooterView = LayoutInflater.from(BaseApplication.mContext).inflate(R.layout.recyclerview_item_foot, null);
        initView();
        initData();
        return view;
    }

    @Override
    public void showLoading() {
        if (dialog == null) {
            dialog=new Dialog(_mActivity, R.style.dialog_loading);
            dialog.setContentView(R.layout.dialog_loading_layout);
        }
        // 设置它的ContentView
        if (!dialog.isShowing()) {
            dialog.show();//显示dialog
        }

    }
    @Override
    public void dismissLoading() {
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }


    public void showError(@Nullable Throwable e) {

    }

    public void recieveData(Bundle bundle) {

    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if (getPresenter() != null) {
            getPresenter().detachView();
        }

    }

    public void UpdateUI(View view) {//解决所有页面   touch所有edittext以外view，自动隐藏键盘  通过decorview可以获取所有子view，循环判断设置touch事件
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View arg0, MotionEvent arg1) {
                    // TODO Auto-generated method stub
                    KeyBoardUtils.hideKeybord(arg0, getActivity());
                    return false;
                }
            });
        }
        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                UpdateUI(innerView);
            }
        }
    }
    //有刷新ptr功能的fragment页面调用此方法
    public void initPtrFrameLayout(final PtrFrameLayout mPtrFrameLayout) {
        if (null == mPtrFrameLayout) return;
        final ImageView view = (ImageView) _mActivity.getLayoutInflater().inflate(R.layout.recyclerview_item_head, mPtrFrameLayout, false);
        Imageloader.loadGif(R.mipmap.center, view);
        mPtrFrameLayout.addPtrUIHandler(new PtrUIHandler() {
            private int mLoadTime = 0;

            @Override
            public void onUIReset(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshPrepare(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshBegin(PtrFrameLayout frame) {

            }

            @Override
            public void onUIRefreshComplete(PtrFrameLayout frame) {

            }

            @Override
            public void onUIPositionChange(PtrFrameLayout frame, boolean isUnderTouch, byte status, PtrIndicator ptrIndicator) {

            }
        });
        mPtrFrameLayout.setHeaderView(view);
        mPtrFrameLayout.setOffsetToKeepHeaderWhileLoading(DensityUtils.dp2px(_mActivity, 100));
        mPtrFrameLayout.setOffsetToRefresh(DensityUtils.dp2px(_mActivity, 100));
        mPtrFrameLayout.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrameLayout.autoRefresh(true);
            }
        }, 100);
        mPtrFrameLayout.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return isCanDoRefresh();
            }

            @Override
            public void onRefreshBegin(final PtrFrameLayout frame) {
                RefreshBegin();
                frame.refreshComplete();
            }
        });
    }
    //开始刷新的时候做什么操作，有刷新需求的fragment复写
    public void RefreshBegin() {

    }

    //判断是否能开始刷新
    public boolean isCanDoRefresh() {
        return true;
    }

    //打电话
    public void call(String tel) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tel));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        _mActivity.startActivity(intent);
    }

    public void WaitingDialog() {
        wait_dialog_view = LayoutInflater.from(_mActivity).inflate(R.layout.layout_waiting_dialog, null, false);
        tv_type = wait_dialog_view.findViewById(R.id.tv_type);
        zip_progress = wait_dialog_view.findViewById(R.id.zip_progress);
        zip_progress.setMax(100);
        tv_type.setText("正在压缩视频...");
        dialog_wait = new Dialog(_mActivity, R.style.dialog_loading_zip);
        dialog_wait.setCanceledOnTouchOutside(false);
        dialog_wait.setCancelable(false);
        dialog_wait.setContentView(wait_dialog_view);
        if (!dialog_wait.isShowing()) {
            dialog_wait.show();
        }
    }

    public void setWait_dialog_text(String s){
        if (dialog_wait!=null && tv_type !=null){
            tv_type.setText(s);
        }
    }

    public void dismissWaitDialog() {
        if (dialog_wait != null && dialog_wait.isShowing()) {
            dialog_wait.dismiss();
        }
    }

    //判断是否登录
    public boolean hasLogin() {
        if (PreferenceUtils.contains(BaseApplication.mContext, "token")) {
            if (Constants.token.equals("")) {
                Constants.token = PreferenceUtils.getPrefString(_mActivity, "token", "");
                Constants.uid = PreferenceUtils.getPrefString(_mActivity, "uid", "");
            }
            return true;
        }
        return false;
    }


    //==========================================阿里支付============================================================//

    /**
     * 阿里支付
     * @param orderString
     *  后台传来的订单
     */
    public void toAliPay(final String orderString) {
        //拿到订单编号，开始支付
        Runnable authRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(_mActivity);
                Map<String, String> result = alipay.payV2(orderString, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;

                mAlyHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }


    /**
     * 支付宝
     */
    @SuppressLint("HandlerLeak")
    private Handler mAlyHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    AliPayResult aliPayResult = new AliPayResult((Map<String, String>) msg.obj);
                    /**
                     * 对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = aliPayResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = aliPayResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        new MaterialDialog.Builder(getActivity())
                                .title("支付宝")
                                .content(resultInfo)
                                .canceledOnTouchOutside(true)
                                .build().show();

                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        new MaterialDialog.Builder(getActivity())
                                .title("支付宝")
                                .content(resultInfo)
                                .canceledOnTouchOutside(true)
                                .build().show();
                    }
                    break;
                }
                default:
                    break;
            }
        }
    };

    //==========================================微信支付============================================================//
    /**
     * 调起微信支付的方法
     **/
    public void toWXPay(final WxPayJsonEntity entity) {
        final IWXAPI iwxapi = WXAPIFactory.createWXAPI(_mActivity, null); //初始化微信api
        Runnable payRunnable = new Runnable() {  //这里注意要放在子线程
            @Override
            public void run() {
                PayReq request = new PayReq(); //调起微信APP的对象
                //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                request.appId = entity.getAppid();
                request.partnerId = entity.getMch_id();
                request.prepayId = entity.getPrepay_id();
                request.packageValue = "Sign=WXPay";
                request.nonceStr = entity.getNonce_str();
                request.timeStamp = String.valueOf(System.currentTimeMillis());
                request.sign = entity.getSign();

                //签名
                LinkedHashMap<String, String> signParams = new LinkedHashMap<>();
                signParams.put("appid", request.appId);
                signParams.put("noncestr", request.nonceStr);
                signParams.put("package", request.packageValue);
                signParams.put("partnerid", request.partnerId);
                signParams.put("prepayid", request.prepayId);
                signParams.put("timestamp", request.timeStamp);
                request.sign = genPackageSign(signParams);
                iwxapi.sendReq(request);//发送调起微信的请求
            }
        };
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }


    /**
     * 将微信支付签名加密
     * @param params
     * @return
     */
    private String genPackageSign(LinkedHashMap<String, String> params) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> entry : params.entrySet()) {
            sb.append(entry.getKey());
            sb.append('=');
            sb.append(entry.getValue());
            sb.append('&');
        }
        sb.append("key=");
        sb.append(Constants.API_KEY);


        String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
        return packageSign;
    }

}
