package com.lfeng.eeliu;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;
/**
 * video  等待对话框
 * @author cyl
 *
 * 2015年10月27日
 */
public class WaitDialog {
	private Context context;
	private Dialog progressDialog;
	private TextView msg;
	public WaitDialog(Context context) {
		super();
		this.context = context;
		progressDialog = new Dialog(context, R.style.progress_dialog);
		progressDialog.setContentView(LayoutInflater.from(context).inflate(R.layout.wait_dialog, null));
		progressDialog.setCancelable(true);
		progressDialog.setCanceledOnTouchOutside(false);
		progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
		msg = (TextView) progressDialog.findViewById(R.id.id_tv_loadingmsg);
		msg.setText("卖力加载中...");
	}
	public void cancel(){
		if(progressDialog != null){
			progressDialog.cancel();
		}
	}
	public void show(){
		if(progressDialog != null){
			progressDialog.show();
		}

		}
	/**
	 * 设置显示内容
	 * @param msg
	 */
	public void setMsg(String msg){
		this.msg.setText(msg);
	}
	public boolean isShowing(){
		return progressDialog.isShowing();
	}

}
