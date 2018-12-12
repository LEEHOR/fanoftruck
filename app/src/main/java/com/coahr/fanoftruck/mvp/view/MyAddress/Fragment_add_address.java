package com.coahr.fanoftruck.mvp.view.MyAddress;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.Utils.ToastUtils;
import com.coahr.fanoftruck.Utils.ValidateUtils;
import com.coahr.fanoftruck.commom.Constants;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;
import com.coahr.fanoftruck.mvp.constract.Fragment_add_address_C;
import com.coahr.fanoftruck.mvp.model.Bean.add_AddressBean;
import com.coahr.fanoftruck.mvp.presenter.Fragment_add_address_P;
import com.coahr.fanoftruck.widgets.TittleBar.MyTittleBar;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by Leehor
 * on 2018/12/12
 * on 8:34
 */
public class Fragment_add_address extends BaseFragment<Fragment_add_address_C.Presenter> implements Fragment_add_address_C.View {

    @Inject
    Fragment_add_address_P p;
    @BindView(R.id.myaddress_mytitle)
    MyTittleBar myaddress_mytitle;
    @BindView(R.id.add_userName)
    EditText add_userName;
    @BindView(R.id.add_user_phone)
    EditText add_user_phone;
    @BindView(R.id.add_user_address)
    EditText add_user_address;
    @BindView(R.id.tv_submit)
    TextView tv_submit;
    private boolean isAdd; //防止提交多次

    public static Fragment_add_address newInstance() {
        return new Fragment_add_address();
    }

    @Override
    public Fragment_add_address_C.Presenter getPresenter() {
        return p;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_add_address;
    }

    @Override
    public void initView() {
        myaddress_mytitle.getLeftIcon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                _mActivity.onBackPressed();
            }
        });
        tv_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(add_userName.getText()) || add_userName.getText().toString().equals("")) {
                    ToastUtils.showLong("请填写正确的姓名");
                    return;
                }
                if (!ValidateUtils.isMobile(add_user_phone.getText().toString().trim())) {
                    ToastUtils.showLong("请填写正确的号码");
                    return;
                }
                if (TextUtils.isEmpty(add_user_address.getText()) || add_user_address.getText().toString().equals("")) {
                    ToastUtils.showLong("请填写正确的收货地址");
                    return;
                }
                if (!isAdd) {
                    isAdd = true;
                    add_address();
                } else {
                    ToastUtils.showLong("已经保存");
                }

            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void add_addressSuccess(add_AddressBean addAddressBean) {
        ToastUtils.showLong(addAddressBean.getJdata().getJmsg());
        isAdd = true;
    }

    @Override
    public void add_addressFailure(String failure) {
        ToastUtils.showLong(failure);
        isAdd = false;
    }

    private void add_address() {
        Map map = new HashMap();
        map.put("token", Constants.token);
        map.put("username", add_userName.getText().toString().trim());
        map.put("telephone", add_user_phone.getText().toString().trim());
        map.put("address", add_user_address.getText().toString().trim());
        p.add_address(map);
    }
}
