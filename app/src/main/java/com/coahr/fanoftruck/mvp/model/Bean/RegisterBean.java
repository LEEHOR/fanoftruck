package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/11/30
 * on 9:31
 */
public class RegisterBean {

    /**
     * code : 0
     * msg : success
     * jdata : {"verify_code":123456}
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
         * verify_code : 123456
         */

        private int verify_code;

        public int getVerify_code() {
            return verify_code;
        }

        public void setVerify_code(int verify_code) {
            this.verify_code = verify_code;
        }
    }
}
