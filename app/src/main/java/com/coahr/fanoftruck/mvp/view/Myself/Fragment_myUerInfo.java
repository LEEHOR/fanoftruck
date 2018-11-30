package com.coahr.fanoftruck.mvp.view.Myself;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_UserInfo_P;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:06
 */
public class Fragment_myUerInfo extends BaseFragment<Fragment_userInfo_C.Presenter> implements Fragment_userInfo_C.View {
   @Inject
    Fragment_UserInfo_P p;

 public static  Fragment_myUerInfo newInstance() {
  Fragment_myUerInfo uerInfo=new Fragment_myUerInfo();
  return uerInfo;
 }

 @Override
    public Fragment_userInfo_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_userinfo;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
