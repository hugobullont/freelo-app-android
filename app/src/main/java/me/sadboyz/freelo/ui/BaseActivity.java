package me.sadboyz.freelo.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;

import me.sadboyz.freelo.R;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        theme();


        //setTheme(R.style.AppThemeGreen);
    }

    @Override
    protected void onResume() {
        super.onResume();
        theme();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
    }

    private void theme(){
        switch (Format.setThemeFromUser()){
            case 1: setTheme(R.style.AppTheme); break;
            case 2: setTheme(R.style.AppThemeGreen); break;
            case 3: setTheme(R.style.AppThemeRed); break;
            case 4: setTheme(R.style.AppThemePurple); break;
            default: setTheme(R.style.AppTheme); break;
        }
    }
}
