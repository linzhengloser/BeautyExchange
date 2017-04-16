package com.qks.beautyexchange.ui.activity.base;

/**
 * 封装MVP 中的 V
 */
public interface BaseView {

    /**
     * 加载中
     */
    void showLoadingView(String msg);

    /**
     * 隐藏加载
     */
    void hideLoading();

    /**
     * 加载异常
     */
    void showLoadingErrorView(String msg);

    /**
     * 网络异常
     * @param msg
     */
    void showNetworErrorView(String msg);

    /**
     * 空数据
     */
    void showEmptyView(String msg);

}
