package com.qks.beautyexchange.ui.activity.base;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseMultiTypeItemRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.adapter.base.MultiItemTypeSupport;
import com.qks.beautyexchange.ui.view.recyclerview.PullRecyclerView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/4/14.
 */
public abstract class BaseMultiTypeListActivity<T> extends BaseListActivity<T> {

    @Override
    protected void initViewsAndEvents() {

        mPullRecyclerView = (PullRecyclerView) findViewById(R.id.common_pullRecyclerView);

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
     * 返回 ItemType 对应的 LayoutId
     * @param itemType ItemType
     * @return LayoutId
     */
    protected abstract int getMultiItemTypeRecyclerViewLayoutID(int itemType);

    /**
     * 返回 ItemType
     * @param position position
     * @param data 数据
     * @return
     */
    protected abstract int getRecyclerViewItemViewType(int position,T data);

    @Override
    protected int getRecyclerViewItemLayoutID() {
        return -1;
    }
}
