package com.qks.mylibrary.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.io.File;

/**
 * Created by admin on 2016/3/31.
 */
public class AppUtils {

    /**
     * 获取当前应用的版本号
     *
     * @param context
     * @return
     */
    public static int getAppVersionCode(Context context) {
        int result = getAppPackageInfo(context).versionCode;
        return result;
    }

    /**
     * 获取当前应用版本名称
     * @param context
     * @return
     */
    public static String getAppVersionName(Context context){
        String result = getAppPackageInfo(context).packageName;
        return result;
    }

    /**
     * 获取App的名称
     * @param context
     * @return
     */
    public static String getAppName(Context context){
        String result = null;
        String packageName = context.getPackageName();
        ApplicationInfo applicationInfo;
        PackageManager packageManager = context.getPackageManager();
        try {
            applicationInfo = packageManager.getApplicationInfo(packageName,0);
            result = packageManager.getApplicationLabel(applicationInfo).toString();
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 清空当前App 的 cache,database,files和sp目录
     * @param context
     */
    public static void clearAppData(Context context){
        clearAppCache(context);
        clearAppDataBase(context);
        clearAppFiles(context);
        clearAppSharedPreference(context);
    }

    public static void clearAppCache(Context context){
        FileUtils.delete(context.getCacheDir(),true);
    }

    public static void clearAppFiles(Context context){
        FileUtils.delete(context.getFilesDir(),true);
    }

    public static void clearAppSharedPreference(Context context){
        FileUtils.delete(new File("/data/data/"+context.getPackageName()+"/shared_prefs"),true);
    }

    public static void clearAppDataBase(Context context){
        FileUtils.delete(new File("/data/data/"+context.getPackageName()+"/databases"),true);
    }


    /**
     * 获取当前应用的的PackageInfo
     * @param context
     * @return
     */
    public static PackageInfo getAppPackageInfo(Context context) {
        String packageName = context.getPackageName();
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return info;
    }



}
