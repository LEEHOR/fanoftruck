package com.coahr.fanoftruck.mvp.presenter;

import com.coahr.fanoftruck.mvp.Base.BasePresenter;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;
import com.coahr.fanoftruck.mvp.model.Fragment_address_list_M;
import com.coahr.fanoftruck.mvp.view.MyAddress.Fragment_address_list;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:34
 */
public class Fragment_address_list_P extends BasePresenter<Fragment_address_list_C.View,Fragment_address_list_C.Model> implements Fragment_address_list_C.Presenter {
    @Inject
    public Fragment_address_list_P(Fragment_address_list mview, Fragment_address_list_M mModel) {
        super(mview, mModel);
    }

    @Override
    public void getAddressList(Map<String, String> map) {
        if (mModle != null) {
            mModle.getAddressList(map);
        }
    }

    @Override
    public void getAddressSuccess(AddressListBean addressListBean) {
        if (getView() != null) {
            getView().getAddressSuccess(addressListBean);
        }
    }

    @Override
    public void getAddressFailure(String failure) {
        if (getView() != null) {
            getView().getAddressFailure(failure);
        }
    }

    @Override
    public void delAddress(Map<String, String> map) {
        if (mModle != null) {
            mModle.delAddress(map);
        }
    }

    @Override
    public void delAddressSuccess(del_addressBean addressBean) {
        if (getView() != null) {
            getView().delAddressSuccess(addressBean);
        }
    }

    @Override
    public void delAddressFailure(String failure) {
        if (getView() != null) {
            getView().delAddressFailure(failure);
        }
    }

    @Override
    public void setAddressDefault(Map<String, String> map) {
        if (mModle != null) {
            mModle.setAddressDefault(map);
        }
    }

    @Override
    public void setAddressDefaultSuccess(set_address_defaultBean addressDefaultSuccess) {
        if (getView() != null) {
            getView().setAddressDefaultSuccess(addressDefaultSuccess);
        }
    }

    @Override
    public void setAddressDefaultFailure(String failure) {
        if (getView() != null) {
            getView().setAddressDefaultFailure(failure);
        }
    }
}
