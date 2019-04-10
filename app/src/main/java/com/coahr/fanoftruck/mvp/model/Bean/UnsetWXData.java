package com.coahr.fanoftruck.mvp.model.Bean;

public class UnsetWXData {
    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "UnsetWXData{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", jdata=" + jdata +
                '}';
    }

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
        private String jmsg;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "jmsg='" + jmsg + '\'' +
                    '}';
        }

        public String getJmsg() {
            return jmsg;
        }

        public void setJmsg(String jmsg) {
            this.jmsg = jmsg;
        }
    }
}
