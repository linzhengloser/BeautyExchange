package com.qks.beautyexchange.adapter.mine;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.zhy.autolayout.utils.AutoUtils;

import butterknife.ButterKnife;

public class MineMyRecyclerViewAdaptr extends RecyclerView.Adapter<MineMyRecyclerViewAdaptr.MineMyViewHolder>{

    private Context mContext;

    public MineMyRecyclerViewAdaptr(Context context){
        this.mContext = context;
    }

    @Override
    public MineMyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MineMyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public final class MineMyViewHolder extends RecyclerView.ViewHolder{
        public MineMyViewHolder(View itemView) {
            super(itemView);
            AutoUtils.auto(itemView);
            ButterKnife.bind(this,itemView);
        }
    }


}
