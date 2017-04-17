package com.qks.beautyexchange.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qks.beautyexchange.R;
import com.qks.mylibrary.ui.activity.BaseAppCompatActivity;


public abstract class BaseActivity extends BaseAppCompatActivity implements BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            //使用 SystemBarTint 这个库设置状态栏的颜色
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    /**
     * 是否需要透明状态栏
     */
    protected abstract boolean isApplyKitKatTranslucency();

    @Override
    public void showLoadingView(String msg) {
        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, "");
    }

    @Override
    public void showLoadingErrorView(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetworErrorView(String msg) {
        toggleNetworkError(true, null);
    }

    @Override
    public void showEmptyView(String msg) {
        toggleShowEmpty(true, msg, null);
    }
}
