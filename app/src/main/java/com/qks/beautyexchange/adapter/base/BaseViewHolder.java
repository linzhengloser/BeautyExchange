package com.qks.beautyexchange.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.qks.beautyexchange.utils.ImageLoaderUtils;
import com.zhy.autolayout.utils.AutoUtils;

/**
 * 实现公共ViewHolder封装,
 * Created by admin on 2016/3/14.
 */
public class BaseViewHolder extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    private View mConverView;

    private Context mContext;

    private int mPosition;

    private int mLayoutId;

    public BaseViewHolder(Context context ,View itemView,int position) {
        super(itemView);
        AutoUtils.auto(itemView);
        this.mViews = new SparseArray<View>();
        this.mConverView = itemView;
        this.mContext = context;
        this.mPosition = position;
        mConverView.setTag(this);
    }


    public static BaseViewHolder get(Context context, View converView, ViewGroup parent,int layoutId,int position){
        if(converView == null){
            View itemView = LayoutInflater.from(context).inflate(layoutId,parent,false);
            BaseViewHolder holder = new BaseViewHolder(context,itemView,position);
            holder.mLayoutId = layoutId;
            return holder;
        }else{
            BaseViewHolder holder = (BaseViewHolder) converView.getTag();
            holder.mPosition = position;
            return holder;
        }
    }

    public int getConverViewPosition(){
        return mPosition;
    }


    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if(view == null){
            view =mConverView.findViewById(viewId);
            mViews.put(viewId,view);
        }
        return (T)view;
    }



    public View getConverView(){
        return mConverView;
    }

    public void updatePosition(int position){
        this.mPosition = position;
    }

    protected int getLayoutId(){return mLayoutId;}

    public BaseViewHolder setText(int viewId,String text){
        TextView textview = getView(viewId);
        textview.setText(text);
        return this;
    }

    public BaseViewHolder setImage(int viewId,String imageUrl){
        ImageLoaderUtils.getInstance().loadImage(mContext,(ImageView)getView(viewId),imageUrl);
        return this;
    }

    public BaseViewHolder setOnClickListener(int viewId, View.OnClickListener listener){
        getView(viewId).setOnClickListener(listener);
        return this;
    }

    public BaseViewHolder setItemAdapter(int viewId, RecyclerView.LayoutManager layoutManager, BaseRecyclerViewAdapter adapter){
        RecyclerView recyclerView = getView(viewId);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return this;
    }

    public BaseViewHolder setMultiTypeItemAdapter(int viewId, RecyclerView.LayoutManager layoutManager, BaseMultiTypeItemRecyclerViewAdapter adapter){
        RecyclerView recyclerView = getView(viewId);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        return this;
    }

}
