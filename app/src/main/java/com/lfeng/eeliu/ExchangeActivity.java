package com.lfeng.eeliu;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.lfeng.eeliu.adapter.BaseTabIndicatorAdapter;
import com.lfeng.eeliu.base.BaseFragmentActivity;
import com.lfeng.eeliu.fragment.AliExchangeFragment;
import com.lfeng.eeliu.fragment.ChargeExchangeFragment;
import com.lfeng.eeliu.fragment.FlowExchangeFragment;
import com.lfeng.eeliu.fragment.WXExchangeFragment;
import com.viewpagerindicator.TabPageIndicator;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYL on 2016/4/11.
 * email:670654904@qq.com
 */
public class ExchangeActivity extends BaseFragmentActivity {
    private TabPageIndicator indicator;
    private ViewPager viewPager;
    private List<Fragment> dataSource;
    private BaseTabIndicatorAdapter indicatorAdapter;

    @Override
    public int setContent() {
        return R.layout.exchange_activity_layout;
    }

    @Override
    public void initView() {
        indicator = (TabPageIndicator) findViewById(R.id.indicator);
        viewPager = (ViewPager) findViewById(R.id.exchange_viewpager);
    }

    @Override
    public void initData() {
        dataSource = new ArrayList<>();
        if(dataSource.size()==0){
            dataSource.add(new AliExchangeFragment());
            dataSource.add(new WXExchangeFragment());
            dataSource.add(new ChargeExchangeFragment());
            dataSource.add(new FlowExchangeFragment());
        }
        String titles[] = {"支付宝","微信","话费","流量"};
        indicatorAdapter = new BaseTabIndicatorAdapter(fragmentManager,dataSource,titles);
        viewPager.setAdapter(indicatorAdapter);
        indicator.setViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }
}
