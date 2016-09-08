package com.lfeng.eeliu.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfeng.eeliu.AboutActivity;
import com.lfeng.eeliu.ExchangeHistoryActivity;
import com.lfeng.eeliu.LoginActivity;
import com.lfeng.eeliu.MyFriendsActivity;
import com.lfeng.eeliu.MyMessageActivity;
import com.lfeng.eeliu.PersonalFileDetailActivity;
import com.lfeng.eeliu.R;
import com.lfeng.eeliu.Utils.Base64Utils;
import com.lfeng.eeliu.Utils.BitmapAndDrawable;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylStringUtils;
import com.lfeng.eeliu.Utils.ImageUtil;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.Utils.MyToast;
import com.lfeng.eeliu.Utils.PictureUtil;
import com.lfeng.eeliu.Utils.SDCard;
import com.lfeng.eeliu.Utils.StartCode;
import com.lfeng.eeliu.base.BaseFragment;
import com.lfeng.eeliu.base.BaseFragmentActivity;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.error.ErrorProcer;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

import java.io.File;

/**
 * Created by CYL on 2016/4/1.
 * email:670654904@qq.com
 * 个人中心
 */
public class PersonalFragment extends BaseFragment implements View.OnClickListener,BaseFragmentActivity.FragmentActivityListener{
    private View clickToLogin,userContainer,personalContainer,myMessageContainer,exchangeHistoryContainer,
            myfriedsContainer,aboutEliuContainer,checkUpdateContainer;
    private TextView userName;
    private ImageView header;
    private LoginDao.DataEntity dataEntity = BaseConfig.getDataEntity();
    private BaseFragmentActivity activity;
    private Bitmap updateHeader;
    @Override
    public int setContentView() {
        return R.layout.personal_fragment_layout;
    }

    @Override
    public void initView(View view) {
        clickToLogin = getView(view,R.id.click_login);
        userContainer = getView(view,R.id.user_name_container);
        userName = (TextView) getView(view,R.id.user_name);
        header = (ImageView) getView(view,R.id.header);
        myfriedsContainer = getView(view,R.id.my_frieds_container);
        personalContainer = getView(view,R.id.personfile_container);
        myMessageContainer = getView(view,R.id.my_message_container);
        exchangeHistoryContainer = getView(view,R.id.exchange_history_container);
        aboutEliuContainer = getView(view,R.id.about_eeliu);
        checkUpdateContainer = getView(view,R.id.check_update);
    }

    @Override
    public void initData() {
        activity = (BaseFragmentActivity) getActivity();
        hideAndShowView();
    }

    @Override
    public void initListener() {
        clickToLogin.setOnClickListener(this);
        myfriedsContainer.setOnClickListener(this);
        personalContainer.setOnClickListener(this);
        myMessageContainer.setOnClickListener(this);
        exchangeHistoryContainer.setOnClickListener(this);
        aboutEliuContainer.setOnClickListener(this);
        activity.setOnFragmentActivityListener(this);
        header.setOnClickListener(this);
    }

    /**
     * 从网络获取用户的信息
     */
    private void getBuniessInfo() {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.click_login:
                startActivityForResult(new Intent(context, LoginActivity.class), StartCode.startLoginActivityCode);
                break;
            case R.id.personfile_container:
                startActivity(new Intent(context, PersonalFileDetailActivity.class));
                break;
            case R.id.my_message_container:
                startActivity(new Intent(context, MyMessageActivity.class));
                break;
            case R.id.exchange_history_container:
                startActivity(new Intent(context, ExchangeHistoryActivity.class));
                break;
            case R.id.my_frieds_container:
                startActivity(new Intent(context, MyFriendsActivity.class));
                break;
            case R.id.about_eeliu:
                startActivity(new Intent(context, AboutActivity.class));
                break;
            case R.id.check_update:

                break;
            case R.id.header:
                PictureUtil.doPickPhotoAction(getActivity());
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == resultCode && requestCode == StartCode.startLoginActivityCode){
            LoginDao loginDao = BaseConfig.getLoginDao();
            if(loginDao != null&&loginDao.getData() != null){
                dataEntity = loginDao.getData();
                if(dataEntity!=null){
                    hideAndShowView();
                }
            }
        }
    }

    /**
     * 由于登录和不登录是两种状态所以 需要 对UI进行操作
     */
    private void hideAndShowView() {

        if(dataEntity != null){
            clickToLogin.setVisibility(View.GONE);
            userContainer.setVisibility(View.VISIBLE);
            userName.setText(dataEntity.getUser_name());
        }
        else{
            clickToLogin.setVisibility(View.VISIBLE);
            userContainer.setVisibility(View.GONE);
        }
    }

    @Override
    public void onFragmentActivityResult(int requestCode, int resultCode, Intent intent) {
        if (resultCode == Activity.RESULT_OK) {
            switch (requestCode) {
                case PictureUtil.PHOTO_PICKED_WITH_DATA:
                    Uri data2 = intent.getData();
                    ImageUtil.cropImage(data2, activity);
                    break;
                case PictureUtil.REQUEST_CAMERA:
                    ImageUtil.cropImage(Uri.fromFile(new File(SDCard.getPath(Constants.picture), "header.png")),activity);
                    break;
                case PictureUtil.PHOTO_CROP:
                    Bundle bundle = intent.getExtras();
                    Bitmap myBitmap = (Bitmap) bundle.get("data");
                    updateHeader = ImageUtil.comp(myBitmap);
//                    ImageUtil.saveBitmap2file(updateHeader, new File(SDCard.getPicturePath(), "header.png"));
                    BitmapAndDrawable.saveBitmap2file(updateHeader, SDCard.getPath(Constants.picture), "header.png");
                    upLoadHeaderToServer();
                default:
                    break;
            }
        }
    }

    /**
     * 上传头像到 服务器
     */
    private void upLoadHeaderToServer() {
        if(dataEntity == null){
            MyToast.makeText("对不起，你未登录");
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(dataEntity.getId()));
        params.put(KeyConstants.uID, dataEntity.getId());
        params.put(KeyConstants.imgInfo, Base64Utils.bitmapToBase64(updateHeader));
        final String url = CylStringUtils.getAjaxUrl(Constants.UPHeadPic,params);
        finalHttp.post(Constants.UPHeadPic,params,new MyAjaxCallBack(){
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ErrorProcer.handleServerException(url, errorNo, strMsg);
            }

            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                ErrorProcer.handleDataException(s);
                header.setImageBitmap(ImageUtil.getCroppedRoundBitmap(updateHeader, 90));
                BitmapAndDrawable.saveBitmap2file(updateHeader,Constants.picture,"header.png");
            }
        });
    }
}
