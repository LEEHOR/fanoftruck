package com.coahr.fanoftruck.widgets.HttpUtils;

/**
 * Created by Leehor
 * on 2018/11/19
 * on 13:01
 */
public interface HttpUtilListener {
    void getAddressSuccess(String body);

    void getAddressFailure(String e);
}
