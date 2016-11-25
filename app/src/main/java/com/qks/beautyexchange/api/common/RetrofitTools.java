package com.qks.beautyexchange.api.common;

import com.qks.beautyexchange.application.Constant;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Retrofit工具类
 * Created by admin on 2016/3/23.
 */
public class RetrofitTools {

    /**
     * 默认为异步
     */
    private static RetrofitTools sInstance;

    /**
     * 同步
     */
    private static RetrofitTools sSyncInstance;

    /**
     * retrofit 实例
     */
    public Retrofit mRetrofit;

    private RetrofitTools(){
        this(true);
    }

    /**
     * 该构造函数用来实现是否使用RxJava做异步处理
     * 如果要使用同步请求,传入false即可
     * @param useRxJava
     */
    private RetrofitTools(boolean useRxJava){
        Retrofit.Builder builder = new Retrofit.Builder();
        builder.baseUrl(Constant.Api.BASE_URL);
        builder.addConverterFactory(GsonConverterFactory.create());
        builder.client(getClient());
        if(useRxJava) {
            builder.addCallAdapterFactory(RxJavaCallAdapterFactory.create());
        }
        mRetrofit = builder.build();
    }

    public static RetrofitTools getInstance(){
        if(sInstance ==null){
            synchronized (RetrofitTools.class){
                if(sInstance == null){
                    sInstance = new RetrofitTools();
                }
            }
        }
        return sInstance;
    }


    public static RetrofitTools getSyncInstance(){
        if(sSyncInstance ==null){
            synchronized (RetrofitTools.class){
                if(sSyncInstance == null){
                    sSyncInstance = new RetrofitTools(false);
                }
            }
        }
        return sSyncInstance;
    }



    /**
     * 创建OkHttp对象
     * @return
     */
    private OkHttpClient getClient(){
        //如果是开发模式 记录所有body 如果不是开发模式 只记录状态码,Http协议版本等...
        HttpLoggingInterceptor loggintInterceptor = new HttpLoggingInterceptor();
        if(Constant.IS_DEV)
            loggintInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        else
            loggintInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return new OkHttpClient.Builder()
                .connectTimeout(Constant.Api.TIME_OUT_MINUTE, TimeUnit.MINUTES)//设置超时时间
                .readTimeout(Constant.Api.TIME_OUT_MINUTE, TimeUnit.MINUTES)
                .writeTimeout(Constant.Api.TIME_OUT_MINUTE, TimeUnit.MINUTES)
                .build();
    }

    public <T>T createApiService(Class<T> clazz){
        return mRetrofit.create(clazz);
    }

}
