package com.qks.mylibrary.utils;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Build;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 部分手机需要隐藏SmartBar
 */
public class SmartBarUtils {

	/**
	 * 隐藏
	 * @param decorView
	 */
	public static void hide(View decorView) {
		if (!hasSmartBar())
			return;
		Class<?>[] arrayOfClass = new Class[1];
		arrayOfClass[0] = Integer.TYPE;
		try {
			Method localMethod = View.class.getMethod("setSystemUiVisibility",
					arrayOfClass);
			Field localField = View.class
					.getField("SYSTEM_UI_FLAG_HIDE_NAVIGATION");
			Object[] arrayOfObject = new Object[1];
			arrayOfObject[0] = localField.get(null);
			localMethod.invoke(decorView, arrayOfObject);
			return;
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 隐藏
	 * @param context
	 * @param window
	 * @param smarBarHeight
     */
	public static void hide(Context context, Window window, int smarBarHeight) {
		if (!hasSmartBar()) {
			return;
		}
		if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
			return;
		}
		window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.addFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);
		int statusBarHeight = getStatusBarHeight(context);
		window.getDecorView().setPadding(0, statusBarHeight, 0, -smarBarHeight);
	}

	/**
	 * 判断是否有SmartBar
	 * @return
	 */
	public static boolean hasSmartBar() {
		try {
			Method method = Class.forName("android.os.Build").getMethod(
					"hasSmartBar");
			return ((Boolean) method.invoke(null)).booleanValue();
		} catch (Exception e) {
			e.printStackTrace();
		}

		if (Build.DEVICE.equals("mx2")) {
			return true;
		} else if (Build.DEVICE.equals("mx") || Build.DEVICE.equals("m9")) {
			return true;
		}
		return true;
	}

	/**
	 *
	 * 
	 * @param context
	 * @return
	 */
	public static int getStatusBarHeight(Context context) {
		int result = 0;
		int resourceId = context.getResources().getIdentifier(
				"status_bar_height", "dimen", "android");
		if (resourceId > 0) {
			result = context.getResources().getDimensionPixelSize(resourceId);
		}
		return result;
	}

}
