package com.qks.beautyexchange.adapter.base;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by admin on 2016/4/11.
 */
public abstract class BaseMultiTypeItemRecyclerViewAdapter<T> extends BaseRecyclerViewAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;


    public BaseMultiTypeItemRecyclerViewAdapter(Context context, List datas, MultiItemTypeSupport multiItemTypeSupport) {
        super(context, datas, -1);
        this.mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position,mDatas.get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        BaseViewHolder viewHolder = BaseViewHolder.get(mContext,null,parent,layoutId,-1);
        setListener(parent,viewHolder,viewType);
        return viewHolder;
    }
}
