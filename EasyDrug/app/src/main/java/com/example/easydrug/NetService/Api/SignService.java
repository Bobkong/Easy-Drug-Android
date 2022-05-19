package com.example.easydrug.NetService.Api;


import com.example.easydrug.NetService.HttpResultFunc;
import com.example.easydrug.NetService.ServiceManager;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;
import model.User;
import okhttp3.ResponseBody;

public class SignService {
    private static SignService instance;
    public static synchronized SignService getInstance(){
        if(instance==null)
            instance=new SignService();
        return instance;
    }

    private final SignApi signApi= ServiceManager.getInstance().create(SignApi.class);

    public Observable<ResponseBody> signUp(String username, String password){
        return signApi.signUp(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }

    public Observable<ResponseBody> signIn(String username, String password){
        return signApi.signIn(new User(username, password))
                .onErrorResumeNext(new HttpResultFunc<>())
                .subscribeOn(Schedulers.io());
    }
}
