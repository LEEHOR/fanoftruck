package com.coahr.fanoftruck.mvp.view.Myself;

import android.os.Bundle;

import com.coahr.fanoftruck.R;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.Base.BaseFragment;

public class InvoiceInfoFragment extends BaseFragment {

    public static InvoiceInfoFragment newInstance(int type){
        InvoiceInfoFragment invoiceInfoFragment = new InvoiceInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("invoiceType", type);
        invoiceInfoFragment.setArguments(bundle);
        return invoiceInfoFragment;
    }

    @Override
    public BaseContract.Presenter getPresenter() {
        return null;
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_invoice_info;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
