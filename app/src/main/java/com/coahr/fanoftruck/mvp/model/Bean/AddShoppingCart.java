package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 9:29
 */
public class AddShoppingCart {
    private int code;
    private String msg;
    private getBuyCarCode.JdataBean jdata;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public getBuyCarCode.JdataBean getJdata() {
        return jdata;
    }

    public void setJdata(getBuyCarCode.JdataBean jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
    }
}
