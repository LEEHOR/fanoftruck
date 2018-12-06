package com.coahr.fanoftruck.mvp.model.Bean;

/**
 * Created by Leehor
 * on 2018/12/6
 * on 16:08
 */
public class Center_Initial_Data {

    /**
     * code : 0
     * msg : success
     * jdata : {"user":{"uid":"1203","wxid":null,"openid":null,"nickname":null,"phone":"18571512117","addtime":"1543558142","update":null,"password":"123456","status":"1","identity_flag":null,"licence_pic":null,"userheadimg":null,"points":"0","username":"leehor","email":"1622293788@qq.com","sex":"0","postal_address":null,"detail_address":null,"realname":null,"pic1":null,"pic2":null}}
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
        /**
         * user : {"uid":"1203","wxid":null,"openid":null,"nickname":null,"phone":"18571512117","addtime":"1543558142","update":null,"password":"123456","status":"1","identity_flag":null,"licence_pic":null,"userheadimg":null,"points":"0","username":"leehor","email":"1622293788@qq.com","sex":"0","postal_address":null,"detail_address":null,"realname":null,"pic1":null,"pic2":null}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * uid : 1203
             * wxid : null
             * openid : null
             * nickname : null
             * phone : 18571512117
             * addtime : 1543558142
             * update : null
             * password : 123456
             * status : 1
             * identity_flag : null
             * licence_pic : null
             * userheadimg : null
             * points : 0
             * username : leehor
             * email : 1622293788@qq.com
             * sex : 0
             * postal_address : null
             * detail_address : null
             * realname : null
             * pic1 : null
             * pic2 : null
             */

            private String uid;
            private String wxid;
            private String openid;
            private String nickname;
            private String phone;
            private String addtime;
            private String update;
            private String password;
            private String status;
            private String identity_flag;
            private String licence_pic;
            private String userheadimg;
            private String points;
            private String username;
            private String email;
            private String sex;
            private String postal_address;
            private String detail_address;
            private String realname;
            private String pic1;
            private String pic2;

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public String getWxid() {
                return wxid;
            }

            public void setWxid(String wxid) {
                this.wxid = wxid;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
                this.nickname = nickname;
            }

            public String getPhone() {
                return phone;
            }

            public void setPhone(String phone) {
                this.phone = phone;
            }

            public String getAddtime() {
                return addtime;
            }

            public void setAddtime(String addtime) {
                this.addtime = addtime;
            }

            public String getUpdate() {
                return update;
            }

            public void setUpdate(String update) {
                this.update = update;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getIdentity_flag() {
                return identity_flag;
            }

            public void setIdentity_flag(String identity_flag) {
                this.identity_flag = identity_flag;
            }

            public String getLicence_pic() {
                return licence_pic;
            }

            public void setLicence_pic(String licence_pic) {
                this.licence_pic = licence_pic;
            }

            public String getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(String userheadimg) {
                this.userheadimg = userheadimg;
            }

            public String getPoints() {
                return points;
            }

            public void setPoints(String points) {
                this.points = points;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getPostal_address() {
                return postal_address;
            }

            public void setPostal_address(String postal_address) {
                this.postal_address = postal_address;
            }

            public String getDetail_address() {
                return detail_address;
            }

            public void setDetail_address(String detail_address) {
                this.detail_address = detail_address;
            }

            public String getRealname() {
                return realname;
            }

            public void setRealname(String realname) {
                this.realname = realname;
            }

            public String getPic1() {
                return pic1;
            }

            public void setPic1(String pic1) {
                this.pic1 = pic1;
            }

            public String getPic2() {
                return pic2;
            }

            public void setPic2(String pic2) {
                this.pic2 = pic2;
            }
        }
    }
}
