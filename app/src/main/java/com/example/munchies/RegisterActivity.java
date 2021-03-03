package com.example.munchies;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    EditText first_name, second_name, email, passwrd, retype_passwrd;

    Button register;

    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        first_name = (EditText) findViewById(R.id.editTextFirstNameReg);
        second_name = (EditText) findViewById(R.id.editTextSecondNameReg);
        email = (EditText) findViewById(R.id.editTextEmailAddressReg);
        passwrd = (EditText) findViewById(R.id.editTextNumberPasswordReg);
        retype_passwrd = (EditText) findViewById(R.id.editTextRetypeNumberPasswordReg);

        DB = new DBHelper(this);

        register = (Button) findViewById(R.id.buttonRegister);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String first_nameString = first_name.getText().toString();
                String second_nameString = second_name.getText().toString();
                String emailString = email.getText().toString();
                String passwrdString = passwrd.getText().toString();
                String retype_passwrdString = retype_passwrd.getText().toString();

                if(first_nameString.equals("") || second_nameString.equals("") || emailString.equals("") || passwrdString.equals("") || retype_passwrdString.equals("")){
                    Toast.makeText(RegisterActivity.this, "Please Fill In All The Fields!", Toast.LENGTH_SHORT).show();

                } else {

                    if(passwrdString.equals(retype_passwrdString)) {
                        Boolean check_user = DB.checkEmail(emailString);

                        if(check_user ==  false){
                            Boolean insertData = DB.insertUserData(first_nameString, second_nameString, emailString, passwrdString);

                            if(insertData == true) {
                                Toast.makeText(RegisterActivity.this, "Registration Successful!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                                RegisterActivity.this.startActivity(i);
                                finish();

                            } else {
                                Toast.makeText(RegisterActivity.this, "Registration Failed!", Toast.LENGTH_SHORT).show();

                            }
                        } else {
                            Toast.makeText(RegisterActivity.this, "Email Already Registered! Please use Another Email Address", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(RegisterActivity.this, "Passwords Do Not Match", Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
    }

    public void login(View view) {
        Intent i = new Intent(this, LoginActivity.class);
        this.startActivity(i);
    }
}