package com.lfeng.eeliu.fragment;

import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.GradientDrawableUtils;
import com.lfeng.eeliu.base.BaseLazyFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYL on 2016/4/11.
 * email:670654904@qq.com
 * 可以通过支付宝提现
 */
public class AliExchangeFragment extends BaseLazyFragment implements RadioGroup.OnCheckedChangeListener,CompoundButton.OnCheckedChangeListener,View.OnClickListener{
    private RadioGroup aliRg;
    private RadioButton fifty,hundred,fiveHundred;
    private TextView exchangeNow;
    private View v;
    private List<RadioButton> radioButtons = new ArrayList<>();
    @Override
    public int setContentView() {
        return R.layout.ali_exchange_fragment_layout;
    }

    @Override
    public void initView(View view) {
        aliRg = (RadioGroup) getView(view,R.id.ali_rg);
        fifty = (RadioButton) getView(view,R.id.fifty);
        hundred = (RadioButton) getView(view,R.id.hundred);
        fiveHundred = (RadioButton) getView(view,R.id.five_hundred);
        exchangeNow = (TextView) getView(view,R.id.exchange_now);
        v = getView(view,R.id.container);
    }

    @Override
    public void initData() {
        radioButtons.add(fifty);
        radioButtons.add(hundred);
//        radioButtons.add()
    }

    @Override
    public void initListener() {
        aliRg.setOnCheckedChangeListener(this);
        fifty.setOnCheckedChangeListener(this);
        hundred.setOnCheckedChangeListener(this);
        fiveHundred.setOnCheckedChangeListener(this);
        exchangeNow.setOnClickListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {

        switch (checkedId){
            case R.id.fifty:
                break;
            case R.id.hundred:
                break;
            case R.id.five_hundred:
                break;
        }
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            buttonView.setBackground(GradientDrawableUtils.getShapeDrawAble(10, Color.parseColor("#FF0000"),1));
        }
        else {
            buttonView.setBackground(GradientDrawableUtils.getShapeDrawAble(10));
        }
    }

    @Override
    public void onClick(View v) {
        PopupWindow popupWindow = new PopupWindow();
        popupWindow.setContentView(LayoutInflater.from(context).inflate(R.layout.red_popwindow_layout, null));
        popupWindow.setFocusable(true);
        popupWindow.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        popupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.showAtLocation(v, Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0);
    }
}
