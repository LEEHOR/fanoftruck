package com.coahr.fanoftruck.mvp.view.BusinessOpportunity;

import android.content.Intent;
import android.os.Build;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.view.ContainerActivity;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;


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
        myTittleBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(_mActivity, ContainerActivity.class);
                intent.putExtra("tofragment", Constants.Fragment_recommendCar);
                startActivity(intent);
            }
        });
        initHardwareAccelerate();
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
