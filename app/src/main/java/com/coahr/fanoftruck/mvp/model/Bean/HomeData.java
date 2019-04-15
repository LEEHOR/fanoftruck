package com.coahr.fanoftruck.mvp.model.Bean;

import java.util.List;

public class HomeData {
    private int code;
    private String msg;
    private JdataBean jdata;

    @Override
    public String toString() {
        return "HomeData{" +
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
         * headImages : [{"id":"15","adv_title":"利物浦","adv_img":"http://app.cvfans.net/Data/Advertise/20190415/5cb3eb5a03872.jpg","adv_url":""},{"id":"14","adv_title":"拜仁","adv_img":"http://app.cvfans.net/Data/Advertise/20190415/5cb3eb25ab433.jpg","adv_url":""},{"id":"13","adv_title":"巴萨","adv_img":"http://app.cvfans.net/Data/Advertise/20190415/5cb3eb4838613.jpg","adv_url":""}]
         * videos : [{"id":"90","video_describe":"1","video_cover":"/Data/Video_cover/25682_1555318489_video_cover.png","video_type":"1","video_link":"/Data/Video/19341_1555318489_video.mp4","video_view_num":"1","video_dz_num":"0","video_publish_user_id":"1203","video_state":"2","add_time":"1555318489","update_time":"1555318523"}]
         * allDatas : {"news":[{"n_id":"9","title":"卡速宝与道达尔润滑油及东风康明斯达成战略合作"}],"activity":[{"adv_img":"http://app.cvfans.net"}],"choose":[{"c_id":"159","c_name":"道达尔红运7900机油润滑油15W40 CJ-4 18L","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg","c_price":"468.99","c_price_old":"469.00"},{"c_id":"162","c_name":"前进全钢卡客车轮胎GA282A","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg","c_price":"0.01","c_price_old":"1289.99"},{"c_id":"161","c_name":"前进全钢卡客车轮胎GA210A","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539923726_913537.jpeg","c_price":"0.01","c_price_old":"1349.99"},{"c_id":"160","c_name":"弗列加LF9009机油滤清器","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","c_price":"0.01","c_price_old":"124.99"}],"goodsType1":[{"c_id":"162","c_name":"前进全钢卡客车轮胎GA282A","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg","c_price":"0.01","c_price_old":"1289.99"},{"c_id":"161","c_name":"前进全钢卡客车轮胎GA210A","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539923726_913537.jpeg","c_price":"0.01","c_price_old":"1349.99"}],"goodsType2":[{"c_id":"150","c_name":"道达尔Quartz 7000汽机油润滑油SN 5W40 4L","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539743910_150097.jpeg","c_price":"0.01","c_price_old":"259.00"},{"c_id":"146","c_name":"道达尔Quartz 5000汽机油润滑油SN5W30 1L","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539680727_889340.jpeg","c_price":"0.01","c_price_old":"69.00"},{"c_id":"145","c_name":"道达尔Quartz 5000汽机油润滑油SN 5W30 4L","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539678178_750363.jpeg","c_price":"0.01","c_price_old":"199.00"}],"goodsType3":[{"c_id":"160","c_name":"弗列加LF9009机油滤清器","c_thumbnail":"http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg","c_price":"0.01","c_price_old":"124.99"}]}
         */

        private AllDatasBean allDatas;
        private List<HeadImagesBean> headImages;
        private List<VideosBean> videos;

        @Override
        public String toString() {
            return "JdataBean{" +
                    "allDatas=" + allDatas +
                    ", headImages=" + headImages +
                    ", videos=" + videos +
                    '}';
        }

        public AllDatasBean getAllDatas() {
            return allDatas;
        }

        public void setAllDatas(AllDatasBean allDatas) {
            this.allDatas = allDatas;
        }

        public List<HeadImagesBean> getHeadImages() {
            return headImages;
        }

        public void setHeadImages(List<HeadImagesBean> headImages) {
            this.headImages = headImages;
        }

        public List<VideosBean> getVideos() {
            return videos;
        }

        public void setVideos(List<VideosBean> videos) {
            this.videos = videos;
        }

        public static class AllDatasBean {
            private List<NewsBean> news;
            private List<ActivityBean> activity;
            private List<ChooseBean> choose;
            private List<GoodsType1Bean> goodsType1;
            private List<GoodsType2Bean> goodsType2;
            private List<GoodsType3Bean> goodsType3;

            @Override
            public String toString() {
                return "AllDatasBean{" +
                        "news=" + news +
                        ", activity=" + activity +
                        ", choose=" + choose +
                        ", goodsType1=" + goodsType1 +
                        ", goodsType2=" + goodsType2 +
                        ", goodsType3=" + goodsType3 +
                        '}';
            }

            public List<NewsBean> getNews() {
                return news;
            }

            public void setNews(List<NewsBean> news) {
                this.news = news;
            }

            public List<ActivityBean> getActivity() {
                return activity;
            }

            public void setActivity(List<ActivityBean> activity) {
                this.activity = activity;
            }

            public List<ChooseBean> getChoose() {
                return choose;
            }

            public void setChoose(List<ChooseBean> choose) {
                this.choose = choose;
            }

            public List<GoodsType1Bean> getGoodsType1() {
                return goodsType1;
            }

            public void setGoodsType1(List<GoodsType1Bean> goodsType1) {
                this.goodsType1 = goodsType1;
            }

            public List<GoodsType2Bean> getGoodsType2() {
                return goodsType2;
            }

            public void setGoodsType2(List<GoodsType2Bean> goodsType2) {
                this.goodsType2 = goodsType2;
            }

            public List<GoodsType3Bean> getGoodsType3() {
                return goodsType3;
            }

            public void setGoodsType3(List<GoodsType3Bean> goodsType3) {
                this.goodsType3 = goodsType3;
            }

            public static class NewsBean {
                /**
                 * n_id : 9
                 * title : 卡速宝与道达尔润滑油及东风康明斯达成战略合作
                 */

                private String n_id;
                private String title;

                @Override
                public String toString() {
                    return "NewsBean{" +
                            "n_id='" + n_id + '\'' +
                            ", title='" + title + '\'' +
                            '}';
                }

                public String getN_id() {
                    return n_id;
                }

                public void setN_id(String n_id) {
                    this.n_id = n_id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }
            }

            public static class ActivityBean {
                /**
                 * adv_img : http://app.cvfans.net
                 */

                private String adv_img;

                public String getAdv_img() {
                    return adv_img;
                }

                public void setAdv_img(String adv_img) {
                    this.adv_img = adv_img;
                }
            }

            public static class ChooseBean {
                /**
                 * c_id : 159
                 * c_name : 道达尔红运7900机油润滑油15W40 CJ-4 18L
                 * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539761057_423245.jpeg
                 * c_price : 468.99
                 * c_price_old : 469.00
                 */

                private String c_id;
                private String c_name;
                private String c_thumbnail;
                private String c_price;
                private String c_price_old;

                @Override
                public String toString() {
                    return "ChooseBean{" +
                            "c_id='" + c_id + '\'' +
                            ", c_name='" + c_name + '\'' +
                            ", c_thumbnail='" + c_thumbnail + '\'' +
                            ", c_price='" + c_price + '\'' +
                            ", c_price_old='" + c_price_old + '\'' +
                            '}';
                }

                public String getC_id() {
                    return c_id;
                }

                public void setC_id(String c_id) {
                    this.c_id = c_id;
                }

                public String getC_name() {
                    return c_name;
                }

                public void setC_name(String c_name) {
                    this.c_name = c_name;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }
            }

            public static class GoodsType1Bean {
                /**
                 * c_id : 162
                 * c_name : 前进全钢卡客车轮胎GA282A
                 * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539924146_733938.jpeg
                 * c_price : 0.01
                 * c_price_old : 1289.99
                 */

                private String c_id;
                private String c_name;
                private String c_thumbnail;
                private String c_price;
                private String c_price_old;

                @Override
                public String toString() {
                    return "GoodsType1Bean{" +
                            "c_id='" + c_id + '\'' +
                            ", c_name='" + c_name + '\'' +
                            ", c_thumbnail='" + c_thumbnail + '\'' +
                            ", c_price='" + c_price + '\'' +
                            ", c_price_old='" + c_price_old + '\'' +
                            '}';
                }

                public String getC_id() {
                    return c_id;
                }

                public void setC_id(String c_id) {
                    this.c_id = c_id;
                }

                public String getC_name() {
                    return c_name;
                }

                public void setC_name(String c_name) {
                    this.c_name = c_name;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }
            }

            public static class GoodsType2Bean {
                /**
                 * c_id : 150
                 * c_name : 道达尔Quartz 7000汽机油润滑油SN 5W40 4L
                 * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539743910_150097.jpeg
                 * c_price : 0.01
                 * c_price_old : 259.00
                 */

                private String c_id;
                private String c_name;
                private String c_thumbnail;
                private String c_price;
                private String c_price_old;

                @Override
                public String toString() {
                    return "GoodsType2Bean{" +
                            "c_id='" + c_id + '\'' +
                            ", c_name='" + c_name + '\'' +
                            ", c_thumbnail='" + c_thumbnail + '\'' +
                            ", c_price='" + c_price + '\'' +
                            ", c_price_old='" + c_price_old + '\'' +
                            '}';
                }

                public String getC_id() {
                    return c_id;
                }

                public void setC_id(String c_id) {
                    this.c_id = c_id;
                }

                public String getC_name() {
                    return c_name;
                }

                public void setC_name(String c_name) {
                    this.c_name = c_name;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }
            }

            public static class GoodsType3Bean {
                /**
                 * c_id : 160
                 * c_name : 弗列加LF9009机油滤清器
                 * c_thumbnail : http://app.cvfans.net/Data/Commodity/Thumbnail/p_icon_1539765279_945315.jpeg
                 * c_price : 0.01
                 * c_price_old : 124.99
                 */

                private String c_id;
                private String c_name;
                private String c_thumbnail;
                private String c_price;
                private String c_price_old;

                @Override
                public String toString() {
                    return "GoodsType3Bean{" +
                            "c_id='" + c_id + '\'' +
                            ", c_name='" + c_name + '\'' +
                            ", c_thumbnail='" + c_thumbnail + '\'' +
                            ", c_price='" + c_price + '\'' +
                            ", c_price_old='" + c_price_old + '\'' +
                            '}';
                }

                public String getC_id() {
                    return c_id;
                }

                public void setC_id(String c_id) {
                    this.c_id = c_id;
                }

                public String getC_name() {
                    return c_name;
                }

                public void setC_name(String c_name) {
                    this.c_name = c_name;
                }

                public String getC_thumbnail() {
                    return c_thumbnail;
                }

                public void setC_thumbnail(String c_thumbnail) {
                    this.c_thumbnail = c_thumbnail;
                }

                public String getC_price() {
                    return c_price;
                }

                public void setC_price(String c_price) {
                    this.c_price = c_price;
                }

                public String getC_price_old() {
                    return c_price_old;
                }

                public void setC_price_old(String c_price_old) {
                    this.c_price_old = c_price_old;
                }
            }
        }

        public static class HeadImagesBean {
            /**
             * id : 15
             * adv_title : 利物浦
             * adv_img : http://app.cvfans.net/Data/Advertise/20190415/5cb3eb5a03872.jpg
             * adv_url :
             */

            private String id;
            private String adv_title;
            private String adv_img;
            private String adv_url;

            @Override
            public String toString() {
                return "HeadImagesBean{" +
                        "id='" + id + '\'' +
                        ", adv_title='" + adv_title + '\'' +
                        ", adv_img='" + adv_img + '\'' +
                        ", adv_url='" + adv_url + '\'' +
                        '}';
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getAdv_title() {
                return adv_title;
            }

            public void setAdv_title(String adv_title) {
                this.adv_title = adv_title;
            }

            public String getAdv_img() {
                return adv_img;
            }

            public void setAdv_img(String adv_img) {
                this.adv_img = adv_img;
            }

            public String getAdv_url() {
                return adv_url;
            }

            public void setAdv_url(String adv_url) {
                this.adv_url = adv_url;
            }
        }

        public static class VideosBean {
            /**
             * id : 90
             * video_describe : 1
             * video_cover : /Data/Video_cover/25682_1555318489_video_cover.png
             * video_type : 1
             * video_link : /Data/Video/19341_1555318489_video.mp4
             * video_view_num : 1
             * video_dz_num : 0
             * video_publish_user_id : 1203
             * video_state : 2
             * add_time : 1555318489
             * update_time : 1555318523
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
                return "VideosBean{" +
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
}
