package com.qks.beautyexchange.application;

import android.app.Application;

import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.qks.beautyexchange.BuildConfig;
import com.qks.mylibrary.receiver.NetStateReceiver;
import com.squareup.leakcanary.LeakCanary;

/**
 * Created by admin on 2016/3/8.
 */
public class MyApplication extends Application {
    private static final String TAG  ="BeautyExchange";

    private static MyApplication sInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        //初始化LeakCannary,用来检测内存泄漏的
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        LeakCanary.install(this);
        //注册网络监听的广播
        NetStateReceiver.registerNetworkStateReceiver(this);
        //初始化Logger
        LogLevel logLevel;
        if(BuildConfig.DEBUG) {
            logLevel = LogLevel.FULL;
        }
        else {
            logLevel = LogLevel.NONE;
        }
        //初始化Logger
        Logger.init(TAG).setMethodCount(2).setMethodCount(2).setLogLevel(logLevel);
    }


    public static MyApplication getInstance(){
        return sInstance;
    }


}
