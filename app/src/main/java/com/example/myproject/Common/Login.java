package com.example.myproject.Common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myproject.Database.DBHelper;
import com.example.myproject.R;
import com.example.myproject.User.HomeActivity;
import com.example.myproject.User.Registerform;
import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {

    TextView textView,usernameText,passText;
    Button loginBut;
    DBHelper db;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textView = findViewById(R.id.create_account);
        usernameText = findViewById(R.id.user_name);
        passText = findViewById(R.id.password);
        loginBut = findViewById(R.id.login);
        db = new DBHelper(this);
        
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
        
        loginBut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameText.getText().toString();
                String password = passText.getText().toString();

                if(username.equals("") || password.equals(""))
                    Toast.makeText(Login.this,"Please fill the all fields",Toast.LENGTH_SHORT).show();
                else{
                    if(db.checkUserPassword(username, password)){
                        Toast.makeText(Login.this,"You are Logged in successfully",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Registerform.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(Login.this,"Username or Password is incorrect..",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}