package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/11/29
 * on 19:29
 */
public class LoginBean {

    /**
     * code : 0
     * msg : 登录成功！
     * jdata : {"token":"685e97457c74459301e9e4b27c1cab53","uid":"1203","telephone":"18571512117","nickname":"","headImg":""}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "LoginBean{" +
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
         * token : 685e97457c74459301e9e4b27c1cab53
         * uid : 1203
         * telephone : 18571512117
         * nickname :
         * headImg :
         */

        private String token;
        private String uid;
        private String telephone;
        private String nickname;
        private String headImg;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "token='" + token + '\'' +
                    ", uid='" + uid + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", nickname='" + nickname + '\'' +
                    ", headImg='" + headImg + '\'' +
                    '}';
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public String getUid() {
            return uid;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getTelephone() {
            return telephone;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public String getHeadImg() {
            return headImg;
        }

        public void setHeadImg(String headImg) {
            this.headImg = headImg;
        }
    }
}
