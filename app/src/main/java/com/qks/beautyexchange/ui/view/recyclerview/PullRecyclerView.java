package com.qks.beautyexchange.ui.view.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qks.beautyexchange.R;

import cn.bingoogolapple.refreshlayout.BGANormalRefreshViewHolder;
import cn.bingoogolapple.refreshlayout.BGARefreshLayout;

/**
 * 封装公共的RecyclerView
 * Created by admin on 2016/4/5.
 */
public class PullRecyclerView extends FrameLayout implements BGARefreshLayout.BGARefreshLayoutDelegate {

    private RecyclerView mRecyclerView;

    private BGARefreshLayout mRefreshLayout;

    private OnRecyclerViewRefreshListener mRefreshListener;

    private RecyclerView.Adapter mAdapter;


    public PullRecyclerView(Context context) {
        super(context);
        initViews();
    }

    public PullRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public PullRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initViews();
    }

    private void initViews() {
        LayoutInflater.from(getContext()).inflate(R.layout.common_pull_to_refresh, this, true);
        mRecyclerView = (RecyclerView) findViewById(R.id.common_recyclerView);
        mRefreshLayout = (BGARefreshLayout) findViewById(R.id.common_swipeRefreshLayout);
        //刷新和加载跟多回调
        mRefreshLayout.setDelegate(this);
        mRefreshLayout.setRefreshViewHolder(new BGANormalRefreshViewHolder(getContext(),true));
    }

    /**
     * 设置刷新
     */
    public void setRefreshing() {
        mRecyclerView.post(() -> mRefreshLayout.beginRefreshing());
    }

    /**
     * 设置加载更多
     */
    public void setLoadMoring(){
        mRecyclerView.post(() -> mRefreshLayout.beginLoadingMore());
    }

    /**
     * 刷新完成
     */
    public void setRefreshComplete() {
        mRefreshLayout.endRefreshing();
    }

    /**
     * 加载完成
     */
    public void setLoadMoreComplete(){
        mRefreshLayout.endLoadingMore();
    }


    public void setOnRefreshListener(OnRecyclerViewRefreshListener listener) {
        this.mRefreshListener = listener;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mRecyclerView.setLayoutManager(layoutManager);
    }

    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }

    @Override
    public void onBGARefreshLayoutBeginRefreshing(BGARefreshLayout layout) {
        mRefreshListener.onRefresh();
    }

    @Override
    public boolean onBGARefreshLayoutBeginLoadingMore(BGARefreshLayout layout) {
        mRefreshListener.onLoadMore();
        return true;
    }

    public interface OnRecyclerViewRefreshListener {

        void onRefresh();

        void onLoadMore();

    }
}
