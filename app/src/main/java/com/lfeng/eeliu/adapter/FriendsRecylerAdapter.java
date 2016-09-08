package com.lfeng.eeliu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.dao.FriendsDao;

import java.util.List;

/**
 * Created by CYL on 2016/4/18.
 * email:670654904@qq.com
 */
public class FriendsRecylerAdapter extends RecyclerView.Adapter<FriendsRecylerAdapter.Viewholder> {
    //数据源
    private List<FriendsDao.DataEntity> dataSoure;
    //初始化view 的工具
    private LayoutInflater inflater;

    public FriendsRecylerAdapter(Context context,List<FriendsDao.DataEntity> dataSoure){
        this.dataSoure = dataSoure;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Viewholder(inflater.inflate(R.layout.friends_item,parent,false));
    }

    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        FriendsDao.DataEntity dao = dataSoure.get(position);
        holder.name.setText(dao.getFriend_name());
        holder.phone.setText(dao.getFriend_account());
        holder.name.setTag(dao);
    }

    /**
     * recyclerview item 的容器
     */
    public final class Viewholder extends RecyclerView.ViewHolder{
        private ImageView icon;
        private TextView name;
        private TextView phone;
        public Viewholder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.friends_name);
            phone = (TextView) itemView.findViewById(R.id.phone);
        }
    }

    @Override
    public int getItemCount() {
        return dataSoure.size();
    }

}
