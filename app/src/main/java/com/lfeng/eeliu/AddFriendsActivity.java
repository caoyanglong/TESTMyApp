package com.lfeng.eeliu;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.Utils.StartCode;
import com.lfeng.eeliu.base.BaseToolbarActivity;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 */
public class AddFriendsActivity extends BaseToolbarActivity implements View.OnClickListener{
    private View addContactContainer,dialogView;
    private TextView addFriendTv;
    private EditText phoneEd,userNameEd;
    private Dialog dialog;
    private LoginDao.DataEntity dataEntity = BaseConfig.getDataEntity();



    @Override
    public int setContent() {
        return R.layout.addfriends_activity_layout;
    }

    @Override
    public void initView() {
        addContactContainer = findViewById(R.id.add_contact_container);
        addFriendTv = (TextView) findViewById(R.id.confirm);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        addContactContainer.setOnClickListener(this);
        addFriendTv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_contact_container:
                addFriendsFromContact();
                break;
            case R.id.confirm:
                addFriendsByDialog("", "");
                break;
        }
    }

    /**
     * 打开通讯录
     */
    private void addFriendsFromContact() {
        Intent intent = new Intent(Intent.ACTION_PICK,android.provider.ContactsContract.Contacts.CONTENT_URI);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivityForResult(intent, StartCode.startContactActivity);
    }

    /**
     * 添加好友
     * @param phone
     * @param name
     */
    private void addFriendsByDialog(String phone, String name) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        dialogView = getLayoutInflater().inflate(R.layout.add_friends_dialog_layout,null);
        builder.setView(dialogView);
        TextView cancel = (TextView) dialogView.findViewById(R.id.cancel);
        TextView addFriend = (TextView) dialogView.findViewById(R.id.confirm);
        ((EditText) dialogView.findViewById(R.id.username)).setText(name);
        ((EditText) dialogView.findViewById(R.id.phone)).setText(phone);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = ((EditText) dialogView.findViewById(R.id.username)).getText().toString();
                String phone = ((EditText) dialogView.findViewById(R.id.phone)).getText().toString();

                checkAddFriend(username, phone);
            }
        });

        dialog = builder.create();
        dialog.show();
    }

    /**
     * 添加好友时 ，信息监测
     * @param username  好友名字
     * @param phone     好友手机号
     */
    private void checkAddFriend(String username, String phone) {
        if(!TextUtils.isEmpty(phone)){
            if(!TextUtils.isEmpty(username)){
                if(dataEntity == null){
                    Toast.makeText(context, "请先登录", Toast.LENGTH_LONG).show();
                    return;
                }
                addFriend(username, phone);
            }
            else{
                Toast.makeText(context, "请输入好友名字", Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(context,"请输入手机号",Toast.LENGTH_LONG).show();
        }
    }

    /**
     * 添加好友
     * @param username      用户备注
     * @param phone         用户手机号
     */
    private void addFriend(String username, String phone) {
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(dataEntity.getId()));
        params.put(KeyConstants.uID,dataEntity.getId());
        params.put(KeyConstants.friend_account,phone);
        params.put(KeyConstants.friend_name,username);
        CylLog.log("addfrieds", Constants.FriendAdd+"?"+params.getParamString());
        finalHttp.get(Constants.FriendAdd, params, new MyAjaxCallBack() {
            @Override
            public void onSuccess(String s) {
                super.onSuccess(s);
                CylLog.log("addfrieds", s);
                if(dialog != null){
                    dialog.cancel();
                }
                Toast.makeText(context, "添加成功", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == StartCode.startContactActivity){
            Uri uri = data.getData();
            // 得到ContentResolver对象
            ContentResolver cr = getContentResolver();
            // 取得电话本中开始一项的光标
            Cursor cursor = cr.query(uri, null, null, null, null);
            // 向下移动光标
            while (cursor.moveToNext()) {
                // 取得联系人名字
                int nameFieldColumnIndex = cursor
                        .getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME);
                String name = cursor.getString(nameFieldColumnIndex);

                String phone = getPhoneNumber(cursor);
                if(phone != null){
//                    addFriend(name,phone);
//                    MyToast.makeText(phone);
                    addFriendsByDialog(phone, name);
                }

            }

        }
    }

    /**
     * 从回调的结果中读取 通讯录中的手机号
     * @param cursor
     * @return
     */
    private String getPhoneNumber(Cursor cursor) {
        String phone = null;
        String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.NAME_RAW_CONTACT_ID));
        String hasPhone = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER));
        if ( hasPhone.equalsIgnoreCase("1")) {
            hasPhone = "true";
        } else {
            hasPhone = "false" ;
        }
        if (Boolean.parseBoolean(hasPhone)) {
            Cursor phones= getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,ContactsContract.CommonDataKinds.Phone.CONTACT_ID +" = "+ contactId,null, null);
            while (phones.moveToNext()) {
                phone = phones.getString(phones.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }
            phones.close();
        }
        return phone;
    }

    @Override
    public String setToolbarTitle() {
        return "添加好友";
    }
}
