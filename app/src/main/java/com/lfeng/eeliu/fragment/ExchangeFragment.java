package com.lfeng.eeliu.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.AESUtils;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.base.BaseFragment;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/1.
 * email:670654904@qq.com
 * 积分兑换fragment
 */
public class ExchangeFragment extends BaseFragment implements View.OnClickListener{
    private TextView exchange;
    private EditText phoneEt,acountEt;
    @Override
    public int setContentView() {
        return R.layout.main_activity_layout;
    }

    @Override
    public void initView(View view) {
        exchange = (TextView) getView(view, R.id.exchange);
        phoneEt = (EditText)  getView(view, R.id.user_phone);
        acountEt = (EditText)  getView(view, R.id.user_count);
    }



    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exchange:
                exchange();
                break;
            case R.id.user_count:
                break;
        }
    }
    /**
     * 积分兑换
     */
    private void exchange() {
        String bid = "";
        LoginDao loginDao = BaseConfig.getLoginDao();
        if(loginDao != null){
            LoginDao.DataEntity entity = loginDao.getData();
            if(entity != null){
                bid = entity.getId();
            }
        }
        else{
            return;
        }
        if(!TextUtils.isEmpty(bid)){
            waitDialog.show();
            String phone = phoneEt.getText().toString();
            String count = acountEt.getText().toString();
            AjaxParams params = new AjaxParams();
            params.put(KeyConstants.token, MD5.GetMD5Code(bid));
            params.put(KeyConstants.bID,bid);
            params.put(KeyConstants.usertel, AESUtils.encode(phone));
            params.put(KeyConstants.amount, AESUtils.encode(count));
            finalHttp.get(Constants.BusinessExchange, params, new MyAjaxCallBack() {
                @Override
                public void onSuccess(String s) {
                    waitDialog.cancel();
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    super.onFailure(t, errorNo, strMsg);
                    waitDialog.cancel();
                }
            });
        }
    }
}
