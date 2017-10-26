package me.sadboyz.freelo.activities;

import android.app.Application;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.models.Work;
import me.sadboyz.freelo.repositories.ApplicationsRepository;
import me.sadboyz.freelo.repositories.CategoriesRepository;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.repositories.WorksRepository;

public class WorkActivity extends AppCompatActivity {

    TextView nameWorkTextView;
    TextView categoryWorkTextView;
    TextView descriptionWorkTextView;
    TextView pubPriceWorkTextView;
    TextView dateWorkTextView;
    TextView createdByTextView;
    TextView applyTextView;
    Button applyButton;
    Work work;

    boolean apply = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work);
        work = Work.from(getIntent().getExtras());
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
        applyButton = (Button) findViewById(R.id.applyButton);
        applyTextView = (TextView) findViewById(R.id.applyTextView);

        Intent intent = getIntent();
        final String adapter = intent.getAction();
        if (adapter=="Following"){
            applyButton.setText("Unfollow");
        }

        nameWorkTextView.setText(work.getName());
        categoryWorkTextView.setText(CategoriesRepository.getInstance().getCategoryById(work.getIdCategory()).getName());
        descriptionWorkTextView.setText(work.getDescription());
        pubPriceWorkTextView.setText("S/ " + String.format("%.2f",work.getPubPrice()));
        dateWorkTextView.setText(work.getDate());
        //createdByTextView.setText(ProfilesRepository.getInstance().GetProfileByUserId(work.getCreatedBy()).getName());

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (adapter=="Following"){
                    showCancelAlert();
                }else {
                    showApplyAlert();
                }
            }
        });

        if(apply){
            applyButton.setVisibility(View.INVISIBLE);
            applyTextView.setVisibility(View.VISIBLE);
        }
    }

    private void showApplyAlert()
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Aplicar al Freelo");
        builder.setMessage("Tu perfil será enviado al creador de este Freelo para que pueda comparar solicitudes.\n\n" +
                "¡Te notificaremos si resultas seleccionado!");
        builder.setPositiveButton("Aplicar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApplicationsRepository.getInstance().AddApplicationToDatabase(SessionVariables.CurrentidUser,work.getIdWork());
                apply = true;
                loadInfoWork(work);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }

    private void showCancelAlert()
    {
        AlertDialog.Builder builder =
                new AlertDialog.Builder(this, R.style.AppCompatAlertDialogStyle);
        builder.setTitle("Dejar de seguir  Freelo");
        builder.setMessage("Este Freelo será borrado de tu lista de Seguidos...");
        final Intent intent=getIntent();
        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                ApplicationsRepository.getInstance().setUnfollowApplication(ApplicationsRepository.getInstance().
                getApplicationByWorkId(work.getIdWork()));
                finish();
                //ApplicationsRepository.getInstance().AddApplicationToDatabase(SessionVariables.CurrentidUser,work.getIdWork());
                apply = true;
                //loadInfoWork(work);
            }
        });
        builder.setNegativeButton("Cancelar", null);
        builder.show();
    }
}
