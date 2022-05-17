package com.example.easydrug.NetService.Api;


import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignApi {
    //sign up
    @POST("/signup")
    Observable<Response<String>> signUp(
            @Field("username") String username,
            @Field("password") String password
    );

    //sign in
    @POST("/login")
    Observable<Response<String>> signIn(
            @Field("username") String username,
            @Field("password") String password
            );

}
