package com.lfeng.eeliu.error;

import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.MyToast;

/**
 * Created by CYL on 2016/4/26.
 * email:670654904@qq.com
 * 错误处理器
 * 可以将任何可能出现错误的 地方交给他去处理
 * 数据异常 服务器返回异常 等  问题
 */
public class ErrorProcer {
    /**
     * 处理数据异常
     * @param msg
     */
    public static void handleDataException(String msg){
        MyToast.makeText("数据异常"+msg);
        CylLog.log("数据异常",  msg);
    }

    /**
     *
     * @param url
     * @param msg
     */
    public static void handleDataException(String url,String msg){
        MyToast.makeText("数据异常"+url+msg);
        CylLog.log("数据异常",url+msg);
    }

    /**
     * 反馈的信息
     * @param msg
     */
    public static void handleServerException(String msg){
        CylLog.log("数据异常", msg);
    }

    /**
     *
     * @param url
     * @param errorCode
     * @param msg
     */
    public static void handleServerException(String url,int errorCode,String msg){
        MyToast.makeText("服务器异常"+url+errorCode+msg);
        CylLog.log("数据异常", url + msg+errorCode);
    }

}
