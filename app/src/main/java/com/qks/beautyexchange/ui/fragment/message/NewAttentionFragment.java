package com.qks.beautyexchange.ui.fragment.message;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qks.beautyexchange.api.entity.message.Attention;
import com.qks.beautyexchange.application.Constant;
import com.qks.beautyexchange.ui.fragment.base.BaseListFragment;
import com.qks.beautyexchange.ui.view.multitype.message.NewAttentionItemViewBinder;

/**
 * 新关注
 * Created by admin on 2016/3/9.
 */
public class NewAttentionFragment extends BaseListFragment {

    @Override
    protected void onUserInvisible() {
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {
        showLoadingView(Constant.Hint.LOADING_HINT);
        new Handler().postDelayed(() -> {
            for (int i = 0; i<10 ;i++){
                mDatas.add(new Attention());
            }
            mAdapter.notifyDataSetChanged();
            hideLoading();
        },3000);
    }

    @Override
    protected void onFirstUserInVisible() {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }


    @Override
    protected void registerMultiType() {
        mAdapter.register(Attention.class,new NewAttentionItemViewBinder());
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }


    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> mPullRecyclerView.setRefreshComplete(),2000);
    }

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(() -> {
            for (int i = 0; i<10 ;i++){
                mDatas.add(new Attention());
            }
            mAdapter.notifyDataSetChanged();
            mPullRecyclerView.setLoadMoreComplete();
        },3000);
    }
}
