package com.coahr.fanoftruck.mvp.model.Bean;

public class BindWXData {
    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "BindWXData{" +
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
        private String bdwx_msg;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "bdwx_msg='" + bdwx_msg + '\'' +
                    '}';
        }

        public String getBdwx_msg() {
            return bdwx_msg;
        }

        public void setBdwx_msg(String bdwx_msg) {
            this.bdwx_msg = bdwx_msg;
        }
    }
}
