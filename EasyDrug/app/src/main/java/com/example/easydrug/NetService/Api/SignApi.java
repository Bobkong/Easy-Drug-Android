package com.example.easydrug.NetService.Api;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignApi {
    //sign up
    @POST("/signup")
    Observable<String> signUp(
            @Body User params
            );


    //sign in
    @POST("/login")
    Observable<String> signIn(
            @Body User params
            );

}
