package com.qks.beautyexchange.ui.activity.mine;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.mine.MineStoreItemDetailRecyclerViewAdapter;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 我的商城详情界面
 * Created by admin on 2016/3/15.
 */
public class MineStoreItemDetailActivity extends BaseActivity{


    @Bind(R.id.rv_mine_store_item_detail)
    RecyclerView rvMineStoreItemDetail;

    @Bind(R.id.srl_mine_store_detail)
    SwipeRefreshLayout srlMineStoreItemDetail;

    private MineStoreItemDetailRecyclerViewAdapter mAdapter;

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
        return R.layout.activity_mine_store_item_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        srlMineStoreItemDetail.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        srlMineStoreItemDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        srlMineStoreItemDetail.setRefreshing(false);
                    }
                },3000);
            }
        });
        mAdapter = new MineStoreItemDetailRecyclerViewAdapter(mContext);
        rvMineStoreItemDetail.setLayoutManager(new LinearLayoutManager(mContext));
        rvMineStoreItemDetail.setAdapter(mAdapter);

    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void onNetworkConnected(NetUtils.NetType type) {

    }

    @Override
    protected void onNetworkDisConnected() {

    }

    @OnClick({R.id.back})
    public void onClick(View view){
        finish();
    }

}
