package com.coahr.fanoftruck.mvp.view.Home;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.KeyBoardUtils;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_maintenance_viewp_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_maintenance_viewp_P;
import com.coahr.fanoftruck.mvp.view.Home.adapter.Fragment_maintenance_viewpager_adapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/21
 * on 0:28
 * 维修视频
 */
public class Fragment_MaintenanceVideo_viewPage extends BaseFragment<Fragment_maintenance_viewp_C.Presenter> implements Fragment_maintenance_viewp_C.View,OnEditorActionListener{
    @Inject
    Fragment_maintenance_viewp_P p;

    @BindView(R.id.mytitle)
    MyTittleBar myTittleBar;
    @BindView(R.id.tab)
    TabLayout tabLayout;
    @BindView(R.id.viewpager)
    ViewPager viewPager;


    public static Fragment_MaintenanceVideo_viewPage newInstance() {
        return  new Fragment_MaintenanceVideo_viewPage();
    }


    @Override
    public Fragment_maintenance_viewp_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_maintenance_video;
    }

    @Override
    public void initView() {
            myTittleBar.getRightIcon().setVisibility(View.VISIBLE); //右边搜索框
            myTittleBar.getRightIcon().setImageDrawable(getResources().getDrawable(R.mipmap.search));

            myTittleBar.getRightIcon().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setVisibility(View.VISIBLE);
                    myTittleBar.getTvTittle().setVisibility(View.GONE);
                    myTittleBar.getRightIcon().setVisibility(View.GONE);
                    myTittleBar.getRightText().setVisibility(View.VISIBLE);
                    myTittleBar.getRightText().setText("取消");
                    myTittleBar.getRightText().setTextColor(getResources().getColor(R.color.red_1));
                }
            });

            myTittleBar.getRightText().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setFocusable(false);
                    myTittleBar.getEditSearch().setText(null);
                    myTittleBar.getEditSearch().setHint("请输入要查询的内容");
                    myTittleBar.getTvTittle().setVisibility(View.VISIBLE);
                    myTittleBar.getEditSearch().setVisibility(View.GONE);
                    myTittleBar.getRightText().setVisibility(View.GONE);
                    myTittleBar.getRightIcon().setVisibility(View.VISIBLE);
                }
            });

            myTittleBar.getEditSearch().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    myTittleBar.getEditSearch().setFocusable(true);
                    myTittleBar.getEditSearch().setFocusableInTouchMode(true);
                }
            });


            myTittleBar.getEditSearch().addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                   // myTittleBar.getRightText().setVisibility(View.VISIBLE);
                   // myTittleBar.getRightIcon().setVisibility(View.GONE);

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
            myTittleBar.getEditSearch().setOnEditorActionListener(this);
    }

    @Override
    public void initData() {
        Fragment_maintenance_viewpager_adapter adapter=new Fragment_maintenance_viewpager_adapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(0);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
        if (i == EditorInfo.IME_ACTION_SEARCH) {
            KeyBoardUtils.hideKeybord(textView, getActivity());
            } else {
            return true;
        }
        return false;
    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
