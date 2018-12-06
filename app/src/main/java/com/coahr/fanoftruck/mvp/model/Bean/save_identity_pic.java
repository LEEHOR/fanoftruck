package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/6
 * on 18:51
 */
public class save_identity_pic {

    /**
     * code : 0
     * msg : success
     * jdata : {"jmsg":"保存成功"}
     */

    private int code;
    private String msg;
    private Save_Identity_Info.JdataBean jdata;

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

    public Save_Identity_Info.JdataBean getJdata() {
        return jdata;
    }

    public void setJdata(Save_Identity_Info.JdataBean jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
        /**
         * jmsg : 保存成功
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
