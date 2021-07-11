package com.example.myproject.User;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myproject.Database.DBHelper;
import com.example.myproject.R;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class Registerform extends AppCompatActivity {

    MaterialAutoCompleteTextView stateText, districtText, talukText, villageText;
    Button resetBut, subBut;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registerform);

        stateText = findViewById(R.id.state);
        districtText = findViewById(R.id.district);
        talukText = findViewById(R.id.taluk);
        villageText = findViewById(R.id.village);
        resetBut = findViewById(R.id.reset_but);
        subBut = findViewById(R.id.sub_but);
        db = new DBHelper(this);

        //insert values
        this.insertValues();

        String[] options = {"TamilNadu"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.list_item,options);
        stateText.setAdapter(adapter);

        stateText.setOnItemClickListener((parent, view, position, id) -> {
            String item = stateText.getAdapter().getItem(position).toString();
            districtText.setAdapter(new ArrayAdapter<>(Registerform.this,R.layout.list_item,db.getItems("district","state",item)));
        });


        districtText.setOnItemClickListener((parent, view, position, id) -> {
            String item = districtText.getAdapter().getItem(position).toString();
            talukText.setAdapter(new ArrayAdapter<>(Registerform.this,R.layout.list_item,db.getItems("taluk","district",item)));
        });


        talukText.setOnItemClickListener((parent, view, position, id) -> {
            String item = talukText.getAdapter().getItem(position).toString();
            villageText.setAdapter(new ArrayAdapter<>(Registerform.this,R.layout.list_item,db.getItems("village","taluk",item)));
        });

        resetBut.setOnClickListener(v -> {
            stateText.setText("");
            districtText.setText("");
            districtText.setAdapter(null);
            talukText.setText("");
            talukText.setAdapter(null);
            villageText.setText("");
            villageText.setAdapter(null);
        });

        subBut.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
            startActivity(intent);
        });
    }

    private void insertValues() {
        db.insertLocalBody("TamilNadu","Madurai","Usilampatti","Nakkalapatti");
        db.insertLocalBody("TamilNadu","Madurai","Usilampatti","Poochipatti");
        db.insertLocalBody("TamilNadu","Madurai","Melur","Ambalakaranpatti");
        db.insertLocalBody("TamilNadu","Madurai","Melur","Arasappanpatti");
        db.insertLocalBody("TamilNadu","Vellore","Tirupathur","Adiyur");
        db.insertLocalBody("TamilNadu","Vellore","Tirupathur","Agraharam");
        db.insertLocalBody("TamilNadu","Vellore","Arakonam","Akkachikuppam");
        db.insertLocalBody("TamilNadu","Vellore","Arakonam","Alapakkam");

    }

}