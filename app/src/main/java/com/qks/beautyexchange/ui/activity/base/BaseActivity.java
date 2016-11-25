package com.qks.beautyexchange.ui.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qks.beautyexchange.R;
import com.qks.mylibrary.ui.activity.BaseAppCompatActivity;


public abstract class BaseActivity extends BaseAppCompatActivity implements
        BaseView {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isApplyKitKatTranslucency()) {
            setSystemBarTintDrawable(getResources().getDrawable(R.drawable.sr_primary));
        }
    }

    protected abstract boolean isApplyKitKatTranslucency();

    @Override
    public void showError(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showException(String msg) {
        toggleShowError(true, msg, null);
    }

    @Override
    public void showNetError() {
        toggleNetworkError(true, null);
    }

    @Override
    public void showLoading(String msg) {
        toggleShowLoading(true, null);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }

}
