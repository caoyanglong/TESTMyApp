package com.lfeng.eeliu.Utils;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class Constants {
    /**
     * 默认的key
     */
    public final static String key = "F2DN64N8N26448T4D6F6BP820V4FPF4V";
    public final static String host = "http://112.74.68.165";
    /**
     * 定义一些常用目录
     */

    public final static String root = "/eeliu";
    public final static String picture = root + "/picture";
    public final static String log = root + "/log";
    public final static String config = root + "/config";


    /**
     * 得到系统信息
     */
    public final static String GetSysInfo = host + "/appservice.asmx/GetSysInfo";
    /**
     * 登录接口
     */
    public final static String Login = host + "/service.asmx/Login";
    /**
     * 得到商家的基本信息
     */
    public final static String GetBusinessInfo = host + "/appservice.asmx/GetBusinessInfo";
    /**
     * 积分兑换
     */
    public final static String BusinessExchange = host + "/appservice.asmx/BusinessExchange";
    /**
     * 获取主页信息
     */
    public final static String GetHome = host + "/service.asmx/GetHome";
    /**
     * 用户注册
     */
    public final static String Regiser = host + "/service.asmx/Regiser";
    /**
     * 修改密码
     */
    public final static String EditPwd = host + "/service.asmx/EditPwd";
    /**
     * 获取用信息
     */
    public final static String GetUserInfo = host + "/service.asmx/GetUserInfo";
    /**
     * 获取好友列表
     */
    public final static String FriendList = host + "/service.asmx/FriendList";
    /**
     * 添加好友
     */
    public final static String FriendAdd = host + "/service.asmx/FriendAdd";
    /**
     * 投递快件
     */
    public final static String SendExpress = host + "/service.asmx/SendExpress";
    /**
     * 获取验证码
     */
    public final static String GetVerifyCode = host + "/service.asmx/GetVerifyCode";
    /**
     * 获取夺宝列表
     */
    public final static String GameList = host + "/service.asmx/GameList";
    /**
     * 上传头像
     */
    public final static String UPHeadPic = host + "/service.asmx/UPHeadPic";
    /**
     * 用户消息列表
     */
    public final static String MsgList = host + "/service.asmx/MsgList";
    /**
     * 请求服务器 响应成功
     */
    public final static int requestOk = 200;

    /**
     * 回调 接口
     */
    public final static  String backName = "baskname";



}
