package com.qks.beautyexchange.ui.activity.base;

import com.qks.beautyexchange.adapter.base.BaseMultiTypeItemRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.adapter.base.MultiItemTypeSupport;

import java.util.ArrayList;

/**
 * Created by admin on 2016/4/14.
 */
public abstract class BaseMultiTypeListActivity<T> extends BaseListActivity<T> {

    @Override
    protected void initViewsAndEvents() {
        mDatas = new ArrayList<T>(0);
        mAdapter = new BaseMultiTypeItemRecyclerViewAdapter<T>(mContext, mDatas, new MultiItemTypeSupport<T>() {
            @Override
            public int getLayoutId(int itemType) {
                return getMultiItemTypeRecyclerViewLayoutID(itemType);
            }

            @Override
            public int getItemViewType(int position, T data) {
                return getRecyclerViewItemViewType(position,data);
            }
        }) {
            @Override
            public void convert(BaseViewHolder holder, T data) {
                baseRecyclerViewAdapterConvert(holder,data);
            }
        };
        mPullRecyclerView.setLayoutManager(getRecyclerViewLayoutManager());
        mPullRecyclerView.setOnRefreshListener(this);
        mPullRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 返回
     * K --- V
     * ItemType --- LayoutId
     * @return
     */
    protected abstract int getMultiItemTypeRecyclerViewLayoutID(int itemType);

    protected abstract int getRecyclerViewItemViewType(int position,T data);

    @Override
    protected int getRecyclerViewItemLayoutID() {
        return -1;
    }
}
