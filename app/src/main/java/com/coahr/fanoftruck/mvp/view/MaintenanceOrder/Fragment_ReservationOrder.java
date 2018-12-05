package com.coahr.fanoftruck.mvp.view.MaintenanceOrder;

import android.os.Build;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.coahr.fanoftruck.widgets.x5web.X5WebViewByMyShelf;
import com.tencent.smtt.export.external.extension.interfaces.IX5WebViewExtension;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/5
 * on 18:13
 * 维修预约订单
 */
public class Fragment_ReservationOrder extends BaseFragment {
    @BindView(R.id.mytittle)
    MyTittleBar myTittleBar;
    @BindView(R.id.my_webView)
    X5WebViewByMyShelf webView;

    public static Fragment_ReservationOrder newInstance() {
        return new Fragment_ReservationOrder();
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
    }

    @Override
    public void initData() {
        initX5WebView("http://app.cvfans.net/H5/maintenance_order.html?token="+Constants.token);
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
