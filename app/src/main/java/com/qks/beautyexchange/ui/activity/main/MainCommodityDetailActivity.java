package com.qks.beautyexchange.ui.activity.main;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.main.MainItemDetailRecyclerViewAdapter;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * 首页的商品详情
 * Created by admin on 2016/3/13.
 */
public class MainCommodityDetailActivity extends BaseActivity {


    @Bind(R.id.rv_main_item_detail)
    RecyclerView rvMainItemDetail;

    private MainItemDetailRecyclerViewAdapter mAdapter;


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
        return R.layout.activity_main_commodity_detail;
    }

    @Override
    protected void initViewsAndEvents() {
        mAdapter = new MainItemDetailRecyclerViewAdapter(mContext);
        rvMainItemDetail.setLayoutManager(new LinearLayoutManager(mContext));
        rvMainItemDetail.setAdapter(mAdapter);
    }

    @Override
    protected View getLoadingTargetView() {
        return rvMainItemDetail;
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
