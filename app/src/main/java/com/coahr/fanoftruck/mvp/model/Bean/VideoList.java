package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 17:18
 */
public class VideoList {
    private String time;
    private String  tag1;
    private String tag2;
    private String tag3;
    private String tag4;

    public VideoList(String time, String tag1, String tag2, String tag3, String tag4) {
        this.time = time;
        this.tag1 = tag1;
        this.tag2 = tag2;
        this.tag3 = tag3;
        this.tag4 = tag4;
    }

    public VideoList() {
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public String getTag2() {
        return tag2;
    }

    public void setTag2(String tag2) {
        this.tag2 = tag2;
    }

    public String getTag3() {
        return tag3;
    }

    public void setTag3(String tag3) {
        this.tag3 = tag3;
    }

    public String getTag4() {
        return tag4;
    }

    public void setTag4(String tag4) {
        this.tag4 = tag4;
    }
}
