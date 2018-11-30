package com.coahr.fanoftruck.mvp.view.Myself;

import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_register_C;
import com.coahr.fanoftruck.mvp.model.Bean.RegisterBean;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.presenter.Fragment_register_P;
import com.coahr.fanoftruck.widgets.BlockTextView;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:17
 * 注册
 */
public class Fragment_register extends BaseFragment<Fragment_register_C.Presenter> implements Fragment_register_C.View {


   @Inject
    Fragment_register_P p;
    @BindView(R.id.myTittle_register)
    MyTittleBar myTittle_register;
    @BindView(R.id.register_phone)
    EditText register_phone;
    @BindView(R.id.register_code)
    EditText register_code;
    @BindView(R.id.register_pass)
    EditText register_pass;
    @BindView(R.id.getRegister_code)
    BlockTextView getRegister_code;
    @BindView(R.id.ck_check)
    CheckBox cb_check;
    @BindView(R.id.user_agreement)
    TextView user_agreement;
    @BindView(R.id.tv_register)
    TextView tv_register;
    @BindView(R.id.register_email)
    EditText register_email;
    @BindView(R.id.register_userName)
    EditText register_userName;
    @BindView(R.id.register_pass_again)
    EditText register_pass_again;

    public static Fragment_register newInstance() {
        Fragment_register register = new Fragment_register();
        return register;
    }

    @Override
    public Fragment_register_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_register;
    }

    @Override
    public void initView() {
        myTittle_register.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        getRegister_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidateUtils.isMobile(register_phone.getText().toString())) {
                    getRegister_code.startGetCount();
                    getCode();
                } else {
                    ToastUtils.showLong("请输入正确手机号");
                }
            }
        });

        user_agreement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        tv_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //用户名
                if (TextUtils.isEmpty(register_userName.getText()) || register_userName.getText().toString().equals("") || register_userName.getText().toString().length() < 6) {
                    ToastUtils.showLong("请设置大于六个字符的用户名");
                    return;
                }
                //密码
                if (TextUtils.isEmpty(register_pass.getText()) || register_pass.getText().toString().equals("") || register_pass.getText().toString().length() < 6) {
                    ToastUtils.showLong("请设置大于六个字符的密码");
                    return;
                }
                //确认密码
                if (!TextUtils.equals(register_pass.getText(), register_pass_again.getText())) {
                    ToastUtils.showLong("两次的密码不相同");
                    return;
                }


                //邮箱验证
                if (!ValidateUtils.isEmail(register_email.getText().toString())) {
                    ToastUtils.showLong("请输入正确的邮箱");
                    return;
                }
                //手机号验证
                if (!ValidateUtils.isMobile(register_phone.getText().toString())) {
                    ToastUtils.showLong("请输入正确的手机号");
                    return;
                }

                //验证码
                if (TextUtils.isEmpty(register_code.getText()) || register_code.getText().toString().equals("") || register_code.getText().toString().length() != 6) {
                    ToastUtils.showLong("请填写验证码");
                    return;
                }

                if (!cb_check.isChecked()) {
                    ToastUtils.showLong("请仔细阅读用户协议并同意");
                    return;
                }

                registerAccount();

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void RegisterSuccess(RegisterBean registerBean) {
        ToastUtils.showLong(registerBean.getMsg());
        _mActivity.onBackPressed();
    }

    @Override
    public void RegisterFailure(String failure) {
        getRegister_code.stopGetCount();
        ToastUtils.showLong(failure);
    }

    @Override
    public void getVerifyCodeSuccess(VerifyCode verifyCode) {
        ToastUtils.showLong(verifyCode.getMsg());
    }

    @Override
    public void getVerifyCodeFailure(String Failure) {
        ToastUtils.showLong(Failure);
    }

    private void getCode() {
        Map map = new HashMap();
        map.put("phone", register_phone.getText().toString());
        p.getVerifyCode(map);
    }

    private void registerAccount() {
        Map map = new HashMap();
        map.put("phone", register_phone.getText().toString());
        map.put("userName", register_userName.getText().toString());
        map.put("pwd", register_pass.getText().toString());
        map.put("email", register_email.getText().toString());
        map.put("device_token", "");
        map.put("verify_code",register_code.getText().toString());
        p.Register(map);

    }
}
