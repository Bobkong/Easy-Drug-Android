package com.example.easydrug.netservice.Api;


import io.reactivex.Observable;

import com.example.easydrug.model.GeneralResponse;
import com.example.easydrug.model.User;

import retrofit2.http.Body;
import retrofit2.http.POST;

public interface SignApi {
    //sign up
    @POST("/signup")
    Observable<GeneralResponse> signUp(
            @Body User params
            );


    //sign in
    @POST("/login")
    Observable<GeneralResponse> signIn(
            @Body User params
            );
    //test git connect
}
