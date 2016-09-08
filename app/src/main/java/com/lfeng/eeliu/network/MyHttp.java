package com.lfeng.eeliu.network;

import com.lfeng.eeliu.MyApplication;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class MyHttp {
    private static FinalHttp finalHttp;
    private static FinalBitmap bitmap;
    /**
     * 得到同一的网络请求工具
     * @return
     */
    public static FinalHttp getMyFinalHttp(){
        if(finalHttp == null){
            finalHttp = new FinalHttp();
        }
        return finalHttp;
    }

    /**
     * 获取唯一bitmap 处理器
     * @return
     */
    public static FinalBitmap getBitmap(){
        if(bitmap == null){
            bitmap = FinalBitmap.create(MyApplication.getMyContext());
        }
        return bitmap;
    }
            ;
}
