package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/4
 * on 9:13
 * 预约和保养页面默认加载
 */
public class AppointmentDefaultBean {

    /**
     * code : 0
     * msg : success
     * jdata : {"telephone":"18571512117","mycar":{"car_id":"676","u_id":"1203","car_frameno":"123456786645666","car_no":"5455455454","default_check":"1"}}
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
         * telephone : 18571512117
         * mycar : {"car_id":"676","u_id":"1203","car_frameno":"123456786645666","car_no":"5455455454","default_check":"1"}
         */

        private String telephone;
        private MycarBean mycar;

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public MycarBean getMycar() {
            return mycar;
        }

        public void setMycar(MycarBean mycar) {
            this.mycar = mycar;
        }

        public static class MycarBean {
            /**
             * car_id : 676
             * u_id : 1203
             * car_frameno : 123456786645666
             * car_no : 5455455454
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
