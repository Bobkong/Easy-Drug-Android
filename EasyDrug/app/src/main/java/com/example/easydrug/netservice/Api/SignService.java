package com.example.easydrug.netservice.Api;


import com.example.easydrug.model.GeneralResponse;
import com.example.easydrug.netservice.HttpResultFunc;
import com.example.easydrug.netservice.EasyDrugServiceManager;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import com.example.easydrug.model.User;
import okhttp3.ResponseBody;

public class SignService {
    private static SignService instance;
    public static synchronized SignService getInstance(){
        if(instance==null)
            instance=new SignService();
        return instance;
    }

    private final SignApi signApi= EasyDrugServiceManager.getInstance().create(SignApi.class);

    public Observable<GeneralResponse> signUp(String username, String password){
        return signApi.signUp(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<GeneralResponse> signIn(String username, String password){
        return signApi.signIn(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
}
