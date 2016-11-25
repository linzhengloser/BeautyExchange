package com.qks.beautyexchange.api.service;

import com.qks.beautyexchange.api.common.HttpResult;
import com.qks.beautyexchange.api.entity.User;
import com.qks.beautyexchange.application.Constant;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by admin on 2016/3/23.
 */
public interface UserService {

    @FormUrlEncoded
    @POST(Constant.Api.BASE_GET + "user_login")
    Observable<HttpResult<User>> login(@Field("phone_number") String phoneNumber, @Field("password") String password, @Query("token") String token);

}
