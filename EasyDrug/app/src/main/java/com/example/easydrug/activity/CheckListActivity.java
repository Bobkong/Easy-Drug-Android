package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.easydrug.R;

public class CheckListActivity extends Activity {
    private ConstraintLayout checkList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardingchecklist);
        checkList = findViewById(R.id.ready_togo);
        checkList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CheckListActivity.this, MainActivity.class));
            }
        });
    }
}
