package me.sadboyz.freelo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.sadboyz.freelo.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (Format.setThemeFromUser()){
            case 1: setTheme(R.style.AppTheme); break;
            case 2: setTheme(R.style.AppThemeGreen); break;
            default: setTheme(R.style.AppTheme); break;
        }


        //setTheme(R.style.AppThemeGreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        switch (Format.setThemeFromUser()){
            case 1: setTheme(R.style.AppTheme); break;
            case 2: setTheme(R.style.AppThemeGreen); break;
            default: setTheme(R.style.AppTheme); break;
        }
    }
}
