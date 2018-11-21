package com.coahr.fanoftruck.mvp.view.Myself;

import com.baidu.location.BDLocation;
import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_myself_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_myself_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 11:12
 */
public class Fragment_Myself extends BaseFragment<Fragment_myself_C.Presenter> implements Fragment_myself_C.View {
@Inject
    Fragment_myself_P p;
    public static Fragment_Myself newInstance() {
        return new Fragment_Myself();
    }

    @Override
    public Fragment_myself_C.Presenter getPresenter() {
        return p;
    }


    @Override
    public int bindLayout() {
        return R.layout.fragment_myself;
    }

    @Override
    public void initView() {

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
