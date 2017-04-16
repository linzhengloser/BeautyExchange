package com.qks.beautyexchange.ui.fragment.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.mine.MineStoreRecyclerViewAdapter;
import com.qks.beautyexchange.ui.fragment.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;


/**
 * Created by admin on 2016/3/11.
 */
public class MineStoreTabFragment extends BaseFragment{

    @BindView(R.id.rv_mine_store)
    RecyclerView rvMineStore;


    private MineStoreRecyclerViewAdapter mAdapter;

    @Override
    protected void onUserInvisible() {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    protected void onFirstUserVisible() {
    }

    @Override
    protected void onFirstUserInVisible() {

    }

    @Override
    protected boolean isBindEventBusHere() {
        return false;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_mine_store;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {
        List<String> datas = new ArrayList<String>();
        for(int i = 0; i < 10 ; i++){
            datas.add("1");
        }
        mAdapter = new MineStoreRecyclerViewAdapter(mContext,datas,R.layout.recyclerview_item_mine_store);
        rvMineStore.setLayoutManager(new LinearLayoutManager(mContext));
        rvMineStore.setAdapter(mAdapter);
    }

}
