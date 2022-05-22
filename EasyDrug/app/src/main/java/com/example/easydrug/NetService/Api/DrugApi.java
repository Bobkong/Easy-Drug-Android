package com.example.easydrug.NetService.Api;

import java.util.Map;

import io.reactivex.Observable;
import model.DrugCode;
import model.DrugInfo;
import model.DrugList;
import model.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface DrugApi {

    //sign up
    @POST("/getDrugList")
    Observable<DrugList> getDrugList(
            @Body User params
    );

    //add drug
    @POST("/addDrug")
    Observable<ResponseBody> addDrug(
            @Body DrugInfo params
    );

    //remove drug
    @POST("/removeDrug")
    Observable<ResponseBody> removeDrug(
            @Body DrugCode params
    );
}
