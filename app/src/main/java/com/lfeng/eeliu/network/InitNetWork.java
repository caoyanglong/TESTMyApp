package com.lfeng.eeliu.network;

import android.util.Log;

import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.dao.GetSystemInfoDao;

import net.tsz.afinal.FinalHttp;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class InitNetWork {
    private FinalHttp finalHttp = MyHttp.getMyFinalHttp();
    public void getTokent(){
        finalHttp.get(Constants.GetSysInfo, new MyAjaxCallBack() {
            @Override
            public void onSuccess(String s) {
                Log.i("onSuccess", s);
                GetSystemInfoDao getSystemInfoDao = AnalysJson.getDao(s,GetSystemInfoDao.class);
                if(getSystemInfoDao != null){
                    if(getSystemInfoDao.getCode() == Constants.requestOk){
                    }
                }
                else{
                    Log.i("onSuccess","---------------");
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
            }
        });
    }
}
