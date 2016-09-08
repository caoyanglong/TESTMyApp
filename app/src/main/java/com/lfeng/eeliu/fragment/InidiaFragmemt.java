package com.lfeng.eeliu.fragment;

import android.view.View;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylStringUtils;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.base.BaseFragment;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.GameListDao;
import com.lfeng.eeliu.error.ErrorProcer;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/26.
 * email:670654904@qq.com
 * 夺宝信息
 */
public class InidiaFragmemt extends BaseFragment {
    private int index = 1;
    @Override
    public int setContentView() {
        return R.layout.inidia_fragment_layout;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        getInidia();
    }

    /**
     * 从网络获取夺宝信息
     */
    private void getInidia() {
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.pageSize, BaseConfig.getPageSize());
        params.put(KeyConstants.pageIndex, index + "");
        final String url = CylStringUtils.getAjaxUrl(Constants.GameList,params);
        finalHttp.get(Constants.GameList,params,new MyAjaxCallBack(){
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ErrorProcer.handleServerException(url,errorNo,strMsg);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ErrorProcer.handleDataException(url+s);
                GameListDao gameListDao = AnalysJson.getDao(s, GameListDao.class);
                if(gameListDao != null && gameListDao.getCode() == Constants.requestOk){

                }
                else{
                }
            }
        });
    }

    @Override
    public void initListener() {

    }
}
