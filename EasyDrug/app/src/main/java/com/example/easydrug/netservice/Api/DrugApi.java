package com.example.easydrug.netservice.Api;

import io.reactivex.Observable;
import com.example.easydrug.model.DrugCode;
import com.example.easydrug.model.DrugInfo;
import com.example.easydrug.model.DrugList;
import com.example.easydrug.model.User;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.POST;

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
