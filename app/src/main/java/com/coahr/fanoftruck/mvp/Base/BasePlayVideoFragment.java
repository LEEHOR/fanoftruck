package com.coahr.fanoftruck.mvp.Base;

import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ScreenUtils;

import butterknife.ButterKnife;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 18:59
 */
public abstract class BasePlayVideoFragment <P extends BaseContract.Presenter> extends BaseFragment implements BaseContract.View  {
    public View  child_footView;
    private boolean isonHiddenChangedMethodInvoke = false;
    private boolean hasload = false;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(bindLayout(), container, false);
        unbinder = ButterKnife.bind(this, view);
        child_footView =inflater.inflate(R.layout.recyclerview_item_foot, null, false);
        //由于沉浸式要留白 标题栏，在这里统一设置，支出statusbar的空间，之后每个fragment的头顶第一个子view，都要
        //以一个viewgroup包含要显示tittle的子view形式进行布局，则此代码正确有效
        View tittleView = ((ViewGroup) view.getRootView()).getChildAt(0);
        if (tittleView != null) {
            tittleView.setPadding(tittleView.getPaddingLeft(), ScreenUtils.getStatusBarHeight(BaseApplication.mContext), tittleView.getPaddingRight(), tittleView.getPaddingBottom());
        }
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        if (!isonHiddenChangedMethodInvoke) {
            initView();
            initData();
            hasload = true;
        }
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        isonHiddenChangedMethodInvoke= true;
        if (!hidden&&!hasload) {
            initView();
            initData();
            hasload = true;
        }
    }
}
