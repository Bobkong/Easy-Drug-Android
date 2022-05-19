package com.example.easydrug.NetService.Api;

import com.example.easydrug.NetService.HttpResultFunc;
import com.example.easydrug.NetService.ServiceManager;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import model.DrugList;
import model.User;

public class DrugService {
    private static DrugService instance;
    public static synchronized DrugService getInstance(){
        if(instance==null)
            instance=new DrugService();
        return instance;
    }

    private final DrugApi drugApi= ServiceManager.getInstance().create(DrugApi.class);

    public Observable<DrugList> getDrugList(String username, String password){
        return drugApi.getDrugList(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }


}
