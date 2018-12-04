package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/3
 * on 15:33
 */
public class SaveUserCar {

    /**
     * code : 0
     * msg : success
     * jdata : {"car_frameno":"3213231232131","car_no":"dsadss"}
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
         * car_frameno : 3213231232131
         * car_no : dsadss
         */

        private String car_frameno;
        private String car_no;

        public String getCar_frameno() {
            return car_frameno;
        }

        public void setCar_frameno(String car_frameno) {
            this.car_frameno = car_frameno;
        }

        public String getCar_no() {
            return car_no;
        }

        public void setCar_no(String car_no) {
            this.car_no = car_no;
        }
    }
}
