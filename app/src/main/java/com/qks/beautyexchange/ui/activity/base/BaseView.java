package com.qks.beautyexchange.ui.activity.base;

/**
 * 封装MVP 中的 V
 */
public interface BaseView {

	/**
	 * 加载中
	 * @param msg
     */
	void showLoading(String msg);

	/**
	 * 隐藏加载
	 */
	void hideLoading();

	/**
	 * 加载异常
	 * @param msg
     */
	void showError(String msg);

	/**
	 * 显示异常
	 * @param msg
     */
	void showException(String msg);

	/**
	 * 网络异常
	 */
	void showNetError();

}
