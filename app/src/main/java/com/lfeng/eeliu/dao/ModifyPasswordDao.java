package com.lfeng.eeliu.dao;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 */
public class ModifyPasswordDao {

    /**
     * code : 200
     * message : 修改成功
     * data :
     */

    private int code;
    private String message;
    private String data;

    public void setCode(int code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public String getData() {
        return data;
    }
}
