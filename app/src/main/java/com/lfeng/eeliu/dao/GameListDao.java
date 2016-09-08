package com.lfeng.eeliu.dao;

import java.util.List;

/**
 * Created by CYL on 2016/4/26.
 * email:670654904@qq.com
 */
public class GameListDao {

    /**
     * code : 200
     * message : 成功
     * data : {"msgInfo":[],"adInfo":[{"id":"1","title":"thisis my title","img_src":"http://112.74.68.165/upload/001.jpg","link_url":"http://ww.baidu.com"}],"productInfo":[]}
     */

    private int code;
    private String message;
    private DataEntity data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public DataEntity getData() {
        return data;
    }

    public static class DataEntity {
        private List<?> msgInfo;
        /**
         * id : 1
         * title : thisis my title
         * img_src : http://112.74.68.165/upload/001.jpg
         * link_url : http://ww.baidu.com
         */

        private List<AdInfoEntity> adInfo;
        private List<?> productInfo;

        public void setMsgInfo(List<?> msgInfo) {
            this.msgInfo = msgInfo;
        }

        public void setAdInfo(List<AdInfoEntity> adInfo) {
            this.adInfo = adInfo;
        }

        public void setProductInfo(List<?> productInfo) {
            this.productInfo = productInfo;
        }

        public List<?> getMsgInfo() {
            return msgInfo;
        }

        public List<AdInfoEntity> getAdInfo() {
            return adInfo;
        }

        public List<?> getProductInfo() {
            return productInfo;
        }

        public static class AdInfoEntity {
            private String id;
            private String title;
            private String img_src;
            private String link_url;

            public void setId(String id) {
                this.id = id;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImg_src(String img_src) {
                this.img_src = img_src;
            }

            public void setLink_url(String link_url) {
                this.link_url = link_url;
            }

            public String getId() {
                return id;
            }

            public String getTitle() {
                return title;
            }

            public String getImg_src() {
                return img_src;
            }

            public String getLink_url() {
                return link_url;
            }
        }
    }
}
