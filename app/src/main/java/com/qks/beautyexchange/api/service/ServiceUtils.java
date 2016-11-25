package com.qks.beautyexchange.api.service;

import com.qks.beautyexchange.api.common.HttpResult;
import com.qks.beautyexchange.api.common.HttpResultFunc;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 在这里的Serice指的是服务器接口
 * Created by admin on 2016/5/23.
 */
public class ServiceUtils {

    /**
     * 抽取数据
     * 所有接口的调用都经过该方法
     * @param observable 接口返回的结果
     * @param <T>
     * @return
     */
    public static <T> Observable<T> extractData(Observable<HttpResult<T>> observable) {
        return observable.map(new HttpResultFunc<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
