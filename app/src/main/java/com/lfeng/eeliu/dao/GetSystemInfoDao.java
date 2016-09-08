package com.lfeng.eeliu.dao;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class GetSystemInfoDao {

    /**
     * code : 200
     * message : 成功
     * data : {"version":"1.0.0","pay_url":"http://112.74.68.165/pay.aspx"}
     */

    private int code;
    private String message;
    /**
     * version : 1.0.0
     * pay_url : http://112.74.68.165/pay.aspx
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
        private String version;
        private String pay_url;

        public void setVersion(String version) {
            this.version = version;
        }

        public void setPay_url(String pay_url) {
            this.pay_url = pay_url;
        }

        public String getVersion() {
            return version;
        }

        public String getPay_url() {
            return pay_url;
        }
    }
}
