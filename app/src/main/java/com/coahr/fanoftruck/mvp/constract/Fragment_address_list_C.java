package com.coahr.fanoftruck.mvp.constract;

import com.amap.api.location.AMapLocation;
import com.coahr.fanoftruck.mvp.Base.BaseContract;
import com.coahr.fanoftruck.mvp.model.Bean.AddressListBean;
import com.coahr.fanoftruck.mvp.model.Bean.del_addressBean;
import com.coahr.fanoftruck.mvp.model.Bean.set_address_defaultBean;

import java.util.Map;

/**
 * Created by Leehor
 * on 2018/11/6
 * on 17:49
 */
public interface Fragment_address_list_C {

    interface View extends BaseContract.View {


        void getAddressSuccess(AddressListBean addressListBean);

        void getAddressFailure(String failure);

        void setAddressDefaultSuccess(set_address_defaultBean addressDefaultSuccess);

        void setAddressDefaultFailure(String failure);

        void delAddressSuccess(del_addressBean addressBean);

        void delAddressFailure(String failure);

    }

    interface Presenter extends BaseContract.Presenter {
        void getAddressList(Map<String, String> map);

        void getAddressSuccess(AddressListBean addressListBean);

        void getAddressFailure(String failure);

        void delAddress(Map<String, String> map);

        void delAddressSuccess(del_addressBean addressBean);

        void delAddressFailure(String failure);

        void setAddressDefault(Map<String, String> map);

        void setAddressDefaultSuccess(set_address_defaultBean addressDefaultSuccess);

        void setAddressDefaultFailure(String failure);

    }

    interface Model extends BaseContract.Model {
        void getAddressList(Map<String, String> map);

        void delAddress(Map<String, String> map);

        void setAddressDefault(Map<String, String> map);
    }
}
