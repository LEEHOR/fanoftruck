package com.coahr.fanoftruck.mvp.Base;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.DensityUtils;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.Utils.ScreenUtils;
import com.coahr.fanoftruck.Utils.imageLoader.Imageloader;


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
        addFooterView = inflater.inflate(R.layout.recyclerview_item_foot, container, false);
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

}
