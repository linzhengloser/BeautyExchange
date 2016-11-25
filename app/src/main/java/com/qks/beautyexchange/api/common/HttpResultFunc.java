package com.qks.beautyexchange.api.common;

import com.orhanobut.logger.Logger;
import com.qks.beautyexchange.application.Constant;

import rx.functions.Func1;

/**
 * 使用RxJava
 * 公共处理方法,剔除不感兴趣的数据
 * Created by admin on 2016/3/23.
 */
public class HttpResultFunc<T> implements Func1<HttpResult<T>, T> {

    @Override
    public T call(HttpResult<T> httpResult) {
        Logger.json(httpResult.toString());
        if (httpResult.getStatus() != Constant.Api.SUCCESS_STATUS) {
            throw new ApiException(httpResult.getStatus());
        }
        return httpResult.getResults();
    }

}
