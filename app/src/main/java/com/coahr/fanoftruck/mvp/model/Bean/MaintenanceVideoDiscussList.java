package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/11/27
 * on 23:58
 * 视频评论
 */
public class MaintenanceVideoDiscussList {

    /**
     * code : 0
     * msg : success
     * jdata : [{"id":"3","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"18","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"17","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"16","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"15","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"14","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"13","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"12","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"11","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"10","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"9","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"8","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"7","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"6","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"5","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null},{"id":"19","video_id":"1","discuss_user_id":"1160","discuss_content":"eees4","add_time":"1542872100","update_time":"1543216369","user_info":null}]
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
         * id : 3
         * video_id : 1
         * discuss_user_id : 1160
         * discuss_content : eees4
         * add_time : 1542872100
         * update_time : 1543216369
         * user_info : null
         */

        private String id;
        private String video_id;
        private String discuss_user_id;
        private String discuss_content;
        private String add_time;
        private String update_time;
        private String user_info;
        private String userHeadImg;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getDiscuss_user_id() {
            return discuss_user_id;
        }

        public void setDiscuss_user_id(String discuss_user_id) {
            this.discuss_user_id = discuss_user_id;
        }

        public String getDiscuss_content() {
            return discuss_content;
        }

        public void setDiscuss_content(String discuss_content) {
            this.discuss_content = discuss_content;
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

        public String getUserHeadImg() {
            return userHeadImg;
        }

        public void setUserHeadImg(String userHeadImg) {
            this.userHeadImg = userHeadImg;
        }
    }
}
