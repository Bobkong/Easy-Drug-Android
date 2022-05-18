package com.example.easydrug.NetService.Api;


import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface SignApi {
    //sign up
    @POST("/signup")
    Observable<ResponseBody> signUp(
            @Body User params
            );


    //sign in
    @POST("/login")
    Observable<ResponseBody> signIn(
            @Body User params
            );
    //test git connect
}
