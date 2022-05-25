package com.example.easydrug.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    private static String TAG = "SplashActivity";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        new Handler().postDelayed(() -> {
            // todo Carson
            // determine go to MainActivity or OnboardingActivity

            startActivity(new Intent(SplashActivity.this, MainActivity.class));
            finish();
        }, 300);
    }
}
