package com.qks.beautyexchange.ui.fragment.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.ui.view.recyclerview.PullRecyclerView;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * 封装公共的只有一种ItemType的BaseListFragment
 * Created by admin on 2016/4/6.
 */
public abstract class BaseListFragment extends BaseFragment implements PullRecyclerView.OnRecyclerViewRefreshListener{

    @BindView(R.id.common_pullRecyclerView)
    protected PullRecyclerView mPullRecyclerView;

    protected Items mDatas;

    protected MultiTypeAdapter mAdapter;

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.common_list_fragment;
    }

    @Override
    protected View getLoadingTargetView() {
        return mPullRecyclerView.getRecyclerView();
    }

    @Override
    protected void initViewsAndEvents() {
        mDatas = new Items();
        mAdapter = new MultiTypeAdapter();
        registerMultiType();
        mPullRecyclerView.setLayoutManager(getRecyclerViewLayoutManager());
        mPullRecyclerView.setAdapter(mAdapter);
        mAdapter.setItems(mDatas);
        mPullRecyclerView.setOnRefreshListener(this);
    }

    protected abstract void registerMultiType();

    protected abstract RecyclerView.LayoutManager getRecyclerViewLayoutManager();

}
