package com.lfeng.eeliu;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lfeng.eeliu.Utils.AESUtils;
import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.Utils.PhoneUtils;
import com.lfeng.eeliu.Utils.StartCode;
import com.lfeng.eeliu.base.BaseToolbarActivity;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class LoginActivity extends BaseToolbarActivity implements View.OnClickListener{
    private String tag = "LoginActivity";
    private TextView logintTv,registerTv,forgetPasswordTv;
    private EditText userEt,pwdEt;
    @Override
    public int setContent() {
        return R.layout.longin_layout;
    }

    @Override
    public void initView() {
        logintTv = (TextView) findViewById(R.id.longin);
        registerTv = (TextView) findViewById(R.id.register);
        userEt = (EditText) findViewById(R.id.phone_number);
        pwdEt = (EditText) findViewById(R.id.pwd);
        forgetPasswordTv = (TextView) findViewById(R.id.forget_pwd);
    }

    @Override
    public void initData() {
        hideBack();
    }


    @Override
    public void initListener() {
        logintTv.setOnClickListener(this);
        registerTv.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.longin:
                login();
                break;
            case R.id.register:
                startActivity(new Intent(context,RegisterActivity.class));
                break;
            case R.id.forget_pwd:
                startActivity(new Intent(context,ForgetPassWordActivity.class));
                break;
        }
    }

    /**
     * 登录
     */
    private void login() {
//        waitDialog.show();
        String user = userEt.getText().toString();
        String pwd = pwdEt.getText().toString();
        AjaxParams params = new AjaxParams();

        params.put(KeyConstants.token, MD5.GetMD5Code(user));
        params.put(KeyConstants.username, user);
        params.put(KeyConstants.password, AESUtils.encode(pwd));
        params.put(KeyConstants.ip, PhoneUtils.getMacAddress(context));
        params.put(KeyConstants.nativePhone, PhoneUtils.getPhoneNumer(context));
        params.put(KeyConstants.nativeOS, PhoneUtils.getOs());
        params.put(KeyConstants.nativeDev, PhoneUtils.getPhoneModel());
        params.put(KeyConstants.appVer, PhoneUtils.getVersion(context));
        final String url = Constants.Login+"?"+params.getParamString();



        finalHttp.get(Constants.Login, params, new MyAjaxCallBack() {
            @Override
            public void onSuccess(String s) {
                waitDialog.cancel();
                CylLog.log("getlogin", s);
                LoginDao loginDao = AnalysJson.getDao(s,LoginDao.class);
                if(loginDao!=null && loginDao.getCode() == Constants.requestOk){
                    LoginDao.DataEntity entity = loginDao.getData();
                    BaseConfig.setDataEntity(entity);
                    BaseConfig.setLoginDao(loginDao);
                    setResult(StartCode.getStartLoginActivityResultCode);
                    finish();
                }
                else{
                    Toast.makeText(context, "登录失败", Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(context, "登录失败", Toast.LENGTH_LONG).show();
                waitDialog.cancel();
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    @Override
    public String setToolbarTitle() {
        return "登录";
    }
}
