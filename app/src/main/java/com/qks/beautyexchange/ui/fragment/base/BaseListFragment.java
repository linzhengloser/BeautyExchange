package com.qks.beautyexchange.ui.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.ui.view.recyclerview.PullRecyclerView;

import java.util.ArrayList;

import butterknife.Bind;

/**
 * 封装公共的只有一种ItemType的BaseListFragment
 * Created by admin on 2016/4/6.
 */
public abstract class BaseListFragment<T> extends BaseFragment implements PullRecyclerView.OnRecyclerViewRefreshListener{

    @Bind(R.id.common_pullRecyclerView)
    protected PullRecyclerView mPullRecyclerView;

    protected BaseRecyclerViewAdapter<T> mAdapter;

    protected ArrayList<T> mDatas;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.common_list_fragment;
    }

    @Override
    protected View getLoadingTargetView() {
        return mPullRecyclerView;
    }

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


    protected abstract int getRecyclerViewItemLayoutID();

    protected abstract RecyclerView.LayoutManager getRecyclerViewLayoutManager();

    protected abstract void baseRecyclerViewAdapterConvert(BaseViewHolder holder,T data);

}
