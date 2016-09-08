package com.lfeng.eeliu.Utils;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/26.
 * email:670654904@qq.com
 */
public class CylStringUtils {
    /**
     * 获取  ajax 的参数
     * @param url
     * @param params
     * @return
     */
    public static String getAjaxUrl(String url ,AjaxParams params){
        return url +"?"+ params.getParamString();
    }

    /**
     * 给图片地址加上主机
     * @param url
     * @return
     */
    public static String ImageUrl(String url){
        if(!url.contains("http")){
            url = Constants.host + url;
        }
        return url;
    }
}

