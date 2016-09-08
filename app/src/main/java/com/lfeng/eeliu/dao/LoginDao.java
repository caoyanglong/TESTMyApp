package com.lfeng.eeliu.dao;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class LoginDao {


    /**
     * code : 200
     * message : 成功
     * data : {"id":"1","telephone":"15136205311","tel_auth":"False","area_code":"0","area_name":"","bank_holder":"","bank_name":"","bank_account":"","address":"","add_time":"2016/4/15 16:29:39","user_name":"15136205311","nick_name":"15136205311","real_name":"","avatar_url":"","game_balance":"0.00","balance":"0.00","msg_num":"0"}
     */

    private int code;
    private String message;
    /**
     * id : 1
     * telephone : 15136205311
     * tel_auth : False
     * area_code : 0
     * area_name :
     * bank_holder :
     * bank_name :
     * bank_account :
     * address :
     * add_time : 2016/4/15 16:29:39
     * user_name : 15136205311
     * nick_name : 15136205311
     * real_name :
     * avatar_url :
     * game_balance : 0.00
     * balance : 0.00
     * msg_num : 0
     */

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
        private String id;
        private String telephone;
        private String tel_auth;
        private String area_code;
        private String area_name;
        private String bank_holder;
        private String bank_name;
        private String bank_account;
        private String address;
        private String add_time;
        private String user_name;
        private String nick_name;
        private String real_name;
        private String avatar_url;
        private String game_balance;
        private String balance;
        private String msg_num;

        public void setId(String id) {
            this.id = id;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setTel_auth(String tel_auth) {
            this.tel_auth = tel_auth;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setBank_holder(String bank_holder) {
            this.bank_holder = bank_holder;
        }

        public void setBank_name(String bank_name) {
            this.bank_name = bank_name;
        }

        public void setBank_account(String bank_account) {
            this.bank_account = bank_account;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setNick_name(String nick_name) {
            this.nick_name = nick_name;
        }

        public void setReal_name(String real_name) {
            this.real_name = real_name;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setGame_balance(String game_balance) {
            this.game_balance = game_balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setMsg_num(String msg_num) {
            this.msg_num = msg_num;
        }

        public String getId() {
            return id;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getTel_auth() {
            return tel_auth;
        }

        public String getArea_code() {
            return area_code;
        }

        public String getArea_name() {
            return area_name;
        }

        public String getBank_holder() {
            return bank_holder;
        }

        public String getBank_name() {
            return bank_name;
        }

        public String getBank_account() {
            return bank_account;
        }

        public String getAddress() {
            return address;
        }

        public String getAdd_time() {
            return add_time;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getNick_name() {
            return nick_name;
        }

        public String getReal_name() {
            return real_name;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getGame_balance() {
            return game_balance;
        }

        public String getBalance() {
            return balance;
        }

        public String getMsg_num() {
            return msg_num;
        }
    }
}
