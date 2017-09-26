package me.sadboyz.freelo.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.repositories.CategoriesRepository;
import me.sadboyz.freelo.repositories.RewardsRepository;
import me.sadboyz.freelo.repositories.WorksRepository;


public class SplashActivity extends AppCompatActivity {
    private static int SPLASH_TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.LoadData();
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(homeIntent);
                finish();
            }
        },SPLASH_TIME_OUT);
    }

    private void LoadData()
    {
        RewardsRepository.getInstance().EventLoad();
        WorksRepository.getInstance().EventLoad();
        CategoriesRepository.getInstance().EventLoad();
        //RewardsRepository.getInstance().SetActiveRewards();
    }
}
