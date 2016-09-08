package com.lfeng.eeliu;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.lfeng.eeliu.base.BaseFragmentActivity;
import com.lfeng.eeliu.fragment.ExchangeFragment;
import com.lfeng.eeliu.home.HomeFragment;
import com.lfeng.eeliu.fragment.InidiaFragmemt;
import com.lfeng.eeliu.fragment.PersonalFragment;

/**
 * Created by CYL on 2016/4/1.
 * email:670654904@qq.com
 * 主容器
 */
public class MainNewContainerActivity extends BaseFragmentActivity implements CompoundButton.OnCheckedChangeListener
,ViewPager.OnPageChangeListener{
    private PersonalFragment personalFragment;
    private HomeFragment homeFragment;
    private InidiaFragmemt inidiaFragmemt;
    private RadioButton homeRB,exchangeRB,personalRB;
    private RadioGroup radioGroup;
    private TextView titleTv;
    private ViewPager containerViewpager;
    private ContianerViewpagerAdapter adapter;
    /**
     * 系统当前记录的时间
     */
    private long currentTime;
    @Override
    public int setContent() {
        return R.layout.main_container_activity;
    }

    @Override
    public void initView() {
        //获取浮动窗口视图所在布局
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        homeRB = (RadioButton) findViewById(R.id.home_rd);
        exchangeRB = (RadioButton) findViewById(R.id.exchange_rd);
        personalRB = (RadioButton) findViewById(R.id.personal_rd);
        radioGroup = (RadioGroup) findViewById(R.id.radio_group);
        titleTv = (TextView) findViewById(R.id.title);
        containerViewpager = (ViewPager) findViewById(R.id.container);

    }

    /**
     * 根据用户选择 id 去做
     * @param id
     */
    private void setTabSelection(int id) {
        switch (id) {
            case R.id.home_rd:
                containerViewpager.setCurrentItem(0);
                titleTv.setText(R.string.home);
                break;
            case R.id.exchange_rd:
                containerViewpager.setCurrentItem(1);
                titleTv.setText(R.string.exchange);
                break;
            case R.id.personal_rd:
                containerViewpager.setCurrentItem(2);
                titleTv.setText(R.string.personal);
                break;
            default:
                break;
        }
    }

    /**
     * 隐藏目前显示的fragment
     * @param transaction
     */
    private void hindeAllFragment(FragmentTransaction transaction) {
        if(homeFragment!=null){
            transaction.hide(homeFragment);
        }
        if(personalFragment!=null){
            transaction.hide(personalFragment);
        }
        if(inidiaFragmemt!=null){
            transaction.hide(inidiaFragmemt);
        }
    }
    @Override
    public void initData() {
        setTabSelection(R.id.home_rd);
        homeRB.setSelected(true);
        adapter = new ContianerViewpagerAdapter(fragmentManager);
        containerViewpager.setAdapter(adapter);

    }



    @Override
    public void initListener() {
        homeRB.setOnCheckedChangeListener(this);
        exchangeRB.setOnCheckedChangeListener(this);
        personalRB.setOnCheckedChangeListener(this);
        containerViewpager.setOnPageChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            setTabSelection(buttonView.getId());
            switch (buttonView.getId()){
                case R.id.exchange_rd:

                    break;
                case  R.id.home_rd:
                    break;
                case  R.id.personal_rd:
                    break;
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() ==  android.R.id.home){
            existApplication();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        existApplication();
    }

    /**
     * 退出应用
     */
    private void existApplication() {
        long now = System.currentTimeMillis();
        if(now - currentTime < 1000){
            finish();
        }
        else{
            currentTime = now;
            Toast.makeText(context, "再点一次退出应用", Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 适配器
     */
    private class ContianerViewpagerAdapter extends FragmentPagerAdapter {
        public ContianerViewpagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HomeFragment();
                case 1:
                    return new ExchangeFragment();
                case 2:
                    return new PersonalFragment();
            }
            return null;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        switch (position){
            case 0:
                homeRB.setChecked(true);
                break;
            case 1:
                exchangeRB.setChecked(true);
                break;
            case 2:
                personalRB.setChecked(true);
                break;
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
