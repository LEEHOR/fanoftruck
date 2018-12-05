package com.coahr.fanoftruck.mvp.view.Myself;

import android.view.View;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 11:35
 * 关于我们
 */
public class Fragment_about_us extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;

    public static Fragment_about_us newInstance() {
        return  new Fragment_about_us();
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_about_us;
    }

    @Override
    public void initView() {
         myTittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 _mActivity.onBackPressed();
             }
         });
    }

    @Override
    public void initData() {

    }
}
