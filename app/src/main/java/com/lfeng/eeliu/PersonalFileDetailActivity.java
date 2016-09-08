package com.lfeng.eeliu;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.BitmapAndDrawable;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.CylStringUtils;
import com.lfeng.eeliu.Utils.ImageUtil;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.Utils.SDCard;
import com.lfeng.eeliu.base.BaseToolbarActivity;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.dao.UserInfoDao;
import com.lfeng.eeliu.error.ErrorProcer;
import com.lfeng.eeliu.network.MyAjaxCallBack;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import java.io.File;

/**
 * Created by CYL on 2016/4/14.
 * email:670654904@qq.com
 * 个人资料界面
 */
public class PersonalFileDetailActivity extends BaseToolbarActivity implements View.OnClickListener{
    private ImageView header;
    private TextView nickName,phone,address,exitLogin;
    @Override
    public int setContent() {
        return R.layout.personalfile_detailactivity_layout;
    }

    @Override
    public void initView() {
        header = (ImageView) findViewById(R.id.header);
        nickName = (TextView) findViewById(R.id.nickname);
        phone = (TextView) findViewById(R.id.telphone);
        address = (TextView) findViewById(R.id.address);
        exitLogin = (TextView) findViewById(R.id.exit_login);
    }

    @Override
    public void initData() {
        getPersonalFile();
    }

    /**
     * 获取用的详细信息
     */
    private void getPersonalFile() {
        LoginDao.DataEntity entity = BaseConfig.getDataEntity();
        if(entity == null){
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(entity.getId()));
        params.put(KeyConstants.uID, entity.getId());
        CylLog.log("personalfile", Constants.GetUserInfo, params);
        final String url = CylStringUtils.getAjaxUrl(Constants.GetUserInfo,params);
        finalHttp.get(Constants.GetUserInfo, params, new MyAjaxCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                CylLog.log("personalfile", s);
                UserInfoDao infoDao = AnalysJson.getDao(s, UserInfoDao.class);
                if (infoDao != null && infoDao.getCode() == Constants.requestOk) {
                    loadUserInfoSuccess(infoDao);
                } else {
                    ErrorProcer.handleDataException(url + "" + s);
                }
            }
            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
                ErrorProcer.handleServerException(url, errorNo, strMsg);
            }
        });
    }

    /**
     * 加载 个人信息成功
     * @param infoDao
     */
    private void loadUserInfoSuccess(UserInfoDao infoDao) {
        UserInfoDao.DataEntity entity = infoDao.getData();
        if(entity != null){
            nickName.setText(entity.getNick_name());
            address.setText(entity.getAddress());
            phone.setText(entity.getTelephone());
//            finalBitmap.display(header, entity.getAvatar_url());
            Bitmap bitmap = BitmapAndDrawable.bitmapFromFile(SDCard.getPath(Constants.picture)+"header.png");
            CylLog.log("headerurl",CylStringUtils.ImageUrl(entity.getAvatar_url()));
            if(bitmap != null){
                header.setImageBitmap(ImageUtil.getCroppedRoundBitmap(bitmap, 90));
            }
            else{
                getHeaderFromServer(entity);
            }
        }
    }

    /**
     * 从服务器获取头像
     * @param entity
     */
    private void getHeaderFromServer(UserInfoDao.DataEntity entity) {
        finalHttp.download(CylStringUtils.ImageUrl(entity.getAvatar_url()), SDCard.getPath(Constants.picture)+"header.png",new AjaxCallBack<File>(){
            @Override
            public void onSuccess(File file) {
                super.onSuccess(file);
                Bitmap bitmap = BitmapAndDrawable.bitmapFromFile(file.getAbsolutePath());
                if(bitmap != null){
                    header.setImageBitmap(ImageUtil.getCroppedRoundBitmap(bitmap, 90));
                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }


    @Override
    public void initListener() {
        exitLogin.setOnClickListener(this);
    }

    @Override
    public String setToolbarTitle() {
        return "个人资料";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.exit_login:
                BaseConfig.setDataEntity(null);
                finish();
                break;
        }
    }
}
