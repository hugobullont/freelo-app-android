package me.sadboyz.freelo.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
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
import me.sadboyz.freelo.models.Profile;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.ui.BaseActivity;

public class MainActivity extends BaseActivity {

    private TextInputEditText idProfileTextInputEditText;
    private TextInputEditText nameProfileTextInputEditText;
    private TextInputEditText lastanameTextInputEditText;
    private TextInputEditText emailTextInputEditText;

    private TextView mTextMessage;
    private TextView mTitle;

    BottomNavigationView navigation;
    boolean noConnection = false;

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
            navigation = (BottomNavigationView) findViewById(R.id.navigation);
            navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
            this.setTitleView(true, true);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(!noConnection) {navigateAccordingTo(R.id.navigation_search);}
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

            Profile profile = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser);
            if(profile == null){
                noConnection = true;
            }


            if(credit && !noConnection)
            {
                TextView cv = new TextView(getApplicationContext());
                cv.setGravity(Gravity.END);
                cv.setLayoutParams(lp);
                //cv.setText("S/ 100.00");
                    Double cred = profile.getCredit();
                    cv.setText("S/ " + String.format("%.2f", cred));
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
        if(noConnection) showNoConnection();
    }

    @Override
    public void recreate() {
        super.recreate();
        navigation.setSelectedItemId(R.id.navigation_search);


    }

    private void showNoConnection()
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Sin Conexión");
        builder.setMessage("No tienes conexión para usar Freelo. \nVuelve más tarde");

        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }

        });
        builder.setCancelable(false);
        builder.show();
    }

}
