package com.lfeng.eeliu;

import android.content.Intent;
import android.os.CountDownTimer;
import android.view.Window;
import android.view.WindowManager;

import com.lfeng.eeliu.network.InitNetWork;

/**
 * Created by CYL on 2016/3/30.
 * email:670654904@qq.com
 */
public class SplashActivity extends com.lfeng.eeliu.base.BaseFragmentActivity {
    private CountDownTimer countDownTimer = new CountDownTimer(3000,3000) {
        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            startActivity(new Intent(context, com.lfeng.eeliu.account.RegisterActivity.class));
            finish();
        }
    };
    @Override
    public int setContent() {
        return R.layout.splash_activity_layout;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        InitNetWork netWork = new InitNetWork();
        netWork.getTokent();
        countDownTimer.start();
    }

    @Override
    public void initListener() {

    }

    @Override
    public void onBackPressed() {

    }
    @Override
    public void beforeSetContent() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
	      /*set it to be full screen*/
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }
}
