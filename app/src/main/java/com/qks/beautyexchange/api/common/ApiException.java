package com.qks.beautyexchange.api.common;

import java.util.HashMap;
import java.util.Map;

/**
 * 封装公共的异常类
 * Created by admin on 2016/3/23.
 */
public class ApiException extends RuntimeException{

    public static final Map<String,String>EXCEPTION_VALUE;

    static {
        EXCEPTION_VALUE = new HashMap<String,String>();
        EXCEPTION_VALUE.put("1001","系统错误");
    }

    public ApiException(int resultCode){
        this(getApiExceptionMessage(resultCode));
    }

    public ApiException(String errorMessage){
        super(errorMessage);
    }

    public static String getApiExceptionMessage(int code){
        return EXCEPTION_VALUE.get(String.valueOf(code));
    }

}
