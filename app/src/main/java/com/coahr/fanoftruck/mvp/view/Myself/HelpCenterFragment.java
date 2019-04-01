package com.coahr.fanoftruck.mvp.view.Myself;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import androidx.cardview.widget.CardView;
import butterknife.BindView;
import butterknife.OnClick;

public class HelpCenterFragment extends BaseFragment {
    @BindView(R.id.mytitle)
    MyTittleBar mytitle;
    @BindView(R.id.cardview_install)
    CardView cardviewInstall;
    @BindView(R.id.cardview_bank_pay)
    CardView cardviewBankPay;
    @BindView(R.id.cardview_truck_upkeep)
    CardView cardviewTruckUpkeep;
    @BindView(R.id.cardview_change_owner)
    CardView cardviewChangeOwner;
    @BindView(R.id.cardview_navigation_crash)
    CardView cardviewNavigationCrash;

    public static HelpCenterFragment newInstance() {
        return new HelpCenterFragment();
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_help_center;
    }

    @Override
    public void initView() {
        TextView rightText = mytitle.getRightText();
        rightText.setVisibility(View.VISIBLE);
        rightText.setText("我的下载");
        rightText.setTextColor(Color.parseColor("#1C54BC"));
        rightText.setOnClickListener(titleClickListener);

        mytitle.getLeftIcon().setOnClickListener(titleClickListener);
    }

    @Override
    public void initData() {

    }

    private View.OnClickListener titleClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.iv_left:
                    _mActivity.onBackPressed();
                    break;
                case R.id.tv_right:
                    break;
            }
        }
    };

    @OnClick({R.id.cardview_install, R.id.cardview_bank_pay, R.id.cardview_truck_upkeep, R.id.cardview_change_owner, R.id.cardview_navigation_crash})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.cardview_install:

                break;
            case R.id.cardview_bank_pay:

                break;
            case R.id.cardview_truck_upkeep:

                break;
            case R.id.cardview_change_owner:

                break;
            case R.id.cardview_navigation_crash:

                break;
        }
    }
}
