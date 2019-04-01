package com.coahr.fanoftruck.mvp.view.MyWebView;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.View;
import android.widget.ProgressBar;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/19
 * on 16:16
 */
public class Fragment_myWebView extends BaseFragment {
    @BindView(R.id.swipe)
    SwipeRefreshLayout mywebview_swipe;
    @BindView(R.id.my_webview_title)
    MyTittleBar my_webview_title;
    @BindView(R.id.my_webView)
    X5WebViewByMyShelf webView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    private String url;
    private String title;
    private boolean isLoading;

    public static Fragment_myWebView newInstance(String url,String title) {
        Fragment_myWebView fragment_myWebView=new Fragment_myWebView();
        Bundle bundle=new Bundle();
        bundle.putString("url",url);
        bundle.putString("title",title);
        fragment_myWebView.setArguments(bundle);
        return fragment_myWebView;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_webview;
    }

    @Override
    public void initView() {
        initHardwareAccelerate();
        my_webview_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        mywebview_swipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!isLoading) {
                    mywebview_swipe.post(new Runnable() {
                        @Override
                        public void run() {
                            isLoading=true;
                            webView.reload();
                            progressBar.setVisibility(View.GONE);
                        }
                    });
                } else {
                    ToastUtils.showLong("加载中");
                }
            }
        });
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    progressBar.setProgress(i,true);
                } else {
                    progressBar.setProgress(i);
                }
            }
        });
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView webView, String s) {
                super.onPageFinished(webView, s);
                mywebview_swipe.setRefreshing(false);
                ToastUtils.showLong("加载完成");
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
                super.onPageStarted(webView, s, bitmap);
                ToastUtils.showLong("正在加载页面");
                progressBar.setVisibility(View.VISIBLE);
            }
            @Override
            public void onReceivedError(WebView webView, int i, String s, String s1) {
                super.onReceivedError(webView, i, s, s1);
                ToastUtils.showLong("加载失败请重试");
                progressBar.setVisibility(View.GONE);
                isLoading=false;
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            url = getArguments().getString("url");
            title = getArguments().getString("title");
            my_webview_title.getTvTittle().setText(title);
        }
        initX5WebView(url);
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
