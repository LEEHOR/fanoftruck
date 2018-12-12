package com.coahr.fanoftruck.mvp.model.Bean;

import com.coahr.fanoftruck.mvp.Base.BaseModel;
import com.coahr.fanoftruck.mvp.constract.Fragment_add_address_C;
import com.coahr.fanoftruck.mvp.constract.Fragment_address_list_C;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:32
 */
public class Fragment_add_address_M extends BaseModel<Fragment_add_address_C.Presenter> implements Fragment_add_address_C.Model{
    @Inject
    public Fragment_add_address_M() {
        super();
    }

    @Override
    public void add_address(Map<String, String> map) {
        mRxManager.add(createFlowable(new SimpleFlowableOnSubscribe<add_AddressBean>(getApiService().add_address(map)))
                .subscribeWith(new SimpleDisposableSubscriber<add_AddressBean>() {
                    @Override
                    public void _onNext(add_AddressBean add_addressBean) {
                        if (getPresenter() != null) {
                            if (add_addressBean.getCode()==0) {
                                getPresenter().add_addressSuccess(add_addressBean);
                            }else {
                                getPresenter().add_addressFailure(add_addressBean.getMsg());
                            }
                        }
                    }
                }));
    }
}
