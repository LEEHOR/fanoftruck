package com.coahr.fanoftruck.mvp.model.Bean;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/5
 * on 15:22
 */
public class Business_car {

    /**
     * code : 0
     * msg : success
     * jdata : {"cars":[{"c_id":"155","c_num":"000010","c_name":"道达尔红运7400机油润滑油15W40 CI-4 4L","c_brand":"63","c_sort":"82","c_status":"1","c_stock":"0","c_thumbnail":"/Data/Commodity/Thumbnail/p_icon_1539756010_109777.jpeg","c_price":"119.00","c_label":"4L,柴机油,CI-4","c_addtime":"1539756215","c_hot":"0","c_price_old":"0.00","c_sold_fake":"0","c_sold_real":"1","s_id":null,"is_preferential":"0","preferential_time":"1539756215","hot_time":"1539756215","c_purchase_price":"95.00","store_c_num":null,"c_description":"<p style=\"text-align: center\"><br/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756196326998.png\" style=\"\" title=\"1539756196326998.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756195124589.png\" style=\"\" title=\"1539756195124589.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756196590783.png\" style=\"\" title=\"1539756196590783.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756195412674.png\" style=\"\" title=\"1539756195412674.png\"/><\/p><p><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756248144328.png\" title=\"1539756248144328.png\" alt=\"道达尔粘度选择.png\"/><\/p>","top_time":null,"is_top":null,"guid":"11A2D78D-8944-F223-68BD-37ADEE0D0F4A","changyong":"0","is_recommend":"1"},{"c_id":"154","c_num":"000009","c_name":"道达尔红运6600机油润滑油20W50 CH-4 18L","c_brand":"63","c_sort":"82","c_status":"1","c_stock":"0","c_thumbnail":"/Data/Commodity/Thumbnail/p_icon_1539747674_736602.jpeg","c_price":"399.00","c_label":"CH-4,柴机油,18L","c_addtime":"1539747824","c_hot":"0","c_price_old":"0.00","c_sold_fake":"0","c_sold_real":"1","s_id":null,"is_preferential":"0","preferential_time":"1539747824","hot_time":"1539747824","c_purchase_price":"310.00","store_c_num":null,"c_description":"<p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747811532087.png\" style=\"\" title=\"1539747811532087.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747813140003.png\" style=\"\" title=\"1539747813140003.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747810140378.png\" style=\"\" title=\"1539747810140378.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747812728097.png\" style=\"\" title=\"1539747812728097.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747812617721.png\" style=\"\" title=\"1539747812617721.png\"/><\/p><p style=\"text-align: center\"><img src=\"https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539747814401830.png\" style=\"\" title=\"1539747814401830.png\"/><\/p><p><br/><\/p>","top_time":null,"is_top":null,"guid":"E3DEA78C-5BDD-6FDE-E868-3B4492456DCC","changyong":"0","is_recommend":"1"}]}
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
        private List<CarsBean> cars;

        public List<CarsBean> getCars() {
            return cars;
        }

        public void setCars(List<CarsBean> cars) {
            this.cars = cars;
        }

        public static class CarsBean implements IPickerViewData {
            /**
             * c_id : 155
             * c_num : 000010
             * c_name : 道达尔红运7400机油润滑油15W40 CI-4 4L
             * c_brand : 63
             * c_sort : 82
             * c_status : 1
             * c_stock : 0
             * c_thumbnail : /Data/Commodity/Thumbnail/p_icon_1539756010_109777.jpeg
             * c_price : 119.00
             * c_label : 4L,柴机油,CI-4
             * c_addtime : 1539756215
             * c_hot : 0
             * c_price_old : 0.00
             * c_sold_fake : 0
             * c_sold_real : 1
             * s_id : null
             * is_preferential : 0
             * preferential_time : 1539756215
             * hot_time : 1539756215
             * c_purchase_price : 95.00
             * store_c_num : null
             * c_description : <p style="text-align: center"><br/></p><p style="text-align: center"><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756196326998.png" style="" title="1539756196326998.png"/></p><p style="text-align: center"><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756195124589.png" style="" title="1539756195124589.png"/></p><p style="text-align: center"><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756196590783.png" style="" title="1539756196590783.png"/></p><p style="text-align: center"><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756195412674.png" style="" title="1539756195412674.png"/></p><p><img src="https://shop.ksuper.coahr.com//ueditor/php/upload/image/20181017/1539756248144328.png" title="1539756248144328.png" alt="道达尔粘度选择.png"/></p>
             * top_time : null
             * is_top : null
             * guid : 11A2D78D-8944-F223-68BD-37ADEE0D0F4A
             * changyong : 0
             * is_recommend : 1
             */

