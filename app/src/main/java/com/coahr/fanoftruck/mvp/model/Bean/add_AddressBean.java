package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/3
 * on 17:46
 */
public class add_AddressBean {

    /**
     * code : 0
     * msg : success
     * jdata : {"jmsg":"设置成功"}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

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

    public JdataBean getJdata() {
        return jdata;
    }

    public void setJdata(JdataBean jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
        /**
         * jmsg : 设置成功
         */

        private String jmsg;

        public String getJmsg() {
            return jmsg;
        }

        public void setJmsg(String jmsg) {
            this.jmsg = jmsg;
        }
    }
}
