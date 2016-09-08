package com.lfeng.eeliu.base;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfeng.eeliu.WaitDialog;
import com.lfeng.eeliu.network.MyHttp;

import net.tsz.afinal.FinalHttp;

/**
 * fragment  代码进行格式化
 * @author 曹阳龙
 * 
 * 2015-9-13
 */
public abstract class BaseFragment extends Fragment {
    protected Context context;
	protected WaitDialog waitDialog;
	protected FinalHttp finalHttp = MyHttp.getMyFinalHttp();
    @Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(setContentView(), container, false);
	}
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		init(view);
		onViewCreated(view);
	}

	private void init(View view) {
		context = view.getContext();
		waitDialog = new WaitDialog(context);
	}

	public Context getContext() {
		return context;
	}
	public void onViewCreated(View view){
		initView(view);
		initData();
		initListener();
	}

	/**
	 * 设置fragment 的布局文件防止 遗忘
	 * @return
	 */
	public abstract int setContentView();
	
	/**
	 * 首次   必先初始化  view 
	 */
	public abstract void initView(View view);
	/**
	 * 初始化数据   从网络 获取数据  数据添加到  view  等逻辑 工作
	 */
	public abstract void initData();
	/**
	 * 初始化  view listener 的工作
	 */
	public abstract void initListener();

	/**
	 * 通过获取view上的子view
	 * @param view    父view
	 * @param viewid  子view 的id
	 * @return
	 */
	public View getView(View view,int viewid){
		return  view.findViewById(viewid);
	}
}