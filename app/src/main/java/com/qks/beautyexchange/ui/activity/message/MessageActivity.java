package com.qks.beautyexchange.ui.activity.message;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.adapter.message.MessageViewPagerAdapter;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.beautyexchange.ui.fragment.message.NewAttentionFragment;
import com.qks.beautyexchange.ui.fragment.message.NewCommentFragment;
import com.qks.beautyexchange.ui.fragment.message.NewPrivateMessageFragment;
import com.qks.mylibrary.utils.NetUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 消息界面
 * Created by admin on 2016/3/9.
 */
public class MessageActivity extends BaseActivity {


    @BindView(R.id.tl_message_tab)
    TabLayout tlMessageTab;

    @BindView(R.id.vp_message_viewpager)
    ViewPager vpMessageViewpager;

    //标签
    private List<String> tabs;
    //ViewPager适配器
    private MessageViewPagerAdapter mMessageViewPagerAdapter;

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
        return R.layout.activity_message;
    }

    @Override
    protected void initViewsAndEvents() {
        //初始化ViewPager
        initViewPager();
        vpMessageViewpager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
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

    private void initViewPager() {
        List<Fragment> listFragment = new ArrayList<Fragment>();
        listFragment.add(new NewAttentionFragment());
        listFragment.add(new NewCommentFragment());
        listFragment.add(new NewPrivateMessageFragment());
        mMessageViewPagerAdapter = new MessageViewPagerAdapter(getSupportFragmentManager(), listFragment);
        vpMessageViewpager.setOffscreenPageLimit(listFragment.size());
        vpMessageViewpager.setAdapter(mMessageViewPagerAdapter);
        tlMessageTab.setupWithViewPager(vpMessageViewpager);
    }


    @OnClick({R.id.back})
    public void allOnClickListener(View view) {
        finish();
    }


}
