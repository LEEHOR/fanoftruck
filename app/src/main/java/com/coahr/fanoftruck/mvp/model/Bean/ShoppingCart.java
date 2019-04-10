package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 13:14
 */
public class ShoppingCart {

    /**
     * code : 0
     * msg : success
     * jdata : {"commodity":[{"c_id":"162","c_name":"前进全钢卡客车轮胎GA282A","c_price":"1289.99","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg","c_num":"11"}]}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "ShoppingCart{" +
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
        private List<CommodityBean> commodity;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "commodity=" + commodity +
                    '}';
        }

        public List<CommodityBean> getCommodity() {
            return commodity;
        }

        public void setCommodity(List<CommodityBean> commodity) {
            this.commodity = commodity;
        }

        public static class CommodityBean {
            /**
             * c_id : 162
             * c_name : 前进全钢卡客车轮胎GA282A
             * c_price : 1289.99
             * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg
             * c_num : 11
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
