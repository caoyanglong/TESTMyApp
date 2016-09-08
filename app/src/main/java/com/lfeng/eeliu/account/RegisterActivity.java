package com.lfeng.eeliu.account;

import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.clearedittext.ClearEditText;
import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.MyToast;
import com.lfeng.eeliu.account.loader.RegisterLoader;
import com.lfeng.eeliu.base.BaseFragmentActivity;

/**
 * Created by cyl
 * on 2016/9/7.
 * email:670654904@qq.com
 */
public class RegisterActivity extends BaseFragmentActivity implements View.OnClickListener{
    private TextView register;
    private ClearEditText userNameEd;
    private ClearEditText passwordeEd;
    private ClearEditText superirorEd;
    @Override
    public int setContent() {
        return R.layout.new_register_activity_layout;
    }

    @Override
    public void initView() {
        register = (TextView) findViewById(R.id.register);
        userNameEd = (ClearEditText) findViewById(R.id.user_name);
        passwordeEd = (ClearEditText) findViewById(R.id.password);
        superirorEd = (ClearEditText) findViewById(R.id.superior);


    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                String name = userNameEd.getText().toString();
                String password = passwordeEd.getText().toString();
                String superiror = superirorEd.getText().toString();

                if(!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password) && name.length()>=3&&password.length()>=6){
                    Bundle bundle = new Bundle();
                    bundle.putString(RegisterLoader.USERLOGIN,name);
                    bundle.putString(RegisterLoader.USERPASS,password);
                    bundle.putString(RegisterLoader.SUPERIOR,superiror);
                    getSupportLoaderManager().restartLoader(1,bundle,registerLoader);
                }
                else{
                    MyToast.makeText("输入用户名或密码不合法");
              }
                break;
        }
    }

    private final LoaderManager.LoaderCallbacks<String> registerLoader = new LoaderManager.LoaderCallbacks<String>() {
        @Override
        public Loader onCreateLoader(int id, Bundle args) {
            return new RegisterLoader(context,args.getString(RegisterLoader.USERLOGIN),
                    args.getString(RegisterLoader.USERPASS),
                    args.getString(RegisterLoader.SUPERIOR));
        }

        @Override
        public void onLoadFinished(Loader<String> loader, String data) {
            if(data != null){
                MyToast.makeText(data);
            }
            else{
                MyToast.makeText("-------------------");
            }
        }

        @Override
        public void onLoaderReset(Loader<String> loader) {

        }
    };
}
