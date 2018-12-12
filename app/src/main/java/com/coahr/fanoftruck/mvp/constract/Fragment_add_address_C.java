package com.coahr.fanoftruck.mvp.constract;

import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.add_AddressBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_add_address_C {

    interface View extends BaseContract.View {

        void add_addressSuccess(add_AddressBean addAddressBean);

        void add_addressFailure(String failure);
    }

    interface Presenter extends BaseContract.Presenter {

        void add_address(Map<String, String> map);

        void add_addressSuccess(add_AddressBean addAddressBean);

        void add_addressFailure(String failure);

    }

    interface Model extends BaseContract.Model {

        void add_address(Map<String, String> map);
    }
}
