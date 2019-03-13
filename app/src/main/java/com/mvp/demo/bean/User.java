package com.mvp.demo.bean;

import java.util.List;

/**
 * @author wangyibo
 * @date 2019/1/30
 */
public class User extends BaseBean{

    private List<DataBean> data;

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * category_parent_id : 29
         * category_parent_name : 信用卡
         * goods_info : [{"category_id":"44","category_name":"华夏银行","create_time":"1552373343","goods_information":{"card_characteristic":"首刷送6个月爱奇艺VIP 刷卡达标...","card_label_name":"视频卡,联名卡,积分","card_url_path":"http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=6","goods_id":"6","goods_image_path":"uploads/2019/01/02/5c2c25f681633.jpg","goods_name":"华夏爱奇艺悦看卡 ","goods_type_name":"联名卡"},"goods_key":"card_id","goods_value_id":"6","shop_id":"511","shop_status":"1","user_id":"36"},{"category_id":"44","category_name":"华夏银行","create_time":"1552373346","goods_information":{"card_characteristic":"首刷送6个月腾讯视频VIP 刷卡达...","card_label_name":"视频卡,联名卡,积分","card_url_path":"http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=1","goods_id":"1","goods_image_path":"uploads/2019/01/02/5c2c241214061.jpg","goods_name":"华夏腾讯新闻xin联名信用卡 ","goods_type_name":"联名卡"},"goods_key":"card_id","goods_value_id":"1","shop_id":"512","shop_status":"1","user_id":"36"},{"category_id":"44","category_name":"华夏银行","create_time":"1552373348","goods_information":{"card_characteristic":"首刷送3个月喜马拉雅PM会员刷 卡...","card_label_name":"联名卡,喜马拉雅FM","card_url_path":"http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=3","goods_id":"3","goods_image_path":"uploads/2019/01/02/5c2c24e5102a6.jpg","goods_name":"华夏喜马拉雅FM联名信用卡","goods_type_name":"联名卡"},"goods_key":"card_id","goods_value_id":"3","shop_id":"513","shop_status":"1","user_id":"36"},{"category_id":"44","category_name":"华夏银行","create_time":"1552373350","goods_information":{"card_characteristic":"首刷送6个月腾讯视频VIP 刷卡达...","card_label_name":"视频卡,联名卡,积分","card_url_path":"http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=2","goods_id":"2","goods_image_path":"uploads/2019/01/02/5c2c24533d11d.jpg","goods_name":"华夏腾讯新闻xin联名信用卡 ","goods_type_name":"联名卡"},"goods_key":"card_id","goods_value_id":"2","shop_id":"514","shop_status":"1","user_id":"36"}]
         */

        private String category_parent_id;
        private String category_parent_name;
        private List<GoodsInfoBean> goods_info;

        public String getCategory_parent_id() {
            return category_parent_id;
        }

        public void setCategory_parent_id(String category_parent_id) {
            this.category_parent_id = category_parent_id;
        }

        public String getCategory_parent_name() {
            return category_parent_name;
        }

        public void setCategory_parent_name(String category_parent_name) {
            this.category_parent_name = category_parent_name;
        }

        public List<GoodsInfoBean> getGoods_info() {
            return goods_info;
        }

        public void setGoods_info(List<GoodsInfoBean> goods_info) {
            this.goods_info = goods_info;
        }

        public static class GoodsInfoBean {
            /**
             * category_id : 44
             * category_name : 华夏银行
             * create_time : 1552373343
             * goods_information : {"card_characteristic":"首刷送6个月爱奇艺VIP 刷卡达标...","card_label_name":"视频卡,联名卡,积分","card_url_path":"http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=6","goods_id":"6","goods_image_path":"uploads/2019/01/02/5c2c25f681633.jpg","goods_name":"华夏爱奇艺悦看卡 ","goods_type_name":"联名卡"}
             * goods_key : card_id
             * goods_value_id : 6
             * shop_id : 511
             * shop_status : 1
             * user_id : 36
             */

            private String category_id;
            private String category_name;
            private String create_time;
            private GoodsInformationBean goods_information;
            private String goods_key;
            private String goods_value_id;
            private String shop_id;
            private String shop_status;
            private String user_id;

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public GoodsInformationBean getGoods_information() {
                return goods_information;
            }

            public void setGoods_information(GoodsInformationBean goods_information) {
                this.goods_information = goods_information;
            }

            public String getGoods_key() {
                return goods_key;
            }

            public void setGoods_key(String goods_key) {
                this.goods_key = goods_key;
            }

            public String getGoods_value_id() {
                return goods_value_id;
            }

            public void setGoods_value_id(String goods_value_id) {
                this.goods_value_id = goods_value_id;
            }

            public String getShop_id() {
                return shop_id;
            }

            public void setShop_id(String shop_id) {
                this.shop_id = shop_id;
            }

            public String getShop_status() {
                return shop_status;
            }

            public void setShop_status(String shop_status) {
                this.shop_status = shop_status;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public static class GoodsInformationBean {
                /**
                 * card_characteristic : 首刷送6个月爱奇艺VIP 刷卡达标...
                 * card_label_name : 视频卡,联名卡,积分
                 * card_url_path : http://h5.qinjiakonggu.com/credit_card/creditCard_details.html?card_id=6
                 * goods_id : 6
                 * goods_image_path : uploads/2019/01/02/5c2c25f681633.jpg
                 * goods_name : 华夏爱奇艺悦看卡
                 * goods_type_name : 联名卡
                 */

                private String card_characteristic;
                private String card_label_name;
                private String card_url_path;
                private String goods_id;
                private String goods_image_path;
                private String goods_name;
                private String goods_type_name;

                public String getCard_characteristic() {
                    return card_characteristic;
                }

                public void setCard_characteristic(String card_characteristic) {
                    this.card_characteristic = card_characteristic;
                }

                public String getCard_label_name() {
                    return card_label_name;
                }

                public void setCard_label_name(String card_label_name) {
                    this.card_label_name = card_label_name;
                }

                public String getCard_url_path() {
                    return card_url_path;
                }

                public void setCard_url_path(String card_url_path) {
                    this.card_url_path = card_url_path;
                }

                public String getGoods_id() {
                    return goods_id;
                }

                public void setGoods_id(String goods_id) {
                    this.goods_id = goods_id;
                }

                public String getGoods_image_path() {
                    return goods_image_path;
                }

                public void setGoods_image_path(String goods_image_path) {
                    this.goods_image_path = goods_image_path;
                }

                public String getGoods_name() {
                    return goods_name;
                }

                public void setGoods_name(String goods_name) {
                    this.goods_name = goods_name;
                }

                public String getGoods_type_name() {
                    return goods_type_name;
                }

                public void setGoods_type_name(String goods_type_name) {
                    this.goods_type_name = goods_type_name;
                }
            }
        }
    }
}
