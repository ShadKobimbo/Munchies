package com.example.munchies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent i = new Intent(this, MainActivity.class);
        this.startActivity(i);
    }

    public void register(View view) {
        Intent i = new Intent(this, RegisterActivity.class);
        this.startActivity(i);
    }

    public void resetPwd(View view) {
        Intent i = new Intent(this, ResetPasswordActivity.class);
        this.startActivity(i);
    }
}