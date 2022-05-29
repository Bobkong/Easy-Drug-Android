package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.easydrug.Configs;
import com.example.easydrug.R;
import com.example.easydrug.Utils.FileUtil;
import com.example.easydrug.model.GeneralResponse;
import com.example.easydrug.netservice.Api.SignService;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;

public class SignUpActivity extends Activity {

    private static String TAG = "SignUpActivity";
    private ConstraintLayout signUp;
    private LinearLayout logIn;
    EditText username, password1, password2;
    private ConstraintLayout submit;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        username = (EditText) findViewById(R.id.editText1);
        password1 = (EditText) findViewById(R.id.editText2);
        password2 = (EditText) findViewById(R.id.editText3);

        signUp = findViewById(R.id.signup_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || password1.getText().toString().isEmpty() || password2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data wrong!", Toast.LENGTH_SHORT).show();
                } else if (!password1.getText().toString().equals(password2.getText().toString())) {
                    Toast.makeText(getApplicationContext(), "Passwords don't match!", Toast.LENGTH_SHORT).show();
                } else {
                    SignService.getInstance().signUp(username.getText().toString(), password1.getText().toString()).observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Observer<GeneralResponse>() {
                        @Override
                        public void onSubscribe(Disposable d) {

                        }

                        @Override
                        public void onNext(GeneralResponse value) {
                            // success
                            if (value.getCode() == Configs.requestSuccess) {
                                // jump to main
                                startActivity(new Intent(SignUpActivity.this, OnBoardingScanDrugActivity.class));
                                // save to local db
                                FileUtil.saveSPString(SignUpActivity.this, Configs.userNameKey, username.getText().toString());
                                FileUtil.saveSPString(SignUpActivity.this, Configs.passwordKey, password1.getText().toString());
                                FileUtil.saveSPBool(SignUpActivity.this, Configs.ifSignedUpKey, true);
                            } else {
                                // toast
                                Toast.makeText(getApplicationContext(), value.getMsg(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            // fail
                            Log.e(TAG, e.toString());
                        }

                        @Override
                        public void onComplete() {

                        }
                    });

                }
            }
        });

        logIn = findViewById(R.id.loginin_button);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
            }
        });

    }
}
