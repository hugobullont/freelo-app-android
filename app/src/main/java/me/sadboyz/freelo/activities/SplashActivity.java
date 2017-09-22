package me.sadboyz.freelo.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import me.sadboyz.freelo.R;



public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //Intent homeIntent = new Intent(SplashActivity.this,MainActivity.this);
                //startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }
}
