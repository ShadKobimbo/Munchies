package com.example.munchies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    EditText email, passwrd;
    Button login;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = (EditText) findViewById(R.id.editTextEmailAddressLogin);
        passwrd = (EditText) findViewById(R.id.editTextNumberPasswordLogin);

        DB = new DBHelper(this);

        login = (Button) findViewById(R.id.buttonLogin);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String emailString = email.getText().toString();
                String passwrdString = passwrd.getText().toString();

                if(emailString.equals("") || passwrdString.equals("")){
                    Toast.makeText(LoginActivity.this, "Please Fill In All The Fields!", Toast.LENGTH_SHORT).show();

                } else {
                    Boolean check_user_pass = DB.checkUserPasswrd(emailString, passwrdString);

                    if(check_user_pass ==  true){
                        Toast.makeText(LoginActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(LoginActivity.this, MainActivity.class);
                        LoginActivity.this.startActivity(i);
                        finish();

                    } else {
                        Toast.makeText(LoginActivity.this, "Invalid Credentials!", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
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