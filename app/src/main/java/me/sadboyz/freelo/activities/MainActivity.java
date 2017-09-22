package me.sadboyz.freelo.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import me.sadboyz.freelo.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_search:
                    mTextMessage.setText(R.string.title_activity_search);
                    return true;
                case R.id.navigation_rewards:
                    mTextMessage.setText(R.string.title_activity_rewards);
                    return true;
                case R.id.navigation_new_work:
                    mTextMessage.setText(R.string.title_activity_new_work);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_activity_notifications);
                    return true;
                case R.id.navigation_profile:
                    mTextMessage.setText(R.string.title_activity_profile);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

}
