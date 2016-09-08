package com.lfeng.eeliu.Utils;

import android.content.Context;

/**
 * sp 转换成px 
 * @author cyl
 * email:670654904@qq.com
 * 2016年1月28日
 */
public class Sp2Px {
	/**
	 * 将sp值转换为px值，保证文字大小不变
 	 * @param context
	 * @param spValue
	 * @return
	 */
	public static int sp2px(Context context, float spValue) { 
	    final float fontScale = context.getResources().getDisplayMetrics().scaledDensity;  
	    return (int) (spValue * fontScale + 0.5f);  
	}
}
