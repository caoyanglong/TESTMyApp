package com.lfeng.eeliu.home;

import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.base.BaseFragment;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.HomeDataDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/1.
 * email:670654904@qq.com
 */
public class HomeFragment extends BaseFragment {
    private ViewPager pager;
    @Override
    public int setContentView() {
        return R.layout.home_fragment_layout;
    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void initData() {
        getHomeData();
    }
    /**
     * 获取首页数据
     */
    private void getHomeData() {
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(Constants.key));
        params.put(KeyConstants.uID, BaseConfig.getUid());
        Log.i("gethome", Constants.GetHome + "?" + params.getParamString());
        finalHttp.get(Constants.GetHome, params, new MyAjaxCallBack() {
            @Override
            public void onSuccess(String s) {
                HomeDataDao homeDataDao = AnalysJson.getDao(s, HomeDataDao.class);
                if(homeDataDao !=null && homeDataDao.getCode() == Constants.requestOk){
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);

            }
        });
    }

    @Override
    public void initListener() {

    }
}
