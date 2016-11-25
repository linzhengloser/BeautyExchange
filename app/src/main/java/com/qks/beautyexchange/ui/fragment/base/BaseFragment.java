package com.qks.beautyexchange.ui.fragment.base;

import com.qks.beautyexchange.ui.activity.base.BaseView;
import com.qks.mylibrary.ui.fragment.BaseLazyFragment;

public abstract class BaseFragment extends BaseLazyFragment implements BaseView {
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
        toggleShowLoading(true, msg);
    }

    @Override
    public void hideLoading() {
        toggleShowLoading(false, null);
    }
}
