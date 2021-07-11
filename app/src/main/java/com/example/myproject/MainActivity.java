package com.example.myproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myproject.Common.Login;
import com.example.myproject.Common.OnBoarding;
import com.example.myproject.User.HomeActivity;

public class MainActivity extends AppCompatActivity {

    private static int SPLASHSCREEN = 3000;
    Animation topanim,bottomanim;
    ImageView imageView;
    TextView slogan;
    SharedPreferences onBorading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //to hide status bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        topanim = AnimationUtils.loadAnimation(this,R.anim.top_anim);
        bottomanim = AnimationUtils.loadAnimation(this,R.anim.bottom_anim);

        imageView = findViewById(R.id.imageView);
        slogan = findViewById(R.id.logotext);

        imageView.setAnimation(topanim);
        slogan.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                onBorading = getSharedPreferences("onBoarding",MODE_PRIVATE);
                boolean isFirstTime = onBorading.getBoolean("firstTime",true);

                if(isFirstTime){
                    SharedPreferences.Editor editor =  onBorading.edit();
                    editor.putBoolean("firstTime",false);
                    editor.commit();
                    startActivity(new Intent(MainActivity.this, OnBoarding.class));
                }
                else{
                    startActivity(new Intent(MainActivity.this, Login.class));
                }
                finish();
            }
        },SPLASHSCREEN);
    }
}