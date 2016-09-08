package com.lfeng.eeliu;

import android.content.Intent;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.lfeng.eeliu.Utils.AnalysJson;
import com.lfeng.eeliu.Utils.Constants;
import com.lfeng.eeliu.Utils.CylLog;
import com.lfeng.eeliu.Utils.KeyConstants;
import com.lfeng.eeliu.Utils.MD5;
import com.lfeng.eeliu.adapter.FriendsRecylerAdapter;
import com.lfeng.eeliu.base.BaseFragmentActivity;
import com.lfeng.eeliu.congfig.BaseConfig;
import com.lfeng.eeliu.dao.FriendsDao;
import com.lfeng.eeliu.dao.LoginDao;
import com.lfeng.eeliu.network.MyAjaxCallBack;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 * 我的好友
 */
public class MyFriendsActivity extends BaseFragmentActivity {
    private RecyclerView friendsRecycler;
    private FriendsRecylerAdapter adapter;
    private List<FriendsDao.DataEntity> dataSource;
    private Toolbar toolbar;
    private CollapsingToolbarLayout collapsingToolbarLayout;
    @Override
    public int setContent() {
        return R.layout.my_friends_activity_layout;
    }

    @Override
    public void initView() {
        friendsRecycler = (RecyclerView) findViewById(R.id.my_frieds);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collap_layout);
    }

    @Override
    public void initData() {
        getFriendsList();
        dataSource = new ArrayList<>();
//        for (int i= 0;i<10;i++){
//            dataSource.add(new FriendsAdapterDao("张三","10086"));
//        }
        adapter = new FriendsRecylerAdapter(context,dataSource);
        friendsRecycler.setLayoutManager(new LinearLayoutManager(context));
        friendsRecycler.setAdapter(adapter);
        setToolbar();
    }

    /**
     * 获取用户的好友列表
     */
    private void getFriendsList() {
        LoginDao.DataEntity entity = BaseConfig.getDataEntity();
        if(entity == null){
            Toast.makeText(context,"请先登录",Toast.LENGTH_LONG).show();
            return;
        }
        AjaxParams params = new AjaxParams();
        params.put(KeyConstants.token, MD5.GetMD5Code(entity.getId()));
        params.put(KeyConstants.uID,entity.getId());
        params.put(KeyConstants.keys,"");
        params.put(KeyConstants.pageSize,"12");
        params.put(KeyConstants.pageIndex,"1");
        finalHttp.get(Constants.FriendList, params, new MyAjaxCallBack(){
            @Override
            public void onSuccess(String s) {
                CylLog.log("getfriends_list", s);
                super.onSuccess(s);
                FriendsDao friendsDao = AnalysJson.getDao(s,FriendsDao.class);
                if(friendsDao !=null && friendsDao.getCode() == Constants.requestOk){
                    List<FriendsDao.DataEntity> entities = friendsDao.getData();
                    if(entities != null && entities.size()>0){
                        dataSource.addAll(entities);
                        adapter.notifyDataSetChanged();
                    }
                }
                else{

                }
            }

            @Override
            public void onFailure(Throwable t, int errorNo, String strMsg) {
                super.onFailure(t, errorNo, strMsg);
            }
        });
    }

    /**
     * 设置标题
     */
    private void setToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        collapsingToolbarLayout.setTitle("我的好友");
    }

    @Override
    public void initListener() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.myfriends_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.add_friend:
                startActivity(new Intent(context,AddFriendsActivity.class));
                break;
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
