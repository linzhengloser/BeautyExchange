package com.qks.mylibrary.utils;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * SharedPreference工具类
 * Created by admin on 2016/3/30.
 */
public class SharedPreferenceUtils {

    public static final String SHARED_NAME;

    static {
        SHARED_NAME = "shared_name";
    }

    public static <T> void put(Context context, String key, T value) {
        SharedPreferences sp = context.getSharedPreferences(SHARED_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        if (value == null) {
            //如果value为null 以string的方式存储
            editor.putString(key, null);
        } else {
            if (value.getClass() == Boolean.class) {
                editor.putBoolean(key, (Boolean) value);
            } else if (value.getClass() == Float.class) {
                editor.putFloat(key, (Float) value);
            } else if (value.getClass() == Integer.class) {
                editor.putInt(key, (Integer) value);
            } else if (value.getClass() == Long.class) {
                editor.putLong(key, (Long) value);
            } else if (value.getClass() == String.class) {
                editor.putString(key, (String) value);
            } else {
                throw new RuntimeException("the put value type can't support.");
            }
        }
        SharedPreferencesCompat.apply(editor);
    }

    public static String get(Context context, String key,String defaultValue){
        return context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).getString(key,defaultValue);
    }

    public static boolean get(Context context, String key,boolean defaultValue){
        return context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).getBoolean(key,defaultValue);
    }

    public static float get(Context context, String key,float defaultValue){
        return context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).getFloat(key,defaultValue);
    }

    public static int get(Context context, String key,int defaultValue){
        return context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).getInt(key,defaultValue);
    }

    public static long get(Context context, String key,long defaultValue){
        return context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).getLong(key,defaultValue);
    }

    public static void remove(Context context,String key){
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    public static void clear(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences(SHARED_NAME,Context.MODE_PRIVATE).edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 兼容部分版本没有apply方法
     */
    public static class SharedPreferencesCompat {

        private static final Method sApplyMethod = findApplyMethod();

        private static Method findApplyMethod() {
            Class clazz = SharedPreferences.Editor.class;
            try {
                return clazz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        public static void apply(SharedPreferences.Editor editor) {

            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                }
                return;
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
            editor.commit();
        }


    }


}
