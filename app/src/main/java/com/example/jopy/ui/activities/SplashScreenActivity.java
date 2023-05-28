package com.example.jopy.ui.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;

import com.example.jopy.R;
import com.example.jopy.ui.activities.login_and_createAccount.LoginActivity;
import com.example.jopy.util.UtilFunctions;

import static com.example.jopy.util.UtilFunctions.setWindowFlag;

public class SplashScreenActivity extends AppCompatActivity {

    private int SPLASH_TIME_OUT=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //basic
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR);
        setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        //
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent loginIntent = new Intent(SplashScreenActivity.this , LoginActivity.class);
                startActivity(loginIntent);
                finish();
//                if(UtilFunctions.readLocal("token",SplashScreenActivity.this).equals("")){
//                    Intent loginIntent = new Intent(SplashScreenActivity.this , LoginActivity.class);
//                    startActivity(loginIntent);
//                    finish();
//                }
//                else {
//                    Intent loginIntent = new Intent(SplashScreenActivity.this , HomePageActivity.class);
//                    startActivity(loginIntent);
//                    finish();
//                }
            }
        },SPLASH_TIME_OUT);
    }
}