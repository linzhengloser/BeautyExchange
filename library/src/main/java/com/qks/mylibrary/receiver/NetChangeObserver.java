package com.qks.mylibrary.receiver;


import com.qks.mylibrary.utils.NetUtils;

public interface NetChangeObserver {
	
	/**
	 * 网络连接成功
	 * @param netType
	 */
	void onNetConnected(NetUtils.NetType netType);
	
	/**
	 * 网络连接断开
	 */
	void onNetDisConnect();
	
}
