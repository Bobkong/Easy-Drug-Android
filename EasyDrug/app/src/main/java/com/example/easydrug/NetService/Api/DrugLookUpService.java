package com.example.easydrug.NetService.Api;

import com.example.easydrug.NetService.DrugUpcServiceManager;
import com.example.easydrug.NetService.HttpResultFunc;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import model.DrugLookUpInfo;

public class DrugLookUpService {

    private static DrugLookUpService instance;
    public static synchronized DrugLookUpService getInstance(){
        if(instance==null)
            instance=new DrugLookUpService();
        return instance;
    }

    private final UpcLookUpApi lookUpApi= DrugUpcServiceManager.getInstance().create(UpcLookUpApi.class);

    public Observable<DrugLookUpInfo> drugLookUp(String upc){
        return lookUpApi.loopUpUpc(upc)
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
}
