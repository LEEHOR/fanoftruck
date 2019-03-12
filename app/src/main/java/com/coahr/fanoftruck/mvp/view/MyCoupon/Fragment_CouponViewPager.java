package com.coahr.fanoftruck.mvp.view.MyCoupon;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.Bean.MessageEvent_coupon;
import com.coahr.fanoftruck.mvp.view.MyCoupon.adapter.CouponViewPagerAdapter;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;

/**
 * 我的优惠券主页
 */
public class Fragment_CouponViewPager extends BaseFragment {

    @BindView(R.id.coupon_tittle)
    MyTittleBar tittleBar;
    @BindView(R.id.coupon_radioGroup)
    RadioGroup radioGroup;
    @BindView(R.id.coupon_Overdue)
    RadioButton radioButton_Right;
    @BindView(R.id.coupon_Standby)
    RadioButton radioButton_Left;
    @BindView(R.id.coupon_viewpager)
    ViewPager viewPager;
    @BindView(R.id.go_receive_coupon)
    Button go_receive_coupon;



    private CouponViewPagerAdapter pagerAdapter;

    public static Fragment_CouponViewPager newInstance(int FormFragment, double orderPrice) {
        Fragment_CouponViewPager fragment = new Fragment_CouponViewPager();
        Bundle arg = new Bundle();
        arg.putInt("from",FormFragment);
        arg.putDouble("orderPrice",orderPrice);
        fragment.setArguments(arg);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_coupon_viewpager;
    }

    @Override
    public void initView() {
        tittleBar.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        radioButton_Left.setChecked(true);
        radioGroup.setOnCheckedChangeListener(new RadioGroupListener());
        go_receive_coupon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_CouponReceive.newInstance());
            }
        });

    }

    @Override
    public void initData() {
        int from = getArguments().getInt("from");
        double orderPrice = getArguments().getDouble("orderPrice");
        pagerAdapter = new CouponViewPagerAdapter(getChildFragmentManager(),from,orderPrice);
        viewPager.setAdapter(pagerAdapter);
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(2);
        viewPager.addOnPageChangeListener(new ViewPageListener());
    }

    class RadioGroupListener implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup radioGroup, int i) {
            switch (i) {
                case R.id.coupon_Overdue:
                    viewPager.setCurrentItem(1);
                    break;
                case R.id.coupon_Standby:
                    viewPager.setCurrentItem(0);
                    break;
            }
        }
    }

    class ViewPageListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


        }

        @Override
        public void onPageSelected(int position) {
            switch (position) {
                case 0:
                    radioButton_Left.setChecked(true);
                    break;
                case 1:
                    radioButton_Right.setChecked(true);
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    /*
     * 获取屏幕的宽度
     */
    public int getW(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowMgr = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        windowMgr.getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if(EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageEvent_coupon messageEvent) {
        radioButton_Left.setText("待使用" + "(" + messageEvent.getLeft_count() + ")");
        radioButton_Right.setText("已过期" + "(" + messageEvent.getRight_count() + ")");

    }

}
