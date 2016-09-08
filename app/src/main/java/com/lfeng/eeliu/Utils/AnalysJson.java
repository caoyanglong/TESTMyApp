package com.lfeng.eeliu.Utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.lfeng.eeliu.dao.StateDao;

import java.lang.reflect.Type;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class AnalysJson {
    private static Gson gson = new Gson();
    public static <T> T getDao(String json,Class<T> c){
        try {
            json = json.trim();
            return  gson.fromJson(json,c);
        } catch (JsonSyntaxException e) {
            return null;
        }
    }

    /**
     * 通过type token 获取对应的数据类
     * @param json
     * @param <T>
     * @return
     */
    public static <T> T getDaoByType(String json,Class<T> c){
        Type type = new TypeToken<StateDao<T>>(){}.getType();
        try {
            json = json.trim();
            StateDao<T> stateDao = gson.fromJson(json,type);
            return (T)stateDao.getData();
        } catch (JsonSyntaxException e) {
            return  null;
        }
    }

}
