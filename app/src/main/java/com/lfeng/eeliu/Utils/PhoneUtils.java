package com.lfeng.eeliu.Utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * 对于手机固有属性的 一些值的提取，如何  iemi （设备的唯一标识码） 软件版本 （md5），软件版本，系统版本 ，操作系统 等信息
 * @author 曹阳龙
 *
 * 2015-9-12
 */
public class PhoneUtils {
    /**
	 * 得到用户手机的iemi
	 * 
	 * @param context
	 * @return
	 */
	public static String getIemi(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imei = telephonyManager.getSimSerialNumber();
		if(imei==null){
			//如果用户没有安装手机卡  默认 为10 个0
			imei = "0000000000";
		}
		return imei;
	}
	/**
	 * 得到手机号  （不是很准确 ，仅仅可以得到手机中设置的手机号）
	 * @param context
	 * @return
	 */
	public static String getPhoneNumer(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		return telephonyManager.getLine1Number();
	}
//	/**
//	 * 用于匹配手机号
//	 * @param phone
//	 * @return
//	 */
//	public static boolean matchPhone(String phone){
//		return machContent(MatchExp.phone, phone);
//	}
//	/**
//	 * 用于匹配邮箱
//	 * @param email
//	 * @return
//	 */
//	public static boolean matchEmail(String email){
//		return machContent(MatchExp.email, email);
//	}
	/**
	 * ^[a-z0-9]+([._\\-]*[a-z0-9])*@([a-z0-9]+[-a-z0-9]*[a-z0-9]+.){1,63}[a-z0-9]+$   邮箱格式  和哈 
	 * 根据提供正则 匹配所给的内容是否正确 
	 * @param expre      正则表达式
	 * @param content    需要匹配的内容
	 * @return
	 */
	public static boolean machContent(String expre,String content){
		Pattern p = Pattern.compile(expre);
		Matcher matcher = p.matcher(content);
		return matcher.matches();
	}
	/**
	 * 获取手机的唯一 mac 地址
	 * @param context
	 * @return
	 */
	public static String getMacAddress(Context context){
		WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo info = wifi.getConnectionInfo();
		return info.getMacAddress();
	}
	/**
	 * 得到运营商的国际编码
	 * @param context
	 * @return
	 */
	public static String getSubscriberId(Context context){
		TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
		String imsi = telephonyManager.getSubscriberId();
		if(imsi==null){
			imsi = telephonyManager.getSimOperator(); 
		}
		return imsi;
	}
//	/**
//	   * 获取版本号
//	   * @return 当前应用的版本号 的MD5 值
//	   */
//	  @SuppressLint("DefaultLocale")
//	public static String getVersionMd5(Context context) {
//	      try {
//	    	 String version = getVersion(context);
//	         String localmd5 = Md5.md5(version);
//	         ExtennalLog.writeLog("version:"+version+"\r\n"+"md5:"+localmd5);
//	         return localmd5.toLowerCase();
//	     } catch (Exception e) {
//         e.printStackTrace();
//	         return null;
//	     }
//	 }
	  /**
	   * 获得系统的版本号
	   * @param context
	   * @return
	   */
	 public static String getVersion(Context context){
		  PackageManager manager = context.getPackageManager();
	      PackageInfo info = null;
		try {
			info = manager.getPackageInfo(context.getPackageName(), 0);
		} catch (NameNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	      return info.versionName;
	  }
	/**
	 * 获取当前手机操作系统版本
	 * @return
	 */
	@SuppressLint("DefaultLocale")
	public static String getOs(){
		String os = android.os.Build.VERSION.RELEASE;
		if(!os.toLowerCase().contains("android")){
			os = "android "+os;
		}
		return os;
	}
	/**
	 * 获取手机型号
	 * @return
	 */
	public static String getPhoneModel(){
		return android.os.Build.MODEL;
	}
}
