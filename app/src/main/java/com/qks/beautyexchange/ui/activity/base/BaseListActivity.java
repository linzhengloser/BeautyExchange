package com.qks.beautyexchange.ui.activity.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.adapter.base.BaseRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.ui.view.recyclerview.PullRecyclerView;

import java.util.ArrayList;

/**
 * Created by admin on 2016/4/14.
 */
public abstract class BaseListActivity<T>  extends BaseActivity implements PullRecyclerView.OnRecyclerViewRefreshListener{

    protected PullRecyclerView mPullRecyclerView;

    protected ArrayList<T> mDatas;

    protected BaseRecyclerViewAdapter<T> mAdapter;

    @Override
    protected void initViewsAndEvents() {
        mDatas = new ArrayList<T>(0);
        mAdapter = new BaseRecyclerViewAdapter<T>(mContext, mDatas,getRecyclerViewItemLayoutID()) {
            @Override
            public void convert(BaseViewHolder holder, T data) {
                baseRecyclerViewAdapterConvert(holder,data);
            }
        };
        mPullRecyclerView.setLayoutManager(getRecyclerViewLayoutManager());
        mPullRecyclerView.setOnRefreshListener(this);
        mPullRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected View getLoadingTargetView() {
        return mPullRecyclerView;
    }

    protected abstract int getRecyclerViewItemLayoutID();

    protected abstract RecyclerView.LayoutManager getRecyclerViewLayoutManager();

    protected abstract void baseRecyclerViewAdapterConvert(BaseViewHolder holder,T data);


}
