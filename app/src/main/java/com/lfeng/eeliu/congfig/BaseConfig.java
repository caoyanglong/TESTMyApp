package com.lfeng.eeliu.congfig;

import android.text.TextUtils;

import com.lfeng.eeliu.dao.LoginDao;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class BaseConfig {
    /**
     * 登录信息
     */
    private static LoginDao loginDao;
    /**
     * 登录数据
     */
    private static LoginDao.DataEntity dataEntity;
    private static String bid = "";
    /**
     * 用户id
     */
    private static String uid="0";
    /**
     * 获取信息的条数
     */
    private static String pageSize = "10";

    public static LoginDao getLoginDao() {
        return loginDao;
    }


    public static void setLoginDao(LoginDao loginDao) {
        BaseConfig.loginDao = loginDao;
    }

    public static String getBid() {
        if(TextUtils.isEmpty(bid)){
            if(loginDao!=null){
                LoginDao.DataEntity entity = loginDao.getData();
                if(entity != null){
                    bid = entity.getId();
                }
            }
        }
        return bid;
    }

    /**
     * 获取用户id
     * @return
     */
    public static String getUid() {
        return uid;
    }

    /**
     * 设置全局的用户id
     * @param uid
     */
    public static void setUid(String uid) {
        BaseConfig.uid = uid;
    }

    /**
     * 获取登录信息
     * @return
     */
    public static LoginDao.DataEntity getDataEntity() {
        return dataEntity;
    }

    /**
     * 设置登录信息
     * @param dataEntity
     */
    public static void setDataEntity(LoginDao.DataEntity dataEntity) {
        BaseConfig.dataEntity = dataEntity;
    }

    public static void setBid(String bid) {
        BaseConfig.bid = bid;
    }

    /**
     * 获取
     * @return
     */
    public static String getPageSize() {
        return pageSize;
    }

    public static void setPageSize(String pageSize) {
        BaseConfig.pageSize = pageSize;
    }
}
