package com.coahr.fanoftruck.mvp.view.Myself;

import android.view.View;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_userInfo_C;
import com.coahr.fanoftruck.mvp.presenter.Fragment_UserInfo_P;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 18:06
 */
public class Fragment_myUerInfo extends BaseFragment<Fragment_userInfo_C.Presenter> implements Fragment_userInfo_C.View, View.OnClickListener {
   @Inject
    Fragment_UserInfo_P p;
   @BindView(R.id.tv_certification)
    TextView tv_certification;

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
        tv_certification.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    public  static void loadUserInfo(){

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_certification:
                start(Fragment_certification.newInstance());
                break;
        }
    }
}
