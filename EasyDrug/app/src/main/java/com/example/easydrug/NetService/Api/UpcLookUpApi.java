package com.example.easydrug.NetService.Api;

import io.reactivex.Observable;
import model.DrugLookUpInfo;
import model.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UpcLookUpApi {

    //sign up
    @GET("/prod/trial//lookup")
    Observable<DrugLookUpInfo> loopUpUpc(
            @Query("upc") String upc
    );
}
