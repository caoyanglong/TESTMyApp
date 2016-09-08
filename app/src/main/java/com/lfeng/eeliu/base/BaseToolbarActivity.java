package com.lfeng.eeliu.base;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.WaitDialog;
import com.lfeng.eeliu.network.MyHttp;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;

/**
 * Created by CYL on 2016/4/19.
 * email:670654904@qq.com
 */
public abstract class BaseToolbarActivity extends AppCompatActivity{
    protected FinalHttp finalHttp = MyHttp.getMyFinalHttp();
    protected FinalBitmap finalBitmap = MyHttp.getBitmap();
    protected FragmentManager fragmentManager;
    //资源管理
    protected Resources resources;
    //上下环境
    protected Context context;
    protected WaitDialog waitDialog;
    protected LayoutInflater inflater;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
        onCreate();
    }

    /**
     * 本类 中做的初始化
     */
    private void init() {
        beforeSetContent();
        setContentView(setContent());
        context = this;
        //使用得到  支持的supportfragmentmanager
        fragmentManager = getSupportFragmentManager();
        resources = getResources();
        waitDialog = new WaitDialog(context);
        inflater = LayoutInflater.from(context);
    }

    /**
     * 用于设置布局文件
     * @return
     */
    public abstract int setContent();
    /**
     * oncreat 改为内部执行
     */
    private void onCreate(){
        initBaseView();
        initView();
        initData();
        initListener();
    }

    /**
     * 初始化 特有的view
     */
    private void initBaseView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collap_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        collapsingToolbarLayout.setTitle(setToolbarTitle());
    }

    /**
     * 设置toolbar 标题
     * @return
     */
    public abstract String setToolbarTitle();

    /**
     * 隐藏返回按钮
     */
    public void hideBack(){
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 提供这个方法原因  ，  主要  有一些代码需要在  setcontent 之前调用  如 ： window窗口的设置
     */
    public void beforeSetContent(){

    }

    /**
     * 首次   必先初始化  view
     */
    public abstract void initView();
    /**
     * 初始化数据   从网络 获取数据  数据添加到  view  等逻辑 工作
     */
    public abstract void initData();
    /**
     * 初始化  view listener 的工作
     */
    public abstract void initListener();
}
