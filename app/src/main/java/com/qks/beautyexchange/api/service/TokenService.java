package com.qks.beautyexchange.api.service;

import com.qks.beautyexchange.api.entity.Token;

import retrofit2.http.GET;
import rx.Observable;

/**
 * 公共Token接口
 * Created by admin on 2016/3/31.
 */
public interface TokenService {


    @GET("evening_paper/index.php/get/get_time")
    Observable<Token> getToken();

}
