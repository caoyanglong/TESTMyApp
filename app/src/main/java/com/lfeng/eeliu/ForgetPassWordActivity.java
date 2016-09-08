package com.lfeng.eeliu;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.clearedittext.ClearEditText;
import com.lfeng.eeliu.Utils.AESUtils;
import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.base.BaseToolbarActivity;
import com.lfeng.eeliu.dao.ModifyPasswordDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 * 忘记密码界面
 */
public class ForgetPassWordActivity extends BaseToolbarActivity implements View.OnClickListener{
    private ClearEditText pwd1Ed, pwd2Ed, userPhoneEd, verifyCodeEd;
    private TextView getCode,modify;
    @Override
    public int setContent() {
        return R.layout.forgetpassword_acitvity_layout;
    }

    @Override
    public void initView() {
        pwd1Ed = (ClearEditText) findViewById(R.id.pwd1);
        pwd2Ed = (ClearEditText) findViewById(R.id.pwd2);
        userPhoneEd = (ClearEditText) findViewById(R.id.phone_number);
        verifyCodeEd = (ClearEditText) findViewById(R.id.verify_code);
        modify = (TextView) findViewById(R.id.confirm);
        getCode = (TextView) findViewById(R.id.get_code);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        modify.setOnClickListener(this);
        getCode.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.confirm:
                checkModifyPassWord();

                break;
            case R.id.get_code:
                checkGetCode();
                break;
        }
    }

    /**
     * 检测获取code、
     *
     */
    private void checkGetCode() {
        String phone = userPhoneEd.getText().toString();
        AjaxParams params = new AjaxParams();

        if(!TextUtils.isEmpty(phone)){
            getCode(phone, params);
        }
    }

    /**
     * 从服务器获取验证码
     * @param phone
     * @param params
     */
    private void getCode(String phone, AjaxParams params) {
        params.put(KeyConstants.token, MD5.GetMD5Code(phone));
        params.put(KeyConstants.tel, AESUtils.encode(phone));
        finalHttp.get(Constants.GetVerifyCode,params,new MyAjaxCallBack(){
            @Override
            public void onStart() {
                waitDialog.show();
            }

            @Override
            public void onSuccess(String s) {
                CylLog.log("modfiy", s);
                String data = AnalysJson.getDaoByType(s,String.class);
                Toast.makeText(context,data,Toast.LENGTH_LONG).show();
                waitDialog.cancel();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                Toast.makeText(context,"error",Toast.LENGTH_LONG).show();
                waitDialog.cancel();
            }
        });
    }

    /**
     * 修改密码 前检测
     */
    private void checkModifyPassWord() {
        String pwd1 = pwd1Ed.getText().toString();
        String pwd2 = pwd2Ed.getText().toString();
        String userPhone = userPhoneEd.getText().toString();
        if(!TextUtils.isEmpty(pwd1)){
            if(pwd2!=null&&pwd1.equals(pwd1)){
                modifyPassWord(pwd1,userPhone);
            }
            else{
                Toast.makeText(context,"两次密码不一致",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context,"请填写新密码",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 修改密码
     */
    private void modifyPassWord(String pwd,String phone) {
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(phone));
        params.put(KeyConstants.username, phone);
        params.put(KeyConstants.password, AESUtils.encode(pwd));
        finalHttp.get(Constants.EditPwd,params,new MyAjaxCallBack(){
            @Override
            public void onSuccess(String s) {
                CylLog.log("resiger", s);
                ModifyPasswordDao modifyPasswordDao = AnalysJson.getDao(s,ModifyPasswordDao.class);
                if(modifyPasswordDao != null && modifyPasswordDao.getCode() == Constants.requestOk){
                    Toast.makeText(context,"密码修改成功",Toast.LENGTH_LONG).show();
                    finish();
                }
                else
                {
                    Toast.makeText(context,"操作失败",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                Toast.makeText(context,"操作失败",Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public String setToolbarTitle() {
        return "修改密码";
    }
}
