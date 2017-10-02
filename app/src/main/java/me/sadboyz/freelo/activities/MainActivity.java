package me.sadboyz.freelo.activities;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
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
        //setContentView(R.layout.activity_login);
        setContentView(R.layout.activity_main);
/*
        idProfileTextInputEditText = (TextInputEditText) findViewById(R.id.idProfileInputTextView);
        nameProfileTextInputEditText = (TextInputEditText) findViewById(R.id.nameInputTextView);
        lastanameTextInputEditText = (TextInputEditText) findViewById(R.id.lastnameProfileInputTextView);
        emailTextInputEditText = (TextInputEditText) findViewById(R.id.emailProfileInputTextView);

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            String id = user.getUid();

            nameProfileTextInputEditText.setText(name);
            lastanameTextInputEditText.setText("");
            emailTextInputEditText.setText(email);
            idProfileTextInputEditText.setText(id);
        }else {
            goLoginScreen();
        }
*/



        /*if(AccessToken.getCurrentAccessToken()==null)
        {
            goLoginScreen();
        }else {*/

            setContentView(R.layout.activity_main);
            BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            this.setTitleView();
            navigateAccordingTo(R.id.navigation_search);
        /*}*/

    }

    private void goLoginScreen()
    {
        Intent intent = new Intent(this, LoginActivity.class);
        //Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    private void setTitleView() {
        TextView tv = new TextView(getApplicationContext());
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(ActionBar.LayoutParams.WRAP_CONTENT, ActionBar.LayoutParams.WRAP_CONTENT);
        tv.setLayoutParams(lp);
        tv.setText("Freelo");
        tv.setTextSize(20);
        tv.setTextColor(Color.parseColor("#FFFFFF"));
        Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
        tv.setTypeface(tf);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(tv);


    }

    private Fragment getFragmentFor (int id)
    {
        switch (id)
        {
            case R.id.navigation_search:
                return new SearchFragment();
            case R.id.navigation_rewards:
                return new RewardsFragment();
            case R.id.navigation_new_work:
                return new NewWorkFragment();
            case R.id.navigation_notifications:
                return new NotificationsFragment();
            case R.id.navigation_profile:
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

    public void logout(View view) {
        FirebaseAuth.getInstance().signOut();
        LoginManager.getInstance().logOut();
        goLoginScreen();
    }
}
