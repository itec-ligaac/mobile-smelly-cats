package com.example.myapplication.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.myapplication.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    }

    public void OnLogin(View view) {
        startActivity(new Intent(LoginActivity.this,MainActivity.class));
    }

    public void OnRegister(View view) {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}