package com.qks.beautyexchange.ui.view.recyclerview;

import android.content.Context;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseRecyclerViewAdapter;

/**
 * 封装公共的RecyclerView
 * Created by admin on 2016/4/5.
 */
public class PullRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView mRecyclerView;

    private SwipeRefreshLayout mSwipeRefreshLayout;

    private OnRecyclerViewRefreshListener mRefreshListener;

    private BaseRecyclerViewAdapter<?> mAdapter;


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
        mSwipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.common_swipeRefreshLayout);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        //刷新和加载跟多回调
        mSwipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 设置刷新
     */
    public void setRefreshing() {
        mRecyclerView.post(() -> mSwipeRefreshLayout.setRefreshing(true));
    }

    /**
     * 刷新完成
     */
    public void onRefreshCompleted() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    /**
     * 加载完成
     * @param status
     */
    public void onLoadMoreCompleted(int status){

    }

    @Override
    public void onRefresh() {
        mRefreshListener.onRefresh();
    }

    public void setOnRefreshListener(OnRecyclerViewRefreshListener listener) {
        this.mRefreshListener = listener;
    }

    public void setAdapter(BaseRecyclerViewAdapter<?> adapter) {
        this.mAdapter = adapter;
        mRecyclerView.setAdapter(mAdapter);
    }

    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.mRecyclerView.setLayoutManager(layoutManager);
    }

    public interface OnRecyclerViewRefreshListener {

        void onRefresh();

        void onLoadMore();

    }
}
