package com.qks.beautyexchange.adapter.base;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qks.beautyexchange.adapter.listener.RecyclerViewItemClickListener;

import java.util.List;

/**
 * 实现简单Adapter封装,复杂使用系统默认Adapter实现
 * Created by admin on 2016/3/14.
 */
public abstract class BaseRecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    protected List<T> mDatas;

    protected Context mContext;

    protected int mLayoutId;

    protected LayoutInflater mInflater;

    protected RecyclerViewItemClickListener mListener;

    public BaseRecyclerViewAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mDatas = datas;
        mLayoutId = layoutId;
        mInflater = LayoutInflater.from(mContext);
    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder viewHolder = BaseViewHolder.get(mContext,null,parent,mLayoutId,-1);
        setListener(parent,viewHolder,viewType);
        return viewHolder;
    }

    protected boolean isEnabled(int viewType){
        return true;
    }

    /**
     * 设置点击事件
     * @param parent
     * @param viewHolder
     * @param viewType
     */
    protected void setListener(final ViewGroup parent,final BaseViewHolder viewHolder,int viewType){
        if(!isEnabled(viewType))
            return;

        viewHolder.getConverView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mListener!=null){
                    int position = getPosition(viewHolder);
                    mListener.onItemClick(parent,v,mDatas.get(position),position);
                }
            }
        });

        viewHolder.getConverView().setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {
                if(mListener!=null){
                    int position = getPosition(viewHolder);
                    mListener.onItemClick(parent,v,mDatas.get(position),position);
                }
                return false;
            }
        });


    }

    /**
     * 获取ViewHolder在RecyclerView中的Position
     * @param viewHolder
     * @return
     */
    protected int getPosition(RecyclerView.ViewHolder viewHolder){
        return viewHolder.getAdapterPosition();
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder,int position) {
        holder.updatePosition(position);
        convert(holder,mDatas.get(position));
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    /**
     * 设置Item点击事件
     * @param listener
     */
    public void setOnItemClickListener(@NonNull RecyclerViewItemClickListener listener) {
        this.mListener = listener;
    }

    public abstract void convert(BaseViewHolder holder, T data);
}
