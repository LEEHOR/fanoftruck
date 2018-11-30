package com.coahr.fanoftruck.mvp.view.Myself;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_forgetPass_C;
import com.coahr.fanoftruck.mvp.model.Bean.ForgetPass;
import com.coahr.fanoftruck.mvp.model.Bean.VerifyCode;
import com.coahr.fanoftruck.mvp.presenter.Fragment_forgetPass_P;
import com.coahr.fanoftruck.widgets.BlockTextView;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:17
 * 忘记密码
 */
public class Fragment_forgetPass extends BaseFragment<Fragment_forgetPass_C.Presenter> implements Fragment_forgetPass_C.View {
    @Inject
    Fragment_forgetPass_P p;
    @BindView(R.id.forget_phone)
    EditText forget_phone;
    @BindView(R.id.forget_Code)
    EditText forget_Code;
    @BindView(R.id.send_code)
    BlockTextView send_code;
    @BindView(R.id.forget_pass)
    EditText forget_pass;
    @BindView(R.id.forget_submit)
    TextView forget_submit;

    public static Fragment_forgetPass newInstance() {
        return new Fragment_forgetPass();
    }

    @Override
    public Fragment_forgetPass_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_forgetpassword;
    }

    @Override
    public void initView() {
        send_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (ValidateUtils.isMobile(forget_phone.getText().toString())){
                    send_code.startGetCount();
                    //获取验证码
                    getverify_code();
                } else {
                    ToastUtils.showLong("请输入正确的手机号码");
                }
            }
        });
        forget_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!ValidateUtils.isMobile(forget_phone.getText().toString())){
                    ToastUtils.showLong("请输入正确的手机号码");
                    return;
                }
                if (TextUtils.isEmpty(forget_Code.getText()) || forget_Code.getText().toString().equals("") || forget_Code.getText().toString().length()!=6){
                    ToastUtils.showLong("请输入正确的验证码");
                    return;
                }
                if (TextUtils.isEmpty(forget_pass.getText()) || forget_pass.getText().toString().equals("") || forget_pass.getText().toString().length()<6){
                    ToastUtils.showLong("请输入大于6为字符的密码");
                    return;
                }
                //修改密码
                getForgetPass();
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void forgetPassSuccess(ForgetPass forgetPass) {
            ToastUtils.showLong(forgetPass.getMsg());
            _mActivity.onBackPressed();
    }

    @Override
    public void forgetPassFailure(String failure) {
            send_code.stopGetCount();
            ToastUtils.showLong(failure);
    }

    @Override
    public void getVerifyCodeSuccess(VerifyCode verifyCode) {
        send_code.stopGetCount();
        ToastUtils.showLong(verifyCode.getMsg());
    }

    @Override
    public void getVerifyCodeFailure(String Failure) {
        send_code.stopGetCount();
        ToastUtils.showLong(Failure);
    }

    private void getForgetPass(){
        Map map=new HashMap();
        map.put("phone",forget_phone.getText().toString());
        map.put("verify_code",forget_Code.getText().toString());
        map.put("pwd",forget_pass.getText().toString());
        p.forgetPass(map);
    }

    private void getverify_code(){
        Map map=new HashMap();
        map.put("phone",forget_phone.getText().toString());
        p.getVerifyCode(map);
    }
}
