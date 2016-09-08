package com.lfeng.eeliu.Utils;

import android.widget.Toast;

import com.lfeng.eeliu.MyApplication;


/**
 * Created by CYL on 2016/4/21.
 * email:670654904@qq.com
 */
public class MyToast {
    /**
     * 测试的时候显示的toast 可以统一管理
     * @param log
     */
    public static void makeTextLog(String log){
        if(log!=null){
            Toast.makeText(MyApplication.getMyContext(), log, Toast.LENGTH_LONG).show();
        }
        else{
            makeTextLog("显示的log为空");
        }
    }

    /**
     * 可以用到给用提示的信息
     * @param log
     */
    public static void makeText(String log){
        if(log!=null){
            Toast.makeText(MyApplication.getMyContext(), log, Toast.LENGTH_LONG).show();
        }
    }
}
