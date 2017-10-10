package me.sadboyz.freelo.activities;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.CategoriesRepository;
import me.sadboyz.freelo.repositories.ProfilesRepository;

public class WorkActivity extends AppCompatActivity {

    TextView nameWorkTextView;
    TextView categoryWorkTextView;
    TextView descriptionWorkTextView;
    TextView pubPriceWorkTextView;
    TextView dateWorkTextView;
    TextView createdByTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        Work work = Work.from(getIntent().getExtras());
        loadInfoWork(work);
        this.setTitleView();
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

    private void loadInfoWork(final Work work)
    {
        nameWorkTextView = (TextView) findViewById(R.id.nameWorkTextView);
        descriptionWorkTextView = (TextView) findViewById(R.id.descriptionWorkTextView);
        categoryWorkTextView = (TextView) findViewById(R.id.categoryWorkTextView);
        pubPriceWorkTextView = (TextView) findViewById(R.id.pubPriceWorkTextView);
        dateWorkTextView = (TextView) findViewById(R.id.dateWorkTextView);
        createdByTextView = (TextView) findViewById(R.id.createdByWorkTextView);

        nameWorkTextView.setText(work.getName());
        categoryWorkTextView.setText(CategoriesRepository.getInstance().getCategoryById(work.getIdCategory()).getName());
        descriptionWorkTextView.setText(work.getDescription());
        pubPriceWorkTextView.setText("S/ " + String.format("%.2f",work.getPubPrice()));
        dateWorkTextView.setText(work.getDate());
        //createdByTextView.setText(ProfilesRepository.getInstance().GetProfileByUserId(work.getCreatedBy()).getName());
    }
}
