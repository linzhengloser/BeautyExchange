package com.qks.beautyexchange.ui.fragment.mine;

import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.ui.fragment.base.BaseFragment;
import com.qks.mylibrary.utils.EventCenter;

/**
 *
 * 我的界面的我的Tab
 * Created by admin on 2016/3/11.
 */
public class MineMyTabFragment extends BaseFragment{
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
        return R.layout.fragment_mine_my;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    protected void initViewsAndEvents() {

    }

    @Override
    protected void onEventComming(EventCenter eventCenter) {

    }
}
