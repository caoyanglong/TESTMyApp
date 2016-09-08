package com.lfeng.eeliu.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lfeng.eeliu.R;
import com.lfeng.eeliu.dao.GetBusinessInfoDao;

import java.util.List;

/**
 * Created by CYL on 2016/4/2.
 * email:670654904@qq.com
 * recyclerview 的适配器
 *
 * 注意泛型使用的是viewholder
 */
public class PersonalAdapter extends RecyclerView.Adapter<PersonalAdapter.PersonalHolder> {
    private List<GetBusinessInfoDao> dataSource;
    private LayoutInflater inflater;

    public PersonalAdapter(List<GetBusinessInfoDao> dataSource, Context context) {
        this.dataSource = dataSource;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public PersonalHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new PersonalHolder(inflater.inflate(R.layout.personal_item,parent,false));
    }


    @Override
    public void onBindViewHolder(PersonalHolder holder, int position) {
        //数据绑定
        //在这里面绑定数据源
        GetBusinessInfoDao getBusinessInfoDao = dataSource.get(position);
        if(getBusinessInfoDao != null){
            List<GetBusinessInfoDao.DataEntity> entities = getBusinessInfoDao.getData();
            if(entities.size()>position){
                GetBusinessInfoDao.DataEntity entity = entities.get(position);
                if(entity != null){
                    holder.textView.setText(entity.toString());
                }
            }
        }

    }

    /**
     * 实现一个viewholder
     */
    public class  PersonalHolder extends  RecyclerView.ViewHolder{
        private TextView textView;
        public PersonalHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
    @Override
    public int getItemCount() {
        return dataSource.size();
    }
}
