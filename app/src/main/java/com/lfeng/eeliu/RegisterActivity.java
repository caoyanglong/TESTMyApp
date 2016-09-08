package com.lfeng.eeliu;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.clearedittext.ClearEditText;
import com.lfeng.eeliu.Utils.AESUtils;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.base.BaseFragmentActivity;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/16.
 * email:670654904@qq.com
 * 注册功能实现
 */
public class RegisterActivity extends BaseFragmentActivity implements View.OnClickListener{
    private ClearEditText pwd1,pwd2,username;
    private TextView register,longin,forgetPwd;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public int setContent() {
        return R.layout.register_activity_layout;
    }

    @Override
    public void initView() {
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collap_layout);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        username = (ClearEditText) findViewById(R.id.phone_number);
        pwd1 = (ClearEditText) findViewById(R.id.pwd1);
        pwd2 = (ClearEditText) findViewById(R.id.pwd2);

        register = (TextView) findViewById(R.id.register);
        longin = (TextView) findViewById(R.id.longin);

        forgetPwd = (TextView) findViewById(R.id.forget_pwd);

    }

    @Override
    public void initData() {
        setToolBar();
    }

    private void setToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("注册");
    }

    @Override
    public void initListener() {
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                register();
                break;
            case R.id.longin:
                break;
            case R.id.forget_pwd:
                startActivity(new Intent(context,ForgetPassWordActivity.class));
                break;
        }
    }

    /**
     * 注册前检测  密码  以及用户名
     */
    private void register() {
        String userName = username.getText().toString();
        String passWord = pwd1.getText().toString();
        String passWord2 = pwd2.getText().toString();
        if(!TextUtils.isEmpty(userName)){
            if(!TextUtils.isEmpty(passWord)){
                if(passWord.equals(passWord2)){
                    handleRegister(userName,passWord);
                }
                else{

                }
            }
            else{

            }
        }
        else{

        }

    }

    private void handleRegister(String usn,String pwd) {
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(usn));
        params.put(KeyConstants.username, usn);
        params.put(KeyConstants.password, AESUtils.encode(pwd));
        finalHttp.get(Constants.Regiser,params,new MyAjaxCallBack(){
            @Override
            public void onSuccess(String s) {
                CylLog.log("resiger",s);
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }
}
