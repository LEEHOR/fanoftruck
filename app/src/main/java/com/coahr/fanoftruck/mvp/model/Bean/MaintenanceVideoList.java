package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/22
 * on 17:18
 */
public class MaintenanceVideoList {

    /**
     * code : 0
     * msg : success
     * jdata : [{"id":"5","video_name":"112","video_type":"2","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"51","video_dz_num":"21","video_publish_user_id":"1170","video_state":"2","add_time":"1542872200","update_time":"1543286370","user_info":null,"discuss_num":"0"},{"id":"7","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870129","update_time":"1543217562","user_info":null,"discuss_num":"0"},{"id":"8","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870129","update_time":"1543217545","user_info":null,"discuss_num":"0"},{"id":"9","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870129","update_time":"1543217545","user_info":null,"discuss_num":"0"},{"id":"10","video_name":"测试2","video_type":"3","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870125","update_time":"1543286377","user_info":null,"discuss_num":"0"},{"id":"11","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870124","update_time":"1543217545","user_info":null,"discuss_num":"0"},{"id":"12","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870123","update_time":"1543217545","user_info":null,"discuss_num":"0"},{"id":"13","video_name":"测试2","video_type":"1","video_link":"/Data/Video/ValidateUtils.mp4","video_view_num":"5","video_dz_num":"3","video_publish_user_id":"1170","video_state":"2","add_time":"1542870122","update_time":"1543217545","user_info":null,"discuss_num":"0"}]
     */

    private int code;
    private String msg;
    private List<JdataBean> jdata;

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
         * id : 5
         * video_name : 112
         * video_type : 2
         * video_link : /Data/Video/ValidateUtils.mp4
         * video_view_num : 51
         * video_dz_num : 21
         * video_publish_user_id : 1170
         * video_state : 2
         * add_time : 1542872200
         * update_time : 1543286370
         * user_info : null
         * discuss_num : 0
         */

        private String id;
        private String video_name;
        private String video_type;
        private String video_link;
        private String video_view_num;
        private String video_dz_num;
        private String video_publish_user_id;
        private String video_state;
        private String add_time;
        private String update_time;
        private String user_info;
        private String discuss_num;
        private String video_cover;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideo_name() {
            return video_name;
        }

        public void setVideo_name(String video_name) {
            this.video_name = video_name;
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

        public String getUser_info() {
            return user_info;
        }

        public void setUser_info(String user_info) {
            this.user_info = user_info;
        }

        public String getDiscuss_num() {
            return discuss_num;
        }

        public void setDiscuss_num(String discuss_num) {
            this.discuss_num = discuss_num;
        }

        public String getVideo_cover() {
            return video_cover;
        }

        public void setVideo_cover(String video_cover) {
            this.video_cover = video_cover;
        }
    }
}
