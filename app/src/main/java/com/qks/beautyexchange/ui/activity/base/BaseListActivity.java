package com.qks.beautyexchange.ui.activity.base;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.ui.view.recyclerview.PullRecyclerView;

import butterknife.BindView;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Created by admin on 2016/4/14.
 */
public abstract class BaseListActivity extends BaseActivity implements PullRecyclerView.OnRecyclerViewRefreshListener{


    @BindView(R.id.common_pullRecyclerView)
    protected PullRecyclerView mPullRecyclerView;

    protected Items mDatas;

    protected MultiTypeAdapter mAdapter;

    @Override
    protected void initViewsAndEvents() {
        mDatas = new Items();
        mAdapter = new MultiTypeAdapter();
        //使用全局的 MultiTypePool，如果在registerMultiType方法中使用了mAdapter.register(class,itemviewbinder),那么优先使用register的。
        mAdapter.applyGlobalMultiTypePool();
        registerMultiType();
        mPullRecyclerView.setLayoutManager(getRecyclerViewLayoutManager());
        mPullRecyclerView.setAdapter(mAdapter);
        mAdapter.setItems(mDatas);
        mPullRecyclerView.setOnRefreshListener(this);
    }

    @Override
    protected View getLoadingTargetView() {
        return mPullRecyclerView.getRecyclerView();
    }

    protected void registerMultiType(){};

    protected abstract RecyclerView.LayoutManager getRecyclerViewLayoutManager();

}
