package com.lfeng.eeliu.Utils;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;

/**
 * Created by CYL on 2016/4/19.
 * email:670654904@qq.com
 */
public class Base64Utils {
    /**
     * utf-8编码
     * @param msg
     * @return
     */
    public static String encode(String msg,String charset){
        try {
            return new String((Base64.encode(msg.getBytes(charset), Base64.DEFAULT)));
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 默认UTF-8解码
     * @param msg
     */
    public static String decode(String msg){
        return decode(msg,"UTF-8");
    }
    public static String decode(String msg,String charset){
        try {
            return new String(Base64.decode(msg.getBytes(charset),Base64.DEFAULT));
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 传递来一个路径 将文件转换未  base64的字符
     * @param path              文件路径   sd 文件  如图片
     * @return                  base64 的字符
     * @throws Exception
     */
    public static String encodeBase64File(String path) throws Exception {
        File file = new File(path);
        FileInputStream inputFile = new FileInputStream(file);
        byte[] data = new byte[(int)file.length()];
        inputFile.read(data);
        inputFile.close();
        return Base64.encodeToString(data, Base64.DEFAULT);
    }
    /**
     * bitmap 转 base64的字符串
     * @param data
     * @return
     * @throws Exception
     */
    @SuppressLint("NewApi")
    public static String encodeBase64File(byte[] data) throws Exception {
        return Base64.encodeToString(data,Base64.DEFAULT);
    }
    /**
     * 将bitmap 转化为  字节数组
     * @param bm         bitmap 位图
     * @return
     */
    public static byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 将图标转化为base64
     * @param bitmap  图片
     * @return
     */
    public static String bitmapToBase64(Bitmap bitmap){
        String base64 = null;
        byte data[] = Bitmap2Bytes(bitmap);
        try {
           base64 = encodeBase64File(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return base64;
    }

}
