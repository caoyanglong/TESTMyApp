package com.lfeng.eeliu.Utils;

import android.util.Log;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/16.
 * email:670654904@qq.com
 */
public class CylLog {
    /**
     * 日志输出同一管理
     * @param tag
     * @param msg
     */
    public static void log(String tag,String msg){
        Log.i(tag,msg);
    }
    public static void log(String tag,String url,AjaxParams params){
        log(tag,url+"?"+params.getParamString());
    }

}
