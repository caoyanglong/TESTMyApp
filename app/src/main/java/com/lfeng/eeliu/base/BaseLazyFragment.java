package com.lfeng.eeliu.base;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.lfeng.eeliu.network.MyHttp;

import net.tsz.afinal.FinalHttp;

/**
 * fragment  实现viewpager 懒加载
 * 假设viewpager 中有  有5个标签  对应fragmentA ，B ,C,D,E等
 * 通过修改fragment 的生命周期可以实现  viewpager 滑动到那个选项卡 那个  fragment 开始加载
 * 1，降低软件的压力（一次初始化很多布局文件 是相当消耗资源的）
 * 2，同事缓解程序的网络压力（有可能每个页面都需要从网络读取数据这样可以延迟到用户点击那个页面的 才开始加载）
 * @author cyl
 * email:670654904@qq.com
 * 2016年2月18日
 */
public abstract class BaseLazyFragment extends Fragment {
    /**
	 * 是否准备好了
	 */
    private boolean isPrepared;
    protected Context context;
//    protected WaitDialog waitDialog;
    protected FinalHttp finalHttp = MyHttp.getMyFinalHttp();
    private View view;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(setContentView(), container, false);
        context = view.getContext();
        return view;
    }

    /**
     * 第一次onResume中的调用onUserVisible避免操作与onFirstUserVisible操作重复
     */
    private boolean isFirstResume = true;

    @Override
    public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }
    /**
     * fragment 第一次可见
     */
    private boolean isFirstVisible = true;
    /**
     * fragment 第一次不可见
     */
    private boolean isFirstInvisible = true;

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    public synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }

    /**
     * 第一次fragment可见（进行初始化工作）
     */
    public void onFirstUserVisible() {
        initView(view);
        initData();
        initListener();
    }

    /**
     * fragment可见（切换回来或者onResume）
     */
    public void onUserVisible() {

    }

    /**
     * 第一次fragment不可见（不建议在此处理事件）
     */
    public void onFirstUserInvisible() {

    }

    /**
     * fragment不可见（切换掉或者onPause）
     */
    public void onUserInvisible() {

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