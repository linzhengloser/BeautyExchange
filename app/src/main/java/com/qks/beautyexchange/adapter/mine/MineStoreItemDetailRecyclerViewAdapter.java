package com.qks.beautyexchange.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.listener.RecyclerViewItemClickListener;
import com.zhy.autolayout.utils.AutoUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 *
 * Created by admin on 2016/3/15.
 */
public class MineStoreItemDetailRecyclerViewAdapter extends RecyclerView.Adapter{

    private static final int HEADER = 1;

    private static final int NORMAL = 2;

    private Context mContext;

    private RecyclerViewItemClickListener mListener;

    public MineStoreItemDetailRecyclerViewAdapter(Context context){
        this.mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position == 0 ? HEADER : NORMAL;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == HEADER){
            return new MineStoreItemDetailHeaderViewHoder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_mine_store_item_detail_header,parent,false));
        }else if(viewType == NORMAL){
            return new MineStoreItemDetailNormalViewHoder(LayoutInflater.from(mContext).inflate(R.layout.recyclerview_item_mine_store_item_detail_normal,parent,false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
        if(holder instanceof MineStoreItemDetailHeaderViewHoder){

        }else if(holder instanceof  MineStoreItemDetailNormalViewHoder){
            ((MineStoreItemDetailNormalViewHoder)holder).number.setText(String.valueOf(position));
        }
    }

    @Override
    public int getItemCount() {
        return 20;
    }


    public final class MineStoreItemDetailHeaderViewHoder extends RecyclerView.ViewHolder{
        public MineStoreItemDetailHeaderViewHoder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this,itemView);
        }
    }
    public final class MineStoreItemDetailNormalViewHoder extends RecyclerView.ViewHolder{
        @BindView(R.id.tv_item_mine_store_item_detail_number)
        TextView number; //编号

        public MineStoreItemDetailNormalViewHoder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


    public void setListener(RecyclerViewItemClickListener listener){
        this.mListener = listener;
    }


}
