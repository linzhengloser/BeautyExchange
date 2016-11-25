package com.qks.beautyexchange.adapter.main;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.R;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

/**
 * 首页详情 适配器
 * Created by admin on 2016/3/14.
 */
public class MainItemDetailRecyclerViewAdapter extends RecyclerView.Adapter{

    private static final int HEANDER = 1;

    private static final int NORMAL = 2;

    private Context mContext;

    public MainItemDetailRecyclerViewAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position ==0 ? HEANDER : NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        if(viewType == HEANDER){
            return new MainItemDetailContentViewHolder(inflater.inflate(R.layout.recyclerview_item_main_item_detail_content,parent,false));
        }else if(viewType == NORMAL){
            return new MainItemDetailContentViewHolder(inflater.inflate(R.layout.recyclerview_item_main_item_detail_comment,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public final class MainItemDetailContentViewHolder extends RecyclerView.ViewHolder{

        public MainItemDetailContentViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

    public final class MainItemDetailCommentViewHolder extends RecyclerView.ViewHolder{

        public MainItemDetailCommentViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this,itemView);
        }
    }

}
