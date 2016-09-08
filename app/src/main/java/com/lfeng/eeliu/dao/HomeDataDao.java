package com.lfeng.eeliu.dao;

import java.util.List;

/**
 * Created by CYL on 2016/4/15.
 * email:670654904@qq.com
 * 主页数据
 */
public class HomeDataDao {


    /**
     * code : 200
     * message : 提交成功
     * data : {"msgInfo":[{"id":"2","contents":"this is my contents"}],"adInfo":[{"id":"1","title":"thisis my title","img_src":"http://112.74.68.165/upload/001.jpg","link_url":"http://ww.baidu.com"},{"id":"2","title":"thisis my title","img_src":"http://112.74.68.165/upload/002.jpg","link_url":"http://ww.baidu.com"}],"userInfo":[{"balance":"0","today_income":"0"}]}
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
        /**
         * id : 2
         * contents : this is my contents
         */

        private List<MsgInfoEntity> msgInfo;
        /**
         * id : 1
         * title : thisis my title
         * img_src : http://112.74.68.165/upload/001.jpg
         * link_url : http://ww.baidu.com
         */

        private List<AdInfoEntity> adInfo;
        /**
         * balance : 0
         * today_income : 0
         */

        private List<UserInfoEntity> userInfo;

        public void setMsgInfo(List<MsgInfoEntity> msgInfo) {
            this.msgInfo = msgInfo;
        }

        public void setAdInfo(List<AdInfoEntity> adInfo) {
            this.adInfo = adInfo;
        }

        public void setUserInfo(List<UserInfoEntity> userInfo) {
            this.userInfo = userInfo;
        }

        public List<MsgInfoEntity> getMsgInfo() {
            return msgInfo;
        }

        public List<AdInfoEntity> getAdInfo() {
            return adInfo;
        }

        public List<UserInfoEntity> getUserInfo() {
            return userInfo;
        }

        public static class MsgInfoEntity {
            private String id;
            private String contents;

            public void setId(String id) {
                this.id = id;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getId() {
                return id;
            }

            public String getContents() {
                return contents;
            }
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

        public static class UserInfoEntity {
            private String balance;
            private String today_income;

            public void setBalance(String balance) {
                this.balance = balance;
            }

            public void setToday_income(String today_income) {
                this.today_income = today_income;
            }

            public String getBalance() {
                return balance;
            }

            public String getToday_income() {
                return today_income;
            }
        }
    }
}
