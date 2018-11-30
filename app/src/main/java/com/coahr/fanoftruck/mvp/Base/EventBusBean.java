package com.coahr.fanoftruck.mvp.Base;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 14:41
 */
public class EventBusBean {
    private int code;
    private String msg;

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

    public EventBusBean(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public EventBusBean() {
    }
}
