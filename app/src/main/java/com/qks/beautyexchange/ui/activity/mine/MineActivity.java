package com.qks.beautyexchange.ui.activity.mine;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.beautyexchange.ui.fragment.mine.MineMyTabFragment;
import com.qks.beautyexchange.ui.fragment.mine.MineStoreTabFragment;
import com.qks.mylibrary.utils.EventCenter;
import com.qks.mylibrary.utils.NetUtils;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Created by admin on 2016/3/10.
 */
public class MineActivity extends BaseActivity {


    @Bind(R.id.fl_mine_frame)
    FrameLayout flMineFrame;
    @Bind(R.id.iv_mine_store_tab)
    ImageView ivStoreTab;//我的选项卡
    @Bind(R.id.iv_mine_my_tab)
    ImageView ivMyTab;//商店选项卡
    @Bind(R.id.tv_mine_my_tab_text)
    TextView tvMyTab;//我的选项卡文字
    @Bind(R.id.tv_mine_store_tab_text)
    TextView tvStoreTab;//商店选项卡文字

    private static final int DEFAULT_SELECTED_TAB = 1;

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
        return true;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.activity_mine;
    }

    @Override
    protected void initViewsAndEvents() {
        switchTab(DEFAULT_SELECTED_TAB);
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


    @OnClick({R.id.iv_mine_my_tab,R.id.iv_mine_store_tab,R.id.back})
    public void onClick(View view){
        switch (view.getId()){
            //我的
            case R.id.iv_mine_my_tab:
                switchTab(1);
                break;
            //商店
            case R.id.iv_mine_store_tab:
                switchTab(2);
                break;
            case R.id.back:
                finish();
                break;
        }
    }

    /**
     * 我的和商店切换
     */
    private void switchTab(int flag){
        Fragment fragment =null;
        if(flag ==1){
            //我的
            fragment = new MineMyTabFragment();
            tvMyTab.setTextColor(getResources().getColor(R.color.black));
            ivMyTab.setImageResource(R.mipmap.mine_tab_selected);
            tvStoreTab.setTextColor(getResources().getColor(R.color.white));
            ivStoreTab.setImageResource(R.mipmap.mine_tab_store_unselected);
        }else if(flag ==2){
            //商店
            fragment = new MineStoreTabFragment();
            tvMyTab.setTextColor(getResources().getColor(R.color.white));
            ivMyTab.setImageResource(R.mipmap.mine_tab_mine_unselected);
            tvStoreTab.setTextColor(getResources().getColor(R.color.black));
            ivStoreTab.setImageResource(R.mipmap.mine_tab_selected);
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.fl_mine_frame,fragment).commit();

    }


}
