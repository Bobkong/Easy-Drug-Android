package com.example.easydrug.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.easydrug.R;

public class SignUpActivity extends Activity {
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
        submit =  findViewById(R.id.signup_button);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().isEmpty() || password1.getText().toString().isEmpty() || password2.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter the Data wrong", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Name -  " + username.getText().toString() + " \n"
                                    + "Password -  " + password1.getText().toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        signUp = findViewById(R.id.signup_button);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
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
