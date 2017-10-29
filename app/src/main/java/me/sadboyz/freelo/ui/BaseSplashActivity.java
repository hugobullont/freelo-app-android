package me.sadboyz.freelo.ui;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.repositories.ApplicationsRepository;
import me.sadboyz.freelo.repositories.CategoriesRepository;
import me.sadboyz.freelo.repositories.ExchangesRepository;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.repositories.RewardsRepository;
import me.sadboyz.freelo.repositories.TransactionsRepository;
import me.sadboyz.freelo.repositories.WorksRepository;

public class BaseSplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.LoadData();
        /*int theme = Format.setThemeFromUser();
        switch (theme){
            case 1: setTheme(R.style.AppTheme_NoActionBar); break;
            case 2: setTheme(R.style.AppTheme_NoActionBarGreen); break;
            default: setTheme(R.style.AppTheme_NoActionBar); break;
        }*/
        //setTheme(R.style.AppTheme_NoActionBarGreen);
    }

    private void LoadData()
    {
        ProfilesRepository.getInstance().EventLoad();
        ProfilesRepository.getInstance().InitialLoad();
        RewardsRepository.getInstance().EventLoad();
        WorksRepository.getInstance().EventLoad();
        CategoriesRepository.getInstance().EventLoad();
        ApplicationsRepository.getInstance().EventLoad();
        ExchangesRepository.getInstance().EventLoad();
        TransactionsRepository.getInstance().EventLoad();
    }
}
