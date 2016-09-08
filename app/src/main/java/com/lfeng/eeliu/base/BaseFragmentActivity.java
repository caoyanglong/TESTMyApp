package com.lfeng.eeliu.base;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;

import com.lfeng.eeliu.WaitDialog;
import com.lfeng.eeliu.network.MyHttp;

import net.tsz.afinal.FinalHttp;

/**
 * 将activity 中经常写的代码 进行格式化
 * @author 曹阳龙
 * 
 * 2015-9-13
 */
public abstract class BaseFragmentActivity extends AppCompatActivity{
	protected FinalHttp finalHttp = MyHttp.getMyFinalHttp();
    protected FragmentManager fragmentManager;
    //资源管理
	protected Resources resources;
	//上下环境  
    protected Context context;
	protected WaitDialog waitDialog;
	protected LayoutInflater inflater;
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
		initView();
		initData();
		initListener();
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

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		onFragmentActivityResult(requestCode,resultCode,data);
	}

	/**
	 * fragment 调用  activity  用于回调
	 * @param requestCode  请求码
	 * @param resultCode   结果码
	 * @param data          具有的数据
	 */
	public void onFragmentActivityResult(int requestCode, int resultCode, Intent data){
		if(listener != null){
			listener.onFragmentActivityResult(requestCode,resultCode,data);
		}
	}

	private FragmentActivityListener listener;

	/**
	 * 设置fragment 中activity的回调事件
	 * @param listener
	 */
	public void setOnFragmentActivityListener(FragmentActivityListener listener){
		this.listener = listener;
	}
	/**
	 * fragment 回调接口
	 */
	public interface FragmentActivityListener{
		void onFragmentActivityResult(int requestCode, int resultCode, Intent data);
	}
}
