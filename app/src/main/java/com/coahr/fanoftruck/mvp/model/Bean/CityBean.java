package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/20
 * on 19:11
 */
public class CityBean {
    private String city_name;
    private String city_code;

    public String getCity_name() {
        return city_name;
    }

    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    public String getCity_code() {
        return city_code;
    }

    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }

    public CityBean(String city_name, String city_code) {
        this.city_name = city_name;
        this.city_code = city_code;
    }

    public CityBean() {

    }
    public static List<CityBean> getSortEntity(List<CityBean> list){
        Collections.sort(list, new Comparator<CityBean>() {
            @Override
            public int compare(CityBean o1, CityBean o2) {
                return o1.getCity_code().compareTo(o2.getCity_code());
            }
        });
        return  list;
    }
}
