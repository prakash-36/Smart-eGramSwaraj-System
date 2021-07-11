package com.example.myproject.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myproject.Database.DBHelper;
import com.example.myproject.R;
import com.google.android.material.textfield.TextInputEditText;

public class SignUp extends AppCompatActivity {

    ImageView back_to_login;
    TextInputEditText usernameText,emailText,passwordText,phone_noText;
    Button signUp;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        back_to_login = findViewById(R.id.signup_back_button);
        usernameText = findViewById(R.id.user_name);
        emailText = findViewById(R.id.email);
        phone_noText = findViewById(R.id.phone_no);
        passwordText = findViewById(R.id.password);
        signUp = findViewById(R.id.sign_up);
        db = new DBHelper(this);

        back_to_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passwordText.getText().toString();
                String email = emailText.getText().toString();
                String phoneno = phone_noText.getText().toString();

                if(username.equals("") || password.equals("") || email.equals("") || phoneno.equals(""))
                    Toast.makeText(SignUp.this,"Please fill the all fields",Toast.LENGTH_SHORT).show();
                else{
                    if(!db.checkUserName(username)){
                        Boolean insert = db.insertData(username, email, password);
                        if(insert){
                            Toast.makeText(SignUp.this,"You are Registered successfully",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else{
                            Toast.makeText(SignUp.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                        Toast.makeText(SignUp.this,"Sorry, User already exists. Try something...",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}