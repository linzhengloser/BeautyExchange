package com.qks.beautyexchange.ui.activity.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.api.entity.main.Animal;
import com.qks.beautyexchange.api.entity.main.MainAdvertisementItem;
import com.qks.beautyexchange.api.entity.main.MainNormalItem;
import com.qks.beautyexchange.ui.activity.base.BaseListActivity;
import com.qks.beautyexchange.ui.activity.find.FindActivity;
import com.qks.beautyexchange.ui.activity.message.MessageActivity;
import com.qks.beautyexchange.ui.activity.mine.MineActivity;
import com.qks.mylibrary.utils.NetUtils;
import com.qks.mylibrary.utils.ToastUtils;

import butterknife.OnClick;

public class MainActivity extends BaseListActivity implements View.OnClickListener {

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
            if(i == 3 || i == 6 ){
                mDatas.add(new MainAdvertisementItem());
            }else{
                mDatas.add(new MainNormalItem());
            }
        }
        mAdapter.notifyDataSetChanged();

        Animal animal = Animal.create(1,"大象");
        ToastUtils.showToast(mContext,animal.toString());

    }

    @Override
    protected RecyclerView.LayoutManager getRecyclerViewLayoutManager() {
        return new LinearLayoutManager(mContext);
    }

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return true;
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
        new Handler().postDelayed(() -> mPullRecyclerView.setRefreshComplete(),2000);
    }

    @Override
    public void onLoadMore() {
        new Handler().postDelayed(() -> {
            for (int i = 0; i < 10; i++) {
                if(i == 3 || i == 6 ){
                    mDatas.add(new MainAdvertisementItem());
                }else{
                    mDatas.add(new MainNormalItem());
                }
            }
            mAdapter.notifyDataSetChanged();
            mPullRecyclerView.setLoadMoreComplete();
        },3000);
    }
}
