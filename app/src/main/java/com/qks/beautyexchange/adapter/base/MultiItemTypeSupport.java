package com.qks.beautyexchange.adapter.base;

/**
 * Created by admin on 2016/4/11.
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int itemType);

    int getItemViewType(int position, T t);
}
