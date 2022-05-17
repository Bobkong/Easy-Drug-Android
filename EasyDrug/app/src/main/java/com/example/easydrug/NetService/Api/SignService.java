package com.example.easydrug.NetService.Api;


import com.example.easydrug.NetService.HttpResultFunc;
import com.example.easydrug.NetService.ServerResultFunc;
import com.example.easydrug.NetService.ServiceManager;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class SignService {
    private static SignService instance;
    public static synchronized SignService getInstance(){
        if(instance==null)
            instance=new SignService();
        return instance;
    }

    private final SignApi recipeApi= ServiceManager.getInstance().create(SignApi.class);

    public Observable<String> signUp(String username, String password){
        return recipeApi.signUp(username, password)
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<String> signIn(String username, String password){
        return recipeApi.signIn(username, password)
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
}
