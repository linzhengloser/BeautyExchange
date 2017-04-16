package com.qks.beautyexchange.ui.fragment.base;

import com.qks.beautyexchange.ui.activity.base.BaseView;
import com.qks.mylibrary.ui.fragment.BaseLazyFragment;

public abstract class BaseFragment extends BaseLazyFragment implements BaseView {

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
