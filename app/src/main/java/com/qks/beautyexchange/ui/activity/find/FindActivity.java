package com.qks.beautyexchange.ui.activity.find;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;

import com.qks.beautyexchange.R;
import com.qks.beautyexchange.ui.activity.base.BaseActivity;
import com.qks.beautyexchange.ui.view.CardDataItem;
import com.qks.beautyexchange.ui.view.CardSlidePanel;
import com.qks.mylibrary.utils.NetUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 探寻
 * Created by admin on 2016/3/22.
 */
public class FindActivity extends BaseActivity {


    @BindView(R.id.csp_search_card_slide_panel)
    CardSlidePanel cspSearch;

    @Override
    protected boolean isApplyKitKatTranslucency() {
        return false;
    }

    @Override
    protected void getBundleExtras(Bundle extras) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        try {
            mediaPlayer.setDataSource("");
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return R.layout.activity_find;
    }

    @Override
    protected void initViewsAndEvents() {
        List<CardDataItem> mCardDataItem = new ArrayList<CardDataItem>();
        for (int i = 0; i < 30; i++) {
            mCardDataItem.add(new CardDataItem());
        }
        cspSearch.fillData(mCardDataItem);
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
    public void allClickListener(View view) {
        finish();
    }


}
