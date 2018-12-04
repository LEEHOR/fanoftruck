package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/3
 * on 17:16
 */
public class CarListBean {

    /**
     * code : 0
     * msg : success
     * jdata : {"mycar":[{"car_id":"669","u_id":"1203","car_frameno":"3213231232131","car_no":"dsadss","default_check":"1"}]}
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
        private List<MycarBean> mycar;

        public List<MycarBean> getMycar() {
            return mycar;
        }

        public void setMycar(List<MycarBean> mycar) {
            this.mycar = mycar;
        }

        public static class MycarBean {
            /**
             * car_id : 669
             * u_id : 1203
             * car_frameno : 3213231232131
             * car_no : dsadss
             * default_check : 1
             */

            private String car_id;
            private String u_id;
            private String car_frameno;
            private String car_no;
            private String default_check;

            public String getCar_id() {
                return car_id;
            }

            public void setCar_id(String car_id) {
                this.car_id = car_id;
            }

            public String getU_id() {
                return u_id;
            }

            public void setU_id(String u_id) {
                this.u_id = u_id;
            }

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

            public String getDefault_check() {
                return default_check;
            }

            public void setDefault_check(String default_check) {
                this.default_check = default_check;
            }
        }
    }
}
