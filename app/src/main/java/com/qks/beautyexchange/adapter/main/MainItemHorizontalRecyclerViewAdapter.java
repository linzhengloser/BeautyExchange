package com.qks.beautyexchange.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

/**
 * 首页可以横着滚动的RecyclerView的adapter
 * Created by admin on 2016/3/9.
 */
public class MainItemHorizontalRecyclerViewAdapter extends RecyclerView.Adapter<MainItemHorizontalRecyclerViewAdapter.MyViewHolder>{

    private Context mContext;

    private int mItemLayoutResId;

    public MainItemHorizontalRecyclerViewAdapter(Context context,int itemLayoutResId){
        this.mContext = context;
        this.mItemLayoutResId = itemLayoutResId;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(mItemLayoutResId,parent,false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public final class MyViewHolder extends RecyclerView.ViewHolder{

        public MyViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
        }
    }

}
