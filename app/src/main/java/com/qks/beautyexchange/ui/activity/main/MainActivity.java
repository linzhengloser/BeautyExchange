package com.qks.beautyexchange.ui.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.base.BaseRecyclerViewAdapter;
import com.qks.beautyexchange.adapter.base.BaseViewHolder;
import com.qks.beautyexchange.ui.activity.base.BaseMultiTypeListActivity;
import com.qks.beautyexchange.ui.activity.find.FindActivity;
import com.qks.beautyexchange.ui.activity.message.MessageActivity;
import com.qks.beautyexchange.ui.activity.mine.MineActivity;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;

import java.util.ArrayList;

import butterknife.OnClick;

public class MainActivity extends BaseMultiTypeListActivity<String> implements View.OnClickListener {

    @OnClick({R.id.ll_main, R.id.ll_find, R.id.ll_message, R.id.ll_mine})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ll_main:
                break;
            //发现
            case R.id.ll_find:
                readyGo(FindActivity.class);
                break;
            //消息
            case R.id.ll_message:
                readyGo(MessageActivity.class);
                break;
            //我的
            case R.id.ll_mine:
                readyGo(MineActivity.class);
                break;
        }

    }


    @Override
    protected void initViewsAndEvents() {
        //必须调用Supper的initViewsAndEvents
        super.initViewsAndEvents();
        for (int i = 0; i < 10; i++) {
            mDatas.add("1");
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected int getMultiItemTypeRecyclerViewLayoutID(int itemType) {
        return itemType == 1 ? R.layout.recyclerview_item_main_what_hot : R.layout.recyclerview_item_main_simple;
    }

    @Override
    protected int getRecyclerViewItemViewType(int position, String data) {
        return position == 3 ? 1 : 0;
    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected void baseRecyclerViewAdapterConvert(BaseViewHolder holder, String data) {
        ArrayList<String> datas = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            datas.add("1");
        }
        
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        if (holder.getConverViewPosition() == 3) {
            holder.setItemAdapter(R.id.rv_item_main_what_hot_horizontal_images, linearLayoutManager, new BaseRecyclerViewAdapter(mContext, datas, R.layout.recyclerview_item_main_what_hot_horizontal) {
                @Override
                public void convert(BaseViewHolder holder, Object data) {

                }
            });
        } else {
            holder.setItemAdapter(R.id.rv_item_main_simple_horizontal_images, linearLayoutManager, new BaseRecyclerViewAdapter(mContext, datas, R.layout.recyclerview_item_main_simple_horizontal) {
                @Override
                public void convert(BaseViewHolder holder, Object data) {

                }
            });
        }
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
    }

    @Override
    protected void onEventComming(EventCenter<?> eventCenter) {

    }

    @Override
    protected void getBundleExtras(Bundle extras) {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected boolean isApplyStatusBarTranslucency() {
        return true;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {
        Toast.makeText(mContext, "onNetworkConnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onNetworkDisConnected() {
        Toast.makeText(mContext, "onNetworkDisConnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(() -> mPullRecyclerView.onRefreshCompleted(),2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
        },3000);
    }
}
