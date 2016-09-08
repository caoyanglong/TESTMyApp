package com.lfeng.eeliu.dao;

import java.util.List;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class GetBusinessInfoDao {

    /**
     * code : 200
     * message : 成功
     * data : [{"id":"1","user_name":"15136205347","user_pwd":"FDD166BCBECC0A8D","salt":"4FBN842","avatar_url":"","rid":"0","total_balance":"0.00","balance":"0.00","rate":"0.00","business_name":"测试商家2","shop_name":"测试店铺1","telephone":"15136205347","area_code":"0","area_name":"","qq":"17199987152","bank_holder":"rabbit","bank_name":"工商","bank_account":"123456789","shop_address":"河南省郑州市金水区","grade":"1","status":"1","add_time":"2016-03-28 14:34:44"}]
     */

    private int code;
    private String message;
    /**
     * id : 1
     * user_name : 15136205347
     * user_pwd : FDD166BCBECC0A8D
     * salt : 4FBN842
     * avatar_url :
     * rid : 0
     * total_balance : 0.00
     * balance : 0.00
     * rate : 0.00
     * business_name : 测试商家2
     * shop_name : 测试店铺1
     * telephone : 15136205347
     * area_code : 0
     * area_name :
     * qq : 17199987152
     * bank_holder : rabbit
     * bank_name : 工商
     * bank_account : 123456789
     * shop_address : 河南省郑州市金水区
     * grade : 1
     * status : 1
     * add_time : 2016-03-28 14:34:44
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
        private String id;
        private String user_name;
        private String user_pwd;
        private String salt;
        private String avatar_url;
        private String rid;
        private String total_balance;
        private String balance;
        private String rate;
        private String business_name;
        private String shop_name;
        private String telephone;
        private String area_code;
        private String area_name;
        private String qq;
        private String bank_holder;
        private String bank_name;
        private String bank_account;
        private String shop_address;
        private String grade;
        private String status;
        private String add_time;

        public void setId(String id) {
            this.id = id;
        }

        public void setUser_name(String user_name) {
            this.user_name = user_name;
        }

        public void setUser_pwd(String user_pwd) {
            this.user_pwd = user_pwd;
        }

        public void setSalt(String salt) {
            this.salt = salt;
        }

        public void setAvatar_url(String avatar_url) {
            this.avatar_url = avatar_url;
        }

        public void setRid(String rid) {
            this.rid = rid;
        }

        public void setTotal_balance(String total_balance) {
            this.total_balance = total_balance;
        }

        public void setBalance(String balance) {
            this.balance = balance;
        }

        public void setRate(String rate) {
            this.rate = rate;
        }

        public void setBusiness_name(String business_name) {
            this.business_name = business_name;
        }

        public void setShop_name(String shop_name) {
            this.shop_name = shop_name;
        }

        public void setTelephone(String telephone) {
            this.telephone = telephone;
        }

        public void setArea_code(String area_code) {
            this.area_code = area_code;
        }

        public void setArea_name(String area_name) {
            this.area_name = area_name;
        }

        public void setQq(String qq) {
            this.qq = qq;
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

        public void setShop_address(String shop_address) {
            this.shop_address = shop_address;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public void setAdd_time(String add_time) {
            this.add_time = add_time;
        }

        public String getId() {
            return id;
        }

        public String getUser_name() {
            return user_name;
        }

        public String getUser_pwd() {
            return user_pwd;
        }

        public String getSalt() {
            return salt;
        }

        public String getAvatar_url() {
            return avatar_url;
        }

        public String getRid() {
            return rid;
        }

        public String getTotal_balance() {
            return total_balance;
        }

        public String getBalance() {
            return balance;
        }

        public String getRate() {
            return rate;
        }

        public String getBusiness_name() {
            return business_name;
        }

        public String getShop_name() {
            return shop_name;
        }

        public String getTelephone() {
            return telephone;
        }

        public String getArea_code() {
            return area_code;
        }

        public String getArea_name() {
            return area_name;
        }

        public String getQq() {
            return qq;
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

        public String getShop_address() {
            return shop_address;
        }

        public String getGrade() {
            return grade;
        }

        public String getStatus() {
            return status;
        }

        public String getAdd_time() {
            return add_time;
        }

        @Override
        public String toString() {
            return "DataEntity{" +
                    "id='" + id + '\'' +
                    ", user_name='" + user_name + '\'' +
                    ", user_pwd='" + user_pwd + '\'' +
                    ", salt='" + salt + '\'' +
                    ", avatar_url='" + avatar_url + '\'' +
                    ", rid='" + rid + '\'' +
                    ", total_balance='" + total_balance + '\'' +
                    ", balance='" + balance + '\'' +
                    ", rate='" + rate + '\'' +
                    ", business_name='" + business_name + '\'' +
                    ", shop_name='" + shop_name + '\'' +
                    ", telephone='" + telephone + '\'' +
                    ", area_code='" + area_code + '\'' +
                    ", area_name='" + area_name + '\'' +
                    ", qq='" + qq + '\'' +
                    ", bank_holder='" + bank_holder + '\'' +
                    ", bank_name='" + bank_name + '\'' +
                    ", bank_account='" + bank_account + '\'' +
                    ", shop_address='" + shop_address + '\'' +
                    ", grade='" + grade + '\'' +
                    ", status='" + status + '\'' +
                    ", add_time='" + add_time + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "GetBusinessInfoDao{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
