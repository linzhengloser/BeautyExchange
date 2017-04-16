package com.qks.beautyexchange.ui.fragment.message;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.application.Constant;
import com.qks.beautyexchange.ui.fragment.base.BaseListFragment;

/**
 * Created by admin on 2016/3/9.
 */
public class NewPrivateMessageFragment extends BaseListFragment<String> {


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
                mDatas.add("0");
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
    protected int getRecyclerViewItemLayoutID() {
        return R.layout.recyclerview_item_message_new_private_message;
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

    }

    @Override
    public void onLoadMore() {

    }
}
