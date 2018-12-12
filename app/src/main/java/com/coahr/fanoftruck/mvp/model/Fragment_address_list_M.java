package com.coahr.fanoftruck.mvp.model;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.SaveUserCar;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:32
 */
public class Fragment_address_list_M extends BaseModel<Fragment_address_list_C.Presenter> implements Fragment_address_list_C.Model{
    @Inject
    public Fragment_address_list_M() {
        super();
    }

    @Override
    public void getAddressList(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<AddressListBean>(getApiService().getAddressList(map)))
                .subscribeWith(new SimpleDisposableSubscriber<AddressListBean>() {
                    @Override
                    public void _onNext(AddressListBean addressListBean) {
                        if (getPresenter() != null) {
                            if (addressListBean.getCode()==0) {
                                getPresenter().getAddressSuccess(addressListBean);
                            }else {
                                getPresenter().getAddressFailure(addressListBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void delAddress(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<del_addressBean>(getApiService().del_address(map)))
                .subscribeWith(new SimpleDisposableSubscriber<del_addressBean>() {
                    @Override
                    public void _onNext(del_addressBean delAddressBean) {
                        if (getPresenter() != null) {
                            if (delAddressBean.getCode()==0) {
                                getPresenter().delAddressSuccess(delAddressBean);
                            }else {
                                getPresenter().delAddressFailure(delAddressBean.getMsg());
                            }
                        }
                    }
                }));
    }

    @Override
    public void setAddressDefault(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<set_address_defaultBean>(getApiService().set_addressDefault(map)))
                .subscribeWith(new SimpleDisposableSubscriber<set_address_defaultBean>() {
                    @Override
                    public void _onNext(set_address_defaultBean addressDefaultBean) {
                        if (getPresenter() != null) {
                            if (addressDefaultBean.getCode()==0) {
                                getPresenter().setAddressDefaultSuccess(addressDefaultBean);
                            }else {
                                getPresenter().setAddressDefaultFailure(addressDefaultBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
