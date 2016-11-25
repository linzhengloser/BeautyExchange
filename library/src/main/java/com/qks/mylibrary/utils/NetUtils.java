package com.qks.mylibrary.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Locale;

public class NetUtils {
	
	private static final String CONNECTIVITY_SERVICE = Context.CONNECTIVITY_SERVICE;

	public enum NetType {
		WIFI, CMNET, CMWAP, NONE
	}

	/**
	 * 当前网络是否可用
	 * @param context
	 * @return
	 */
	public static boolean isNetworkAvailable(Context context) {
		ConnectivityManager mgr = (ConnectivityManager) context
				.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo[] info = mgr.getAllNetworkInfo();
		if (null != info) {
			for (int i = 0; i < info.length; i++) {
				if (info[i].getState() == NetworkInfo.State.CONNECTED) {
					return true;
				}
			}
		}
		return false;

	}

	/**
	 * 网路是否连接
	 * @param context
	 * @return
     */
	public static boolean isNetworkConnected(Context context){
		if(null != context){
			ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mgr.getActiveNetworkInfo();
			if(null != mNetworkInfo){
				return mNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	/**
	 * 是否是Wifi连接
	 * @param context
	 * @return
     */
	public static boolean isWifiConnected(Context context){
		if(null != context){
			ConnectivityManager mgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mWifNetworkInfo = mgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
			if(null != mWifNetworkInfo){
				return mWifNetworkInfo.isAvailable();
			}
		}
		return false;
	}
	
	public static boolean isMobileConnected(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
			if (mMobileNetworkInfo != null) {
				return mMobileNetworkInfo.isAvailable();
			}
		}
		return false;
	}

	public static int getConnectedType(Context context) {
		if (context != null) {
			ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
			NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
			if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
				return mNetworkInfo.getType();
			}
		}
		return -1;
	}
	
	public static NetType getAPNType(Context context){
		ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
		NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
		if(null == networkInfo){
			return NetType.NONE;
		}
		int nType = networkInfo.getType();
		if(nType == ConnectivityManager.TYPE_MOBILE){
			if(networkInfo.getExtraInfo().toLowerCase(Locale.getDefault()).equals("cmnet")){
				return NetType.CMNET;
			}else{
				return NetType.CMWAP;
			}
		}else if(nType == ConnectivityManager.TYPE_WIFI){
			return NetType.WIFI;
		}
		return NetType.NONE;
	}

}
