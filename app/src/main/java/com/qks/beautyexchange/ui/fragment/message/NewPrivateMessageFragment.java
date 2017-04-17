package com.qks.beautyexchange.ui.fragment.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qks.beautyexchange.api.entity.message.PrivateMessage;
import com.qks.beautyexchange.application.Constant;
import com.qks.beautyexchange.ui.fragment.base.BaseListFragment;
import com.qks.beautyexchange.ui.view.multitype.message.NewPrivateMessageItemViewBinder;

/**
 * Created by admin on 2016/3/9.
 */
public class NewPrivateMessageFragment extends BaseListFragment {


    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {
        showLoadingView(Constant.Hint.LOADING_HINT);
        mPullRecyclerView.postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                mDatas.add(new PrivateMessage());
            }
            hideLoading();
            mAdapter.notifyDataSetChanged();
        }, 3000);
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
        mAdapter.register(PrivateMessage.class,new NewPrivateMessageItemViewBinder());
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onRefresh() {
        mPullRecyclerView.postDelayed(() -> {
            mPullRecyclerView.setRefreshComplete();
        },3000);
    }

    @Override
    public void onLoadMore() {
        mPullRecyclerView.postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                mDatas.add(new PrivateMessage());
            }
            mAdapter.notifyDataSetChanged();
        },3000);
    }
}
