package com.qks.beautyexchange.ui.fragment.message;

import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.application.Constant;
import com.qks.beautyexchange.ui.fragment.base.BaseListFragment;

/**
 * 新关注
 * Created by admin on 2016/3/9.
 */
public class NewAttentionFragment extends BaseListFragment<String> {

    @Override
    protected void onUserInvisible() {
    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {
        showLoadingView(Constant.Hint.LOADING_HINT);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i<10 ;i++){
                    mDatas.add("1");
                }
                mAdapter.notifyDataSetChanged();
                showLoadingErrorView("加载异常");
//                hideLoading();
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
    protected int getRecyclerViewItemLayoutID() {
        return R.layout.recyclerview_item_message_new_attention;
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected void baseRecyclerViewAdapterConvert(BaseViewHolder holder, String data) {

    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mPullRecyclerView.onRefreshCompleted();
            }
        },2000);
    }

    @Override
    public void onLoadMore() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
            }
        },3000);
    }
}
