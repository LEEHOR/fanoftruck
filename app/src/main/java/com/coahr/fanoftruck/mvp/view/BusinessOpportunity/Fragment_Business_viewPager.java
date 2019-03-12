package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import androidx.annotation.NonNull;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.mvp.view.Myself.Fragment_login;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;


import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:10
 */
public class Fragment_Business_viewPager extends BaseFragment {

    @BindView(R.id.x5_web)
    X5WebViewByMyShelf webView;
    @BindView(R.id.mytitle_business)
    MyTittleBar myTittleBar;
    @BindView(R.id.mywebview_swipe)
    SwipeRefreshLayout mywebview_swipe;
    private boolean isLoading;
    public static Fragment_Business_viewPager newInstance() {
        return new Fragment_Business_viewPager();
    }


    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_business_opportunity_order;
    }

    @Override
    public void initView() {
        initHardwareAccelerate();
        myTittleBar.getLeftIcon().setVisibility(View.GONE);
        myTittleBar.getRightText().setVisibility(View.VISIBLE);
        myTittleBar.getRightText().setText("推荐购车");
        myTittleBar.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        myTittleBar.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (hasLogin()) {
                    Intent intent = new Intent(_mActivity, ContainerActivity.class);
                    intent.putExtra("tofragment", Constants.Fragment_recommendCar);
                    startActivity(intent);
                } else {
                    new MaterialDialog.Builder(_mActivity)
                            .title("提示")
                            .content("您当前未登录")
                            .negativeText("取消")
                            .positiveText("去登陆")
                            .onNegative(new MaterialDialog.SingleButtonCallback() {
                                @Override
                                public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                    dialog.dismiss();
                                }
                            }).onPositive(new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            dialog.dismiss();
                            start(Fragment_login.newInstance(Constants.Fragment_myUserInfo));
                        }
                    }).build().show();
                }


            }
        });
        initHardwareAccelerate();
        mywebview_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    mywebview_swipe.post(new Runnable() {
                        @Override
                        public void run() {
                            isLoading=true;
                            webView.reload();
                        }
                    });
                } else {
                    ToastUtils.showLong("加载中");
                }
            }
        });


        //webView.setOnClickListener();
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mywebview_swipe.setRefreshing(false);
                ToastUtils.showLong("加载完成");
                isLoading=false;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                ToastUtils.showLong("正在加载页面");
            }
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                ToastUtils.showLong("加载失败请重试");
                isLoading=false;
            }

            @Override
            public void onScaleChanged(WebView webView, float v, float v1) {
                super.onScaleChanged(webView, v, v1);
            }

        });
    }

    @Override
    public void initData() {
        initX5WebView("http://app.cvfans.net/H5/niche.html?token="+Constants.token);
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Build.VERSION.SDK_INT >= 21) {
                _mActivity.getWindow().setFlags(
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    private void initX5WebView(String url) {
        webView.getSettings().setDisplayZoomControls(false);
        webView.clearCache(true);
        IX5WebViewExtension ix5 = webView.getX5WebViewExtension();
        if (null != ix5) {
            ix5.setScrollBarFadingEnabled(false);
        }
        webView.loadUrl(url);
    }

    @Override
    public void onPause() {
        super.onPause();
        if (webView != null) {
            webView.onPause();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //释放资源
        if (webView != null) {
            webView.destroy();
            webView.clearCache(true);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null)
            webView.onResume();
    }
    @Override
    public boolean onBackPressedSupport() {

        if (webView.canGoBack()){
            webView.goBack();
            return true;
        }

        return super.onBackPressedSupport();
    }
}
