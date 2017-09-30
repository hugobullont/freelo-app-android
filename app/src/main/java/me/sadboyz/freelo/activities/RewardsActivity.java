package me.sadboyz.freelo.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.tasks.OnSuccessListener;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.models.Reward;
import me.sadboyz.freelo.repositories.ImagesRepository;

/**
 * Created by Leonel on 26/09/2017.
 */

public class RewardsActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView descriptionTextView;
    TextView priceTextView;
    TextView quantityTextView;
    ImageView pictureImageView;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Reward reward = Reward.from(getIntent().getExtras());
        loadInfoRewards(reward);
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

    private void loadInfoRewards(final Reward reward) {
        nameTextView = (TextView)findViewById(R.id.rewardsNameTextView);
        descriptionTextView= (TextView)findViewById(R.id.rewardsDescriptionTextView);
        priceTextView = (TextView)findViewById(R.id.rewardsPriceTextView);
        quantityTextView = (TextView)findViewById(R.id.rewardsQuantityTextView);
        pictureImageView = (ImageView)findViewById(R.id.rewardsPictureView);


        nameTextView.setText(reward.getName());
        descriptionTextView.setText(reward.getDescription());
        priceTextView.setText(String.format("%.2f",reward.getPrice()));
        quantityTextView.setText(String.valueOf(reward.getQuantity()));
        //pictureImageView.setImageResource(reward.getPictureID());


        ImagesRepository.getInstance().GetStorageReferenceFor(reward.getPictureID())
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Glide.with(getApplicationContext()).load(uri.toString()).into(pictureImageView);
                loadImage(Glide.with(getApplicationContext()),uri.toString(),pictureImageView);
            }
        });


    }

    public void loadImage(RequestManager glide, String url, ImageView view)
    {
        glide.load(url).into(view);
    }


}
