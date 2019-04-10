package com.coahr.fanoftruck.mvp.view.Myself;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.PreferenceUtils;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseApplication;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.model.Bean.EventBusBean;
import com.coahr.fanoftruck.mvp.constract.Fragment_login_C;
import com.coahr.fanoftruck.mvp.model.Bean.LoginBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_Login_P;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;
import com.socks.library.KLog;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/23
 * on 11:38
 * 登录页面
 */
public class Fragment_login extends BaseFragment<Fragment_login_C.Presenter> implements Fragment_login_C.View {
    @Inject
    Fragment_Login_P p;
    @BindView(R.id.et_login_account)
    EditText et_login_account;
    @BindView(R.id.et_login_pass)
    EditText et_login_pass;
    @BindView(R.id.tv_login_btn)
    TextView tv_login_btn;
    @BindView(R.id.register_Account)
    TextView register_Account;
    @BindView(R.id.forget_pass)
    TextView forget_pass;
    @BindView(R.id.login_title)
    MyTittleBar login_title;
    private int toFragment;

    /**
     *
     * @param toFragment
     *          来自哪个页面
     * @return
     */
    public static Fragment_login newInstance(int toFragment) {
        Fragment_login login=new Fragment_login();
        Bundle bundle=new Bundle();
        bundle.putInt("toFragment",toFragment);
        login.setArguments(bundle);
        return login;

    }


    @Override
    public Fragment_login_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_login;
    }

    @Override
    public void initView() {
        login_title.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        //账户
        et_login_account.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable) && !TextUtils.isEmpty(et_login_pass.getText())
                        && !editable.toString().equals("") && !et_login_pass.getText().toString().equals("")) {
                    tv_login_btn.setEnabled(true);
                    tv_login_btn.setBackground(getResources().getDrawable(R.drawable.business_shape_bule700_white));
                } else {
                    tv_login_btn.setEnabled(false);
                    tv_login_btn.setBackground(getResources().getDrawable(R.drawable.business_shape_gray600_gray));
                }
            }
        });
        //密码
        et_login_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable) && !TextUtils.isEmpty(et_login_account.getText())
                        && !editable.toString().equals("") && !et_login_account.getText().toString().equals("")) {
                    tv_login_btn.setEnabled(true);
                    tv_login_btn.setBackground(getResources().getDrawable(R.drawable.business_shape_bule700_white));
                } else {
                    tv_login_btn.setEnabled(false);
                    tv_login_btn.setBackground(getResources().getDrawable(R.drawable.business_shape_gray600_gray));
                }
            }
        });
        //登录
        tv_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!et_login_account.getText().toString().equals("") && !et_login_pass.getText().toString().equals("")) {
                    Map map = new HashMap();
                    map.put("phone", et_login_account.getText().toString());
                    map.put("pwd", et_login_pass.getText().toString());
                    p.Login(map);
                }
            }
        });

        //注册
        register_Account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                start(Fragment_register.newInstance());
            }
        });
        //忘记密码
        forget_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                start(Fragment_forgetPass.newInstance());
            }
        });
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            toFragment = getArguments().getInt("toFragment");
        }
    }


    @Override
    public void LoginSuccess(LoginBean loginBean) {
        PreferenceUtils.setPrefString(BaseApplication.mContext, Constants.token_key, loginBean.getJdata().getToken());
        PreferenceUtils.setPrefString(BaseApplication.mContext, Constants.uid_key, loginBean.getJdata().getUid());
        PreferenceUtils.setPrefString(BaseApplication.mContext, Constants.nickname_key, loginBean.getJdata().getNickname());
        PreferenceUtils.setPrefString(BaseApplication.mContext, Constants.telephone_key, loginBean.getJdata().getTelephone());
        PreferenceUtils.setPrefString(BaseApplication.mContext, Constants.headImg_key, loginBean.getJdata().getHeadImg());
        Constants.token = loginBean.getJdata().getToken();
        Constants.uid = loginBean.getJdata().getUid();
        Constants.nickname = loginBean.getJdata().getNickname();
        Constants.telephone = loginBean.getJdata().getTelephone();
        Constants.headImg = loginBean.getJdata().getHeadImg();
        ToastUtils.showLong(loginBean.getMsg());
        KLog.e("lizhiguo", "token == " + Constants.token);
        if (toFragment==Constants.MainActivity) {
            EventBus.getDefault().postSticky(new EventBusBean(1, "success"));
            _mActivity.onBackPressed();
        }
        if (toFragment==Constants.Fragment_Store_Detail){
            _mActivity.onBackPressed();
        }
        if (toFragment==Constants.Fragment_MaintenanceVideo_viewPage){
            _mActivity.onBackPressed();
        }
        if (toFragment ==Constants.Fragment_myUserInfo){
            _mActivity.onBackPressed();
        }
    }

    @Override
    public void LoginFailure(String failure) {
        ToastUtils.showLong(failure);
    }
}
