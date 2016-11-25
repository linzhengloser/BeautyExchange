package com.qks.mylibrary.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;

import com.qks.mylibrary.utils.NetUtils;

import java.util.ArrayList;



public class NetStateReceiver extends BroadcastReceiver {
    public final static String CUSTOM_ANDROID_NET_CHANGE_ACTION = "com.qks.beautyexchange.net.CONNECTIVITY_CHANGE";

    private final static String ANDROID_NET_CHANGE_ACTION = "android.net.conn.CONNECTIVITY_CHANGE";
//    private final static String TAG = NetStateReceiver.class.getSimpleName();
    private static boolean isNetAvailable = false;

    private static BroadcastReceiver mBroadcastReceiver;

    private static NetUtils.NetType mNetType;

    private static ArrayList<NetChangeObserver> mNetChangeObservers = new ArrayList<NetChangeObserver>();

    public static BroadcastReceiver getReceiver() {
        if (null == mBroadcastReceiver) {
            synchronized (NetStateReceiver.class) {
                if (null == mBroadcastReceiver) {
                    mBroadcastReceiver = new NetStateReceiver();
                }
            }
        }
        return mBroadcastReceiver;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        mBroadcastReceiver = NetStateReceiver.this;
        if (intent.getAction().equalsIgnoreCase(ANDROID_NET_CHANGE_ACTION)
                || intent.getAction().equalsIgnoreCase(
                CUSTOM_ANDROID_NET_CHANGE_ACTION)) {
            if (!NetUtils.isNetworkAvailable(context)) {
                isNetAvailable = false;
            } else {
                isNetAvailable = true;
                mNetType = NetUtils.getAPNType(context);
            }
            notifyObserver();
        }

    }

    /**
     * 注册此广播
     *
     * @param context
     */
    public static void registerNetworkStateReceiver(Context context) {
        IntentFilter filter = new IntentFilter();
        filter.addAction(ANDROID_NET_CHANGE_ACTION);
        filter.addAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        context.getApplicationContext().registerReceiver(getReceiver(), filter);
    }

    /**
     * 手动发一条广播
     *
     * @param context
     */
    public static void checkNetworkState(Context context) {
        Intent intent = new Intent();
        intent.setAction(CUSTOM_ANDROID_NET_CHANGE_ACTION);
        context.sendBroadcast(intent);
    }

    /**
     * 注销掉网络广播
     * @param context
     */
    public static void unRegisterNetworkStateReceiver(Context context) {
        if (mBroadcastReceiver != null) {
            context.getApplicationContext().unregisterReceiver(
                    mBroadcastReceiver);
        }
    }

    public static boolean isNetworkAvailable() {
        return isNetAvailable;
    }

    ;

    public static NetUtils.NetType getAPNType() {
        return mNetType;
    }

    ;

    public static void registerObserver(NetChangeObserver observer) {
        if (null == mNetChangeObservers) {
            mNetChangeObservers = new ArrayList<NetChangeObserver>();
        }
        mNetChangeObservers.add(observer);
    }

    public static void removeRegisterObserver(NetChangeObserver observer) {
        if (null == mNetChangeObservers) {
            mNetChangeObservers = new ArrayList<NetChangeObserver>();
        }
        mNetChangeObservers.remove(observer);
    }

    private void notifyObserver() {
        if (!mNetChangeObservers.isEmpty()) {
            for (int i = 0,s = mNetChangeObservers.size(); i < s; i++) {
                NetChangeObserver observer = mNetChangeObservers.get(i);
                if (observer != null) {
                    if (isNetworkAvailable()) {
                        observer.onNetConnected(mNetType);
                    } else {
                        observer.onNetDisConnect();
                    }
                }
            }
        }
    }

}
