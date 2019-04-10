package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/12
 * on 17:08
 */
public class Confirm_order {

    /**
     * code : 0
     * msg : success
     * jdata : {"userinfo":{"ua_id":"1001","username":"Ewqeqweqwe","telephone":"qwewqeqweqwe","address":"Ewqeqweqw"},"commodity":[{"c_id":"155","c_name":"道达尔红运7400机油润滑油15W40 CI-4 4L","c_price":"119.00","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539756010_109777.jpeg","c_num":"2"},{"c_id":"158","c_name":"道达尔红运7900机油润滑油15W40 CJ-4 4L","c_price":"139.00","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539758445_286492.jpeg","c_num":"5"},{"c_id":"162","c_name":"前进全钢卡客车轮胎GA282A","c_price":"1289.99","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg","c_num":"3"}],"fee":0,"total":4802.97}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "Confirm_order{" +
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
        /**
         * userinfo : {"ua_id":"1001","username":"Ewqeqweqwe","telephone":"qwewqeqweqwe","address":"Ewqeqweqw"}
         * commodity : [{"c_id":"155","c_name":"道达尔红运7400机油润滑油15W40 CI-4 4L","c_price":"119.00","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539756010_109777.jpeg","c_num":"2"},{"c_id":"158","c_name":"道达尔红运7900机油润滑油15W40 CJ-4 4L","c_price":"139.00","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539758445_286492.jpeg","c_num":"5"},{"c_id":"162","c_name":"前进全钢卡客车轮胎GA282A","c_price":"1289.99","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg","c_num":"3"}]
         * fee : 0
         * total : 4802.97
         */

        private UserinfoBean userinfo;
        private int fee;
        private double total;
        private List<CommodityBean> commodity;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "userinfo=" + userinfo +
                    ", fee=" + fee +
                    ", total=" + total +
                    ", commodity=" + commodity +
                    '}';
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public int getFee() {
            return fee;
        }

        public void setFee(int fee) {
            this.fee = fee;
        }

        public double getTotal() {
            return total;
        }

        public void setTotal(double total) {
            this.total = total;
        }

        public List<CommodityBean> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityBean> commodity) {
            this.commodity = commodity;
        }

        public static class UserinfoBean {
            /**
             * ua_id : 1001
             * username : Ewqeqweqwe
             * telephone : qwewqeqweqwe
             * address : Ewqeqweqw
             */

            private String ua_id;
            private String username;
            private String telephone;
            private String address;

            @Override
            public String toString() {
                return "UserinfoBean{" +
                        "ua_id='" + ua_id + '\'' +
                        ", username='" + username + '\'' +
                        ", telephone='" + telephone + '\'' +
                        ", address='" + address + '\'' +
                        '}';
            }

            public String getUa_id() {
                return ua_id;
            }

            public void setUa_id(String ua_id) {
                this.ua_id = ua_id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }
        }

        public static class CommodityBean {
            /**
             * c_id : 155
             * c_name : 道达尔红运7400机油润滑油15W40 CI-4 4L
             * c_price : 119.00
             * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539756010_109777.jpeg
             * c_num : 2
             */

            private String c_id;
            private String c_name;
            private String c_price;
            private String c_thumbnail;
            private String c_num;

            @Override
            public String toString() {
                return "CommodityBean{" +
                        "c_id='" + c_id + '\'' +
                        ", c_name='" + c_name + '\'' +
                        ", c_price='" + c_price + '\'' +
                        ", c_thumbnail='" + c_thumbnail + '\'' +
                        ", c_num='" + c_num + '\'' +
                        '}';
            }

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }

            public String getC_num() {
                return c_num;
            }

            public void setC_num(String c_num) {
                this.c_num = c_num;
            }
        }
    }
}
