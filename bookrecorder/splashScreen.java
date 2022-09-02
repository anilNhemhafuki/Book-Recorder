package com.example.bookrecorder;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Handler;
import android.content.Intent;
import android.os.Bundle;


public class splashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIME_OUT=2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                Intent i = new Intent(splashScreen.this, onboardScreen.class);
                startActivity(i);

                finish();
            }
        }, 2000);
    }
}