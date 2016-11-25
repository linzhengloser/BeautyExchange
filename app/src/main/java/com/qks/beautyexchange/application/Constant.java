package com.qks.beautyexchange.application;

/**
 * Created by admin on 2016/3/23.
 */
public class Constant {

    /**
     * 是否是开发模式
     */
    public static final boolean IS_DEV = true;

    /**
     * API相关常量,按照格式来写
     */
    public static class Api{
        //接口基础URL
        public static final String BASE_URL = "http://123.57.239.182:8012/";
        //Get接口基础URL
        public static final String BASE_GET = "evening_paper/index.php/get/";
        //服务器返回数据正确的STATUS
        public static final int SUCCESS_STATUS = 0;
        //最大超时时间 单位:分钟
        public static final int TIME_OUT_MINUTE = 1 ;
    }


    /**
     * 提示相关常量
     */
    public static class Hint{
        public static final String LOADING_HINT = "加载中...";
    }

    /**
     * 应用级别的配置
     */
    public static class App{
        /**
         * 是否开启Wifi加载图片功能
         * 如:Wifi 下加载   2G/3G/4G 下加载
         */
        public static boolean IS_WIFI_LOAD_IMAGE = true;
    }






}
