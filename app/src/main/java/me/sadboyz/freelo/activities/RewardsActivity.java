package me.sadboyz.freelo.activities;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.google.android.gms.tasks.OnSuccessListener;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Profile;
import me.sadboyz.freelo.models.Reward;
import me.sadboyz.freelo.repositories.ApplicationsRepository;
import me.sadboyz.freelo.repositories.ExchangesRepository;
import me.sadboyz.freelo.repositories.ImagesRepository;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.repositories.RewardsRepository;

/**
 * Created by Leonel on 26/09/2017.
 */

public class RewardsActivity extends AppCompatActivity {
    TextView nameTextView;
    TextView descriptionTextView;
    //TextView priceTextView;
    //TextView quantityTextView;
    ImageView pictureImageView;
    Button buttonR;
    Button insufficientCreditButton;

    boolean apply = false;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rewards);
        Reward reward = Reward.from(getIntent().getExtras());
        loadInfoRewards(reward);
        this.setTitleView(true,true);
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
            Double cred = ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser).getCredit();
            cv.setText("S/ " + String.format("%.2f",cred));
            //cv.setText("S/ " + ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser).toString());
            cv.setTextSize(20);
            cv.setTextColor(Color.parseColor("#FFFFFF"));
            ly.addView(cv);
            Typeface tf = Typeface.createFromAsset(getAssets(), "fonts/Pacifico-Regular.ttf");
            cv.setTypeface(tf);
        }
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(ly);

    }

    private void loadInfoRewards(final Reward reward) {

        nameTextView = (TextView)findViewById(R.id.rewardsNameTextView);
        descriptionTextView= (TextView)findViewById(R.id.rewardsDescriptionTextView);
        //priceTextView = (TextView)findViewById(R.id.rewardsPriceTextView);
        //quantityTextView = (TextView)findViewById(R.id.rewardsQuantityTextView);
        pictureImageView = (ImageView)findViewById(R.id.rewardsPictureView);
        nameTextView.setText(reward.getName()+" a "+"S/"+String.format("%.2f",reward.getPrice()) );
        descriptionTextView.setText(reward.getDescription());
        buttonR = (Button) findViewById(R.id.buttonR);
        insufficientCreditButton=(Button) findViewById(R.id.insufficientCreditButton);

        if (!(ExchangesRepository.getInstance().ValidateCredit(reward))){
            buttonR.setVisibility(View.INVISIBLE);
            insufficientCreditButton.setVisibility(View.VISIBLE);
        }else{
            insufficientCreditButton.setVisibility(View.INVISIBLE);
        }
        //priceTextView.setText(String.format("%.2f",reward.getPrice()));
        //quantityTextView.setText(String.valueOf(reward.getQuantity()));
        //pictureImageView.setImageResource(reward.getPictureID());


        ImagesRepository.getInstance().GetStorageReferenceFor(reward.getPictureID())
                .getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                //Glide.with(getApplicationContext()).load(uri.toString()).into(pictureImageView);
                loadImage(Glide.with(getApplicationContext()),uri.toString(),pictureImageView);
            }
        });

        buttonR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showApplyAlert(reward);
            }
        });
    }

    private void showApplyAlert(final Reward reward) {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Canjear Cupon");
        builder.setMessage("Su cupon de descuento se le  enviara a un lugar por definir \n\n" +
                "¡Freelo informa... tal vez te lo enviemos por FB !");
        builder.setPositiveButton("Generar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ExchangesRepository.getInstance().AddExchangeToDatabase(
                        ProfilesRepository.getInstance().GetProfileByUserId(SessionVariables.CurrentidUser).getIdUser()
                        ,reward.getIdReward());
                loadInfoRewards(reward);
                setTitleView(true,true);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showInsufficientCreditAlert() {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Credito Insuficiente");
        builder.setMessage("Usted no cuenta con crédito suficiente \n\n" +
                "Insert coin xD");
        builder.setNeutralButton("Aceptar",null);
        //builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    public void loadImage(RequestManager glide, String url, ImageView view)
    {
        glide.load(url).into(view);
    }


}
