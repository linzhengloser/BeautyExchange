package com.qks.beautyexchange.adapter.listener;

import android.view.View;
import android.view.ViewGroup;

/**
 *因为RecyclerView本身不支持OnItemClik事件,所以在这里之定义一个点击事件
 * Created by admin on 2016/3/14.
 */
public interface RecyclerViewItemClickListener<T> {

    void onItemClick(ViewGroup parent,View view,T t, int position);

    void onItemLongClick(ViewGroup parent,View view,T t, int position);

}
