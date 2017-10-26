package me.sadboyz.freelo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.net.URL;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.fragments.NewWorkFragment;
import me.sadboyz.freelo.fragments.NotificationsFragment;
import me.sadboyz.freelo.fragments.ProfileFragment;
import me.sadboyz.freelo.fragments.RewardsFragment;
import me.sadboyz.freelo.fragments.SearchFragment;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.repositories.ProfilesRepository;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText idProfileTextInputEditText;
    private TextInputEditText nameProfileTextInputEditText;
    private TextInputEditText lastanameTextInputEditText;
    private TextInputEditText emailTextInputEditText;

    private TextView mTextMessage;
    private TextView mTitle;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            return navigateAccordingTo(item.getItemId());
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_main);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        this.setTitleView(true,true);
        navigateAccordingTo(R.id.navigation_search);

    }
    

    public void setTitleView(boolean title, boolean credit) {

        RelativeLayout ly = new RelativeLayout(getApplicationContext());
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.WRAP_CONTENT);

        tv.setGravity(Gravity.START);
        tv.setLayoutParams(lp);
        tv.setText(getString(R.string.app_name));
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        if(title)
        {
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
            tv.setTypeface(tf);
        }

        ly.addView(tv);


        if(credit)
        {
            TextView cv = new TextView(getApplicationContext());
            cv.setGravity(Gravity.END);
            cv.setLayoutParams(lp);
            //cv.setText("S/ 100.00");
            Double cred = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser).getCredit();
            cv.setText("S/ " + String.format("%.2f",cred));
            cv.setTextSize(20);
            cv.setTextColor(Color.parseColor("#FFFFFF"));
            ly.addView(cv);
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
            cv.setTypeface(tf);
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(ly);

    }

    private Fragment getFragmentFor (int id)
    {
        switch (id)
        {
            case R.id.navigation_search:
                this.setTitleView(true,true);
                return new SearchFragment();
            case R.id.navigation_rewards:
                this.setTitleView(true,true);
                return new RewardsFragment();
            case R.id.navigation_new_work:
                this.setTitleView(true,true);
                return new NewWorkFragment();
            case R.id.navigation_notifications:
                this.setTitleView(true,true);
                return new NotificationsFragment();
            case R.id.navigation_profile:
                this.setTitleView(true,false);
                return new ProfileFragment();
                //return new ProfileFragment();
        }
        return null;
    }

    private boolean navigateAccordingTo(int id)
    {
        try
        {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content,getFragmentFor(id))
                    .commit();
            return true;
        }
        catch (NullPointerException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onResume() {
        super.onResume();
        this.setTitleView(true,true);
    }
}
