package com.lfeng.eeliu.dao;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 * 这个用于 listview 的
 */
public class FriendsAdapterDao {
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
