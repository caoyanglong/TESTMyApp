package com.lfeng.eeliu;

import android.app.Application;
import android.content.Context;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class MyApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this;
    }
    public static Context getMyContext(){
        return context;
    }
}
