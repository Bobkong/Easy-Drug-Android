package com.example.easydrug.NetService.Api;

import com.example.easydrug.NetService.HttpResultFunc;
import com.example.easydrug.NetService.EasyDrugServiceManager;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import model.DrugCode;
import model.DrugInfo;
import model.DrugList;
import model.User;
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

    public Observable<ResponseBody> addDrug(String username, String drugName, String drugImageUrl, String drugUpcCode){
        return drugApi.addDrug(new DrugInfo(username, drugName, drugImageUrl, drugUpcCode))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
    public Observable<ResponseBody> removeDrug(String username, String drugUpcCode){
        return drugApi.removeDrug(new DrugCode(username, drugUpcCode))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }


}
