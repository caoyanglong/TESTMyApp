package com.lfeng.eeliu.account.loader;


import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxParams;

/**
 * Created by cyl
 * on 2016/9/7.
 * email:670654904@qq.com
 */
public class RegisterLoader extends AsyncTaskLoader<String> {
    private String requestUrl = "http://115.28.9.92/eliu/index.php/App/Token/register";
    //账号
    public final static String USERLOGIN = "user_login";
    //用户密码
    public final static String USERPASS = "user_pass";
    //商家推荐码
    public final static String SUPERIOR = "superior";
    private AjaxParams params = new AjaxParams();
    public RegisterLoader(Context context,String urser,String pass,String superior) {
        super(context);
        params.put(USERLOGIN,urser);
        params.put(USERPASS,pass);
        if(superior != null){
            params.put(SUPERIOR,superior);
        }
        forceLoad();
    }

    @Override
    public String loadInBackground() {
        try {
            String content = (String) new FinalHttp().getSync(requestUrl,params);
            Log.d("<content>",content);
            return content;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }
}
