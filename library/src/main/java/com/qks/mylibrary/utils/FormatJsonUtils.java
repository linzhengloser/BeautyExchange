package com.qks.mylibrary.utils;

import com.google.gson.Gson;

/**
 * Created by admin on 2016/5/10.
 */
public class FormatJsonUtils {

    public static final Gson sGson;

    static{
        sGson = new Gson();
    }

    /**
     * 将一个Json字符串转换成一个对象
     * @param json
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fromJson(String json,Class<T> clazz){
        return sGson.fromJson(json,clazz);
    }


    /**
     * 将一个对象转换成一个字符串
     * @param obj
     * @return
     */
    public static String toJson(Object obj){
        return sGson.toJson(obj);
    }



}
