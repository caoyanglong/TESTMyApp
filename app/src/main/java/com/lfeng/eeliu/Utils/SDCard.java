package com.lfeng.eeliu.Utils;

import android.os.Environment;

import java.io.File;

/**
 * Created by CYL on 2016/4/26.
 * email:670654904@qq.com
 */
public class SDCard {
    /**
     * sd中目录
     * @param path /f../../
     * @return
     */
    public static String getPath(String path){
        path = Environment.getExternalStorageDirectory()+path;
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        return path;
    }
}
