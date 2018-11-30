package com.coahr.fanoftruck.mvp.view.Myself;

import android.view.View;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_myCar_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myCar_P;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 17:58
 */
public class Fragment_myCar extends BaseFragment<Fragment_myCar_C.Presenter> implements Fragment_myCar_C.View {

   @Inject
    Fragment_myCar_P p;
   @BindView(R.id.mytittle)
    MyTittleBar mytittle;

    public static Fragment_myCar newInstance() {
        return new Fragment_myCar();
    }

    @Override
    public Fragment_myCar_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_mycar;
    }

    @Override
    public void initView() {
        mytittle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });

        mytittle.getRightText().setVisibility(View.VISIBLE);
        mytittle.getRightText().setText("添加车辆");
        mytittle.getRightText().setTextColor(getResources().getColor(R.color.material_blue_550));
        mytittle.getRightText().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            start(Fragment_addCar.newInstance());
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onLocationSuccess(BDLocation location) {

    }

    @Override
    public void onLocationFailure(int failure) {

    }
}
