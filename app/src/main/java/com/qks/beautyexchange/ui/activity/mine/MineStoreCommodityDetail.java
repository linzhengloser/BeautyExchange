package com.qks.beautyexchange.ui.activity.mine;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.mine.MineStoreCommodityRecyclerViewAdapter;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 *  商城界面的商品详情
 * Created by admin on 2016/3/16.
 */
public class MineStoreCommodityDetail extends BaseActivity{

    @Bind(R.id.rv_mine_store_commodity_detail)
    RecyclerView rvMineStoreCommodityDetail;

    private MineStoreCommodityRecyclerViewAdapter mAdapter;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
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
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mine_store_commodity_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        mAdapter = new MineStoreCommodityRecyclerViewAdapter(mContext);
        rvMineStoreCommodityDetail.setLayoutManager(new LinearLayoutManager(mContext));
        rvMineStoreCommodityDetail.setAdapter(mAdapter);
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
