package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by lizhiguo
 * on 2019/4/16
 */
public class TruckVideoListData {

    /**
     * code : 0
     * msg : 请求数据成功
     * jdata : [{"id":"95","video_describe":"你","video_cover":"/Data/Video_cover/29618_1555378818_video_cover.png","video_type":"4","video_link":"/Data/Video/47721_1555378818_video.mp4","video_view_num":"0","video_dz_num":"0","video_publish_user_id":"1203","video_state":"2","add_time":"1555378818","update_time":"1555379130"},{"id":"94","video_describe":"体检","video_cover":"/Data/Video_cover/18283_1555378423_video_cover.png","video_type":"4","video_link":"/Data/Video/80606_1555378423_video.mp4","video_view_num":"0","video_dz_num":"0","video_publish_user_id":"1203","video_state":"2","add_time":"1555378423","update_time":"1555379126"}]
     */

    private int code;
    private String msg;
    private List<JdataBean> jdata;

    @Override
    public String toString() {
        return "TruckVideoListData{" +
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

    public List<JdataBean> getJdata() {
        return jdata;
    }

    public void setJdata(List<JdataBean> jdata) {
        this.jdata = jdata;
    }

    public static class JdataBean {
        /**
         * id : 95
         * video_describe : 你
         * video_cover : /Data/Video_cover/29618_1555378818_video_cover.png
         * video_type : 4
         * video_link : /Data/Video/47721_1555378818_video.mp4
         * video_view_num : 0
         * video_dz_num : 0
         * video_publish_user_id : 1203
         * video_state : 2
         * add_time : 1555378818
         * update_time : 1555379130
         */

        private String id;
        private String video_describe;
        private String video_cover;
        private String video_type;
        private String video_link;
        private String video_view_num;
        private String video_dz_num;
        private String video_publish_user_id;
        private String video_state;
        private String add_time;
        private String update_time;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "id='" + id + '\'' +
                    ", video_describe='" + video_describe + '\'' +
                    ", video_cover='" + video_cover + '\'' +
                    ", video_type='" + video_type + '\'' +
                    ", video_link='" + video_link + '\'' +
                    ", video_view_num='" + video_view_num + '\'' +
                    ", video_dz_num='" + video_dz_num + '\'' +
                    ", video_publish_user_id='" + video_publish_user_id + '\'' +
                    ", video_state='" + video_state + '\'' +
                    ", add_time='" + add_time + '\'' +
                    ", update_time='" + update_time + '\'' +
                    '}';
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideo_describe() {
            return video_describe;
        }

        public void setVideo_describe(String video_describe) {
            this.video_describe = video_describe;
        }

        public String getVideo_cover() {
            return video_cover;
        }

        public void setVideo_cover(String video_cover) {
            this.video_cover = video_cover;
        }

        public String getVideo_type() {
            return video_type;
        }

        public void setVideo_type(String video_type) {
            this.video_type = video_type;
        }

        public String getVideo_link() {
            return video_link;
        }

        public void setVideo_link(String video_link) {
            this.video_link = video_link;
        }

        public String getVideo_view_num() {
            return video_view_num;
        }

        public void setVideo_view_num(String video_view_num) {
            this.video_view_num = video_view_num;
        }

        public String getVideo_dz_num() {
            return video_dz_num;
        }

        public void setVideo_dz_num(String video_dz_num) {
            this.video_dz_num = video_dz_num;
        }

        public String getVideo_publish_user_id() {
            return video_publish_user_id;
        }

        public void setVideo_publish_user_id(String video_publish_user_id) {
            this.video_publish_user_id = video_publish_user_id;
        }

        public String getVideo_state() {
            return video_state;
        }

        public void setVideo_state(String video_state) {
            this.video_state = video_state;
        }

        public String getAdd_time() {
            return add_time;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getUpdate_time() {
            return update_time;
        }

        public void setUpdate_time(String update_time) {
            this.update_time = update_time;
        }
    }
}
