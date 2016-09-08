package com.lfeng.eeliu.dao;

import java.util.List;

/**
 * Created by CYL on 2016/4/19.
 * email:670654904@qq.com
 * 从服务器获取的信息  好友列表  所对应的对象
 */
public class FriendsDao {

    /**
     * code : 200
     * message : 提交成功
     * data : [{"Seq_RowNum":"1","id":"16","friend_account":"158380589988","friend_name":"ghgvg","avatar_url":""},{"Seq_RowNum":"2","id":"15","friend_account":"158360566885","friend_name":"ytyy","avatar_url":""},{"Seq_RowNum":"3","id":"14","friend_account":"158360566885","friend_name":"ytyy","avatar_url":""},{"Seq_RowNum":"4","id":"13","friend_account":"158360566885","friend_name":"ytyy","avatar_url":""},{"Seq_RowNum":"5","id":"12","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"6","id":"11","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"7","id":"10","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"8","id":"9","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"9","id":"8","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"10","id":"7","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"11","id":"6","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""},{"Seq_RowNum":"12","id":"5","friend_account":"15836069652","friend_name":"gghhh","avatar_url":""}]
     */

    private int code;
    private String message;
    /**
     * Seq_RowNum : 1
     * id : 16
     * friend_account : 158380589988
     * friend_name : ghgvg
     * avatar_url :
     */

    private List<DataEntity> data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<DataEntity> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public List<DataEntity> getData() {
        return data;
    }

    public static class DataEntity {
        private String Seq_RowNum;
        private String id;
        private String friend_account;
        private String friend_name;
        private String avatar_url;

        public void setSeq_RowNum(String Seq_RowNum) {
            this.Seq_RowNum = Seq_RowNum;
        }

        public void setId(String id) {
            this.id = id;
        }

        public void setFriend_account(String friend_account) {
            this.friend_account = friend_account;
        }

        public void setFriend_name(String friend_name) {
            this.friend_name = friend_name;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public String getSeq_RowNum() {
            return Seq_RowNum;
        }

        public String getId() {
            return id;
        }

        public String getFriend_account() {
            return friend_account;
        }

        public String getFriend_name() {
            return friend_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }
    }
}
