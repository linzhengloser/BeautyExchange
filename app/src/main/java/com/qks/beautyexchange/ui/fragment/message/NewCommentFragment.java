package com.qks.beautyexchange.ui.fragment.message;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qks.beautyexchange.api.entity.message.Comment;
import com.qks.beautyexchange.ui.fragment.base.BaseListFragment;
import com.qks.beautyexchange.ui.view.multitype.message.NewCommentItemViewBinder;

/**
 * 新回复
 * Created by admin on 2016/3/9.
 */
public class NewCommentFragment extends BaseListFragment {


    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {
        showLoadingView("加载中...");
        mPullRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mDatas.add(new Comment());
                }
                hideLoading();
                mAdapter.notifyDataSetChanged();
            }
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
        mAdapter.register(Comment.class,new NewCommentItemViewBinder());
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullRecyclerView.setRefreshComplete();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    mDatas.add(new Comment());
                    mAdapter.notifyDataSetChanged();
                }
            }
        },3000);
    }
}
