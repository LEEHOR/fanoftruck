package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/3
 * on 18:11
 * 维修保养或预约保养传值
 */
public class EventBusUserCar {
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

    public EventBusUserCar() {
    }

    public EventBusUserCar(String car_frameno, String car_no) {
        this.car_frameno = car_frameno;
        this.car_no = car_no;
    }
}
