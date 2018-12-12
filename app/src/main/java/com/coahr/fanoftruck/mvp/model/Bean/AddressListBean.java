package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

/**
 * Created by Leehor
 * on 2018/12/11
 * on 18:42
 */
public class AddressListBean {

    /**
     * code : 0
     * msg : success
     * jdata : {"address":[]}
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
        private List<AddressEntity> address;

        public List<AddressEntity> getAddress() {
            return address;
        }

        public void setAddress(List<AddressEntity> address) {
            this.address = address;
        }

        public static class AddressEntity {
            /**
             * id : 562
             * username : 引吭高歌
             * telephone : 15722620367
             * address : 天津 天津市 河东区共和国非常纯纯粹粹 v 哈哈哈
             * selected : 1
             */

            private String id;
            private String username;
            private String telephone;
            private String address;
            private String selected;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getTelephone() {
                return telephone;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public String getAddress() {
                return address;
            }

            public void setAddress(String address) {
                this.address = address;
            }

            public String getSelected() {
                return selected;
            }

            public void setSelected(String selected) {
                this.selected = selected;
            }
        }
    }
}