            private String c_id;
            private String c_num;
            private String c_name;
            private String c_brand;
            private String c_sort;
            private String c_status;
            private String c_stock;
            private String c_thumbnail;
            private String c_price;
            private String c_label;
            private String c_addtime;
            private String c_hot;
            private String c_price_old;
            private String c_sold_fake;
            private String c_sold_real;
            private Object s_id;
            private String is_preferential;
            private String preferential_time;
            private String hot_time;
            private String c_purchase_price;
            private Object store_c_num;
            private String c_description;
            private Object top_time;
            private Object is_top;
            private String guid;
            private String changyong;
            private String is_recommend;

            public String getC_id() {
                return c_id;
            }

            public void setC_id(String c_id) {
                this.c_id = c_id;
            }

            public String getC_num() {
                return c_num;
            }

            public void setC_num(String c_num) {
                this.c_num = c_num;
            }

            public String getC_name() {
                return c_name;
            }

            public void setC_name(String c_name) {
                this.c_name = c_name;
            }

            public String getC_brand() {
                return c_brand;
            }

            public void setC_brand(String c_brand) {
                this.c_brand = c_brand;
            }

            public String getC_sort() {
                return c_sort;
            }

            public void setC_sort(String c_sort) {
                this.c_sort = c_sort;
            }

            public String getC_status() {
                return c_status;
            }

            public void setC_status(String c_status) {
                this.c_status = c_status;
            }

            public String getC_stock() {
                return c_stock;
            }

            public void setC_stock(String c_stock) {
                this.c_stock = c_stock;
            }

            public String getC_thumbnail() {
                return c_thumbnail;
            }

            public void setC_thumbnail(String c_thumbnail) {
                this.c_thumbnail = c_thumbnail;
            }

            public String getC_price() {
                return c_price;
            }

            public void setC_price(String c_price) {
                this.c_price = c_price;
            }

            public String getC_label() {
                return c_label;
            }

            public void setC_label(String c_label) {
                this.c_label = c_label;
            }

            public String getC_addtime() {
                return c_addtime;
            }

            public void setC_addtime(String c_addtime) {
                this.c_addtime = c_addtime;
            }

            public String getC_hot() {
                return c_hot;
            }

            public void setC_hot(String c_hot) {
                this.c_hot = c_hot;
            }

            public String getC_price_old() {
                return c_price_old;
            }

            public void setC_price_old(String c_price_old) {
                this.c_price_old = c_price_old;
            }

            public String getC_sold_fake() {
                return c_sold_fake;
            }

            public void setC_sold_fake(String c_sold_fake) {
                this.c_sold_fake = c_sold_fake;
            }

            public String getC_sold_real() {
                return c_sold_real;
            }

            public void setC_sold_real(String c_sold_real) {
                this.c_sold_real = c_sold_real;
            }

            public Object getS_id() {
                return s_id;
            }

            public void setS_id(Object s_id) {
                this.s_id = s_id;
            }

            public String getIs_preferential() {
                return is_preferential;
            }

            public void setIs_preferential(String is_preferential) {
                this.is_preferential = is_preferential;
            }

            public String getPreferential_time() {
                return preferential_time;
            }

            public void setPreferential_time(String preferential_time) {
                this.preferential_time = preferential_time;
            }

            public String getHot_time() {
                return hot_time;
            }

            public void setHot_time(String hot_time) {
                this.hot_time = hot_time;
            }

            public String getC_purchase_price() {
                return c_purchase_price;
            }

            public void setC_purchase_price(String c_purchase_price) {
                this.c_purchase_price = c_purchase_price;
            }

            public Object getStore_c_num() {
                return store_c_num;
            }

            public void setStore_c_num(Object store_c_num) {
                this.store_c_num = store_c_num;
            }

            public String getC_description() {
                return c_description;
            }

            public void setC_description(String c_description) {
                this.c_description = c_description;
            }

            public Object getTop_time() {
                return top_time;
            }

            public void setTop_time(Object top_time) {
                this.top_time = top_time;
            }

            public Object getIs_top() {
                return is_top;
            }

            public void setIs_top(Object is_top) {
                this.is_top = is_top;
            }

            public String getGuid() {
                return guid;
            }

            public void setGuid(String guid) {
                this.guid = guid;
            }

            public String getChangyong() {
                return changyong;
            }

            public void setChangyong(String changyong) {
                this.changyong = changyong;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
            }

            @Override
            public String getPickerViewText() {
                return this.c_name;
            }
        }
    }
}
