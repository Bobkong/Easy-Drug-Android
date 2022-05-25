package com.example.easydrug.netservice.Api;

import com.example.easydrug.model.DrugDetail;
import com.example.easydrug.model.DrugDetailRequestParam;
import com.example.easydrug.netservice.HttpResultFunc;
import com.example.easydrug.netservice.EasyDrugServiceManager;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import com.example.easydrug.model.DrugCode;
import com.example.easydrug.model.DrugInfo;
import com.example.easydrug.model.DrugList;
import com.example.easydrug.model.User;
import okhttp3.ResponseBody;

public class DrugService {
    private static DrugService instance;
    public static synchronized DrugService getInstance(){
        if(instance==null)
            instance=new DrugService();
        return instance;
    }

    private final DrugApi drugApi= EasyDrugServiceManager.getInstance().create(DrugApi.class);

    public Observable<DrugList> getDrugList(String username, String password){
        return drugApi.getDrugList(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<ResponseBody> addDrug(String username, String drugName, String drugImageUrl, String drugUpcCode, String drugDesc){
        return drugApi.addDrug(new DrugInfo(username, drugName, drugImageUrl, drugUpcCode, drugDesc))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
    public Observable<ResponseBody> removeDrug(String username, String drugUpcCode){
        return drugApi.removeDrug(new DrugCode(username, drugUpcCode))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<DrugDetail> getDrugDetail(String username, String drugName, String drugDesc) {
        return drugApi.getDrugDetail(new DrugDetailRequestParam(username, drugName, drugDesc))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }


}
