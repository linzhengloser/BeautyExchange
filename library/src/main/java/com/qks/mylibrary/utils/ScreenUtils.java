package com.qks.mylibrary.utils;

import android.content.Context;
import android.content.res.Resources;

/**
 * 屏幕工具类
 * Created by admin on 2016/3/23.
 */
public class ScreenUtils {

    public final static float DENSITY = Resources.getSystem().getDisplayMetrics().density;

    private ScreenUtils() {
    }

    /**
     * 获取屏幕的Width
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    /**
     * 获取屏幕的Height
     *
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }


    /**
     * px 转 dp
     * @param pixel
     * @return
     */
    public static int px2dp(int pixel) {
        return (int) ((pixel - 0.5f) / DENSITY);
    }

    /**
     * dp 转 px
     * @param pixel
     * @return
     */
    public static int dp2px(int pixel) {
        return (int) ((pixel - 0.5f) / DENSITY);
    }


}
