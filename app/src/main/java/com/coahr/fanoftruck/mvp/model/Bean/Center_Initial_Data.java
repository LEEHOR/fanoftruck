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
     * jdata : {"user":{"uid":"1203","wxid":null,"openid":null,"nickname":"abc123","phone":"18571512117","addtime":"1543558142","update":null,"password":"123456","status":"1","identity_flag":null,"licence_pic":null,"userheadimg":null,"points":"0","username":"leehor","email":"1622293788@qq.com","sex":"1","postal_address":"北京市北京市东城区","detail_address":"吉里吉里","realname":"李浩","pic1":"http://app.cvfans.net/Data/Identity/20181210/F1544411061.jpg","pic2":"http://app.cvfans.net/Data/Identity/20181210/T1544411061.jpg","accesstime":null}}
     */

    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "Center_Initial_Data{" +
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
         * user : {"uid":"1203","wxid":null,"openid":null,"nickname":"abc123","phone":"18571512117","addtime":"1543558142","update":null,"password":"123456","status":"1","identity_flag":null,"licence_pic":null,"userheadimg":null,"points":"0","username":"leehor","email":"1622293788@qq.com","sex":"1","postal_address":"北京市北京市东城区","detail_address":"吉里吉里","realname":"李浩","pic1":"http://app.cvfans.net/Data/Identity/20181210/F1544411061.jpg","pic2":"http://app.cvfans.net/Data/Identity/20181210/T1544411061.jpg","accesstime":null}
         */

        private UserBean user;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "user=" + user +
                    '}';
        }

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
             * nickname : abc123
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
             * sex : 1
             * postal_address : 北京市北京市东城区
             * detail_address : 吉里吉里
             * realname : 李浩
             * pic1 : http://app.cvfans.net/Data/Identity/20181210/F1544411061.jpg
             * pic2 : http://app.cvfans.net/Data/Identity/20181210/T1544411061.jpg
             * accesstime : null
             */

            private String uid;
            private Object wxid;
            private Object openid;
            private String nickname;
            private String phone;
            private String addtime;
            private Object update;
            private String password;
            private String status;
            private Object identity_flag;
            private Object licence_pic;
            private Object userheadimg;
            private String points;
            private String username;
            private String email;
            private String sex;
            private String postal_address;
            private String detail_address;
            private String realname;
            private String pic1;
            private String pic2;
            private Object accesstime;

            @Override
            public String toString() {
                return "UserBean{" +
                        "uid='" + uid + '\'' +
                        ", wxid=" + wxid +
                        ", openid=" + openid +
                        ", nickname='" + nickname + '\'' +
                        ", phone='" + phone + '\'' +
                        ", addtime='" + addtime + '\'' +
                        ", update=" + update +
                        ", password='" + password + '\'' +
                        ", status='" + status + '\'' +
                        ", identity_flag=" + identity_flag +
                        ", licence_pic=" + licence_pic +
                        ", userheadimg=" + userheadimg +
                        ", points='" + points + '\'' +
                        ", username='" + username + '\'' +
                        ", email='" + email + '\'' +
                        ", sex='" + sex + '\'' +
                        ", postal_address='" + postal_address + '\'' +
                        ", detail_address='" + detail_address + '\'' +
                        ", realname='" + realname + '\'' +
                        ", pic1='" + pic1 + '\'' +
                        ", pic2='" + pic2 + '\'' +
                        ", accesstime=" + accesstime +
                        '}';
            }

            public String getUid() {
                return uid;
            }

            public void setUid(String uid) {
                this.uid = uid;
            }

            public Object getWxid() {
                return wxid;
            }

            public void setWxid(Object wxid) {
                this.wxid = wxid;
            }

            public Object getOpenid() {
                return openid;
            }

            public void setOpenid(Object openid) {
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

            public Object getUpdate() {
                return update;
            }

            public void setUpdate(Object update) {
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

            public Object getIdentity_flag() {
                return identity_flag;
            }

            public void setIdentity_flag(Object identity_flag) {
                this.identity_flag = identity_flag;
            }

            public Object getLicence_pic() {
                return licence_pic;
            }

            public void setLicence_pic(Object licence_pic) {
                this.licence_pic = licence_pic;
            }

            public Object getUserheadimg() {
                return userheadimg;
            }

            public void setUserheadimg(Object userheadimg) {
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

            public Object getAccesstime() {
                return accesstime;
            }

            public void setAccesstime(Object accesstime) {
                this.accesstime = accesstime;
            }
        }
    }
}