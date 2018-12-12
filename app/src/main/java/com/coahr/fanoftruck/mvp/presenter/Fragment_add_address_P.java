package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_add_address_C;
import com.coahr.fanoftruck.mvp.model.Bean.Fragment_add_address_M;
import com.coahr.fanoftruck.mvp.model.Bean.add_AddressBean;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_add_address;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:34
 */
public class Fragment_add_address_P extends BasePresenter<Fragment_add_address_C.View,Fragment_add_address_C.Model> implements Fragment_add_address_C.Presenter {
    @Inject
    public Fragment_add_address_P(Fragment_add_address mview, Fragment_add_address_M mModel) {
        super(mview, mModel);
    }


    @Override
    public void add_address(Map<String, String> map) {
        if (mModle != null) {
            mModle.add_address(map);
        }
    }

    @Override
    public void add_addressSuccess(add_AddressBean addAddressBean) {
        if (getView() != null) {
            getView().add_addressSuccess(addAddressBean);
        }
    }

    @Override
    public void add_addressFailure(String failure) {
        if (getView() != null) {
            getView().add_addressFailure(failure);
        }
    }
}
