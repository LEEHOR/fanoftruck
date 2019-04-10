package com.coahr.fanoftruck.widgets.AltDialog;

import android.app.Dialog;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.coahr.fanoftruck.R;
import com.socks.library.KLog;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Author： hengzwd on 2018/8/17.
 * Email：hengzwdhengzwd@qq.com
 * 支付工具选择弹窗
 */
public class PayTypeSelectDialogFragment extends AppCompatDialogFragment {


    @BindView(R.id.rb_zfb)
    RadioButton rbZfb;
    @BindView(R.id.rb_wx)
    RadioButton rbWx;
    @BindView(R.id.rdg_pay)
    RadioGroup rdgPay;
    @BindView(R.id.fl_paytype_container)
    FrameLayout flPaytypeContainer;
    Unbinder unbinder;


    private OnPayTypeSelectListener onpayTypeSelectListener;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_paytype, container, false);
        unbinder = ButterKnife.bind(this, view);
        init();
        return view;
    }

    private void init() {
        flPaytypeContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        rdgPay.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_zfb:
                        KLog.e("lizhiguo", "选择了支付宝");
                        onpayTypeSelectListener.onItemSelect("ali");
                        break;
                    case R.id.rb_wx:
                        KLog.e("lizhiguo", "选择了微信");
                        onpayTypeSelectListener.onItemSelect("wx");
                        break;
                }
            }
        });
    }


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog = super.onCreateDialog(savedInstanceState);
        Window window = dialog.getWindow();
        if (window != null) {
            window.getDecorView().setPadding(0, 0, 0, 0);
            window.setBackgroundDrawableResource(android.R.color.transparent);
            window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
            window.setWindowAnimations(R.style.bottom_in_out_animation);
        }
        return dialog;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    public  void setOnpayTypeSelectListener(OnPayTypeSelectListener onpayTypeSelectListener){
        this.onpayTypeSelectListener=onpayTypeSelectListener;
    }

    public interface  OnPayTypeSelectListener{

        void onItemSelect(String payType);
    }

}
