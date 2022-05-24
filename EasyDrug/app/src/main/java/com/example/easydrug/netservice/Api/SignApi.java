package com.example.easydrug.netservice.Api;


import io.reactivex.Observable;
import com.example.easydrug.model.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
