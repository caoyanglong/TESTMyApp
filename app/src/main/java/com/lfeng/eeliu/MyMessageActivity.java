package com.lfeng.eeliu;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;

import com.lfeng.eeliu.base.BaseFragmentActivity;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 * 我的消息
 */
public class MyMessageActivity extends BaseFragmentActivity {
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public int setContent() {
        return R.layout.mymessage_activity_layout;
    }

    @Override
    public void initView() {
//        toolbar = (Toolbar) findViewById(R.id.toolbar);
//        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collap_layout);
    }

    @Override
    public void initData() {
//        setToolbar();
    }
    /**
     * 设置标题
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        collapsingToolbarLayout.setTitle("登录");
    }


    @Override
    public void initListener() {

    }
}
