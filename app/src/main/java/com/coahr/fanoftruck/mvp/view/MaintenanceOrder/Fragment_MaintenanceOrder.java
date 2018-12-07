package com.coahr.fanoftruck.mvp.view.MaintenanceOrder;

import android.graphics.Bitmap;
import android.os.Build;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/5
 * on 17:49
 */
public class Fragment_MaintenanceOrder extends BaseFragment {

    @BindView(R.id.mytittle)
    MyTittleBar myTittleBar;
    @BindView(R.id.my_webView)
    X5WebViewByMyShelf webView;
    @BindView(R.id.mySwipe)
    SwipeRefreshLayout mySwipe;
    private boolean isLoading;

    public static Fragment_MaintenanceOrder newInstance() {
        return new Fragment_MaintenanceOrder();
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintenance_order;
    }

    @Override
    public void initView() {
        initHardwareAccelerate();
        mySwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    webView.reload();
                    isLoading=true;
                }

            }
        });
        myTittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
    }

    @Override
    public void initData() {
        //
        initX5WebView("http://app.cvfans.net/H5/maintenance_order.html?token="+Constants.token);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mySwipe.setRefreshing(false);
                isLoading=false;
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
            }
        });
    }

    /**
     * 启用硬件加速
     */
    private void initHardwareAccelerate() {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                getActivity().getWindow().setFlags(
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                        android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED);
            }
        } catch (Exception e) {
        }
    }

    private void initX5WebView(String url) {
        webView.getSettings().setDisplayZoomControls(false);
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
        if (webView != null)
            webView.destroy();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (webView != null)
            webView.onResume();
    }
}
