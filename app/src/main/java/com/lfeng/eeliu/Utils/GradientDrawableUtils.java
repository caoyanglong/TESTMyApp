package com.lfeng.eeliu.Utils;

import android.graphics.drawable.GradientDrawable;

import com.lfeng.eeliu.MyApplication;

/**
 * Created by CYL on 2016/4/13.
 * email:670654904@qq.com
 */
public class GradientDrawableUtils {
    /**
     * 设置背景  圆角大小  和   背景色
     * @param corners
     * @param slide
     * @return
     */
    public static GradientDrawable getShapDrawAble(int corners,int slide){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(Sp2Px.sp2px(MyApplication.getMyContext(), corners));
        drawable.setColor(slide);
        return  drawable;
    }

    /**
     * 设置圆角 和 描边的
     * @param corners  圆角的半径
     * @param stroke   描边的颜色
     * @param width    描边的宽度
     * @return
     */
    public static GradientDrawable getShapeDrawAble(int corners,int stroke,int width){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(Sp2Px.sp2px(MyApplication.getMyContext(), corners));
        drawable.setStroke(width, stroke);
        return  drawable;
    }
    /**
     * 设置圆角 和 描边的
     * @param corners  圆角的半径
     * @return
     */
    public static GradientDrawable getShapeDrawAble(int corners){
        GradientDrawable drawable = new GradientDrawable();
        drawable.setCornerRadius(Sp2Px.sp2px(MyApplication.getMyContext(), corners));
        return  drawable;
    }
}
