package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.easydrug.R;

public class OnBoardingScanDrugActivity extends Activity{
    private ConstraintLayout letsGoScanDrug;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboardingscandrug);
        letsGoScanDrug = findViewById(R.id.letsgo_scandrug);
        letsGoScanDrug.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OnBoardingScanDrugActivity.this, CheckListActivity.class));
            }
        });
    }
}


