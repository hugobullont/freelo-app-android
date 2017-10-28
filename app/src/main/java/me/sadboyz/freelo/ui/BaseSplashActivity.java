package me.sadboyz.freelo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.sadboyz.freelo.R;

public class BaseSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (Format.setThemeFromUser()){
            case 1: setTheme(R.style.AppTheme_NoActionBar); break;
            case 2: setTheme(R.style.AppTheme_NoActionBarGreen); break;
            default: setTheme(R.style.AppTheme_NoActionBar); break;
        }
        //setTheme(R.style.AppTheme_NoActionBarGreen);
    }
}
