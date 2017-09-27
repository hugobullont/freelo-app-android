package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Hugo on 27/09/2017.
 */

public class Application {
    private String idApplication;
    private String idWork;
    private String idUser;
    private String date;
    private boolean status;


    public Application() {
    }

    public Application(String idApplication, String idWork, String idUser, String date, boolean status) {
        this.setIdApplication(idApplication);
        this.setIdWork(idWork);
        this.setIdUser(idUser);
        this.setDate(date);
        this.setStatus(status);
    }


    public String getIdApplication() {
        return idApplication;
    }

    public Application setIdApplication(String idApplication) {
        this.idApplication = idApplication;
        return this;
    }

    public String getIdWork() {
        return idWork;
    }

    public Application setIdWork(String idWork) {
        this.idWork = idWork;
        return this;
    }

    public String getIdUser() {
        return idUser;
    }

    public Application setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getDate() {
        return date;
    }

    public Application setDate(String date) {
        this.date = date;
        return this;
    }

    public boolean isStatus() {
        return status;
    }

    public Application setStatus(boolean status) {
        this.status = status;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("idApplication",idApplication);
        bundle.putString("idUser",idUser);
        bundle.putString("idWork",idWork);
        bundle.putString("date",date);
        bundle.putBoolean("status",status);
        return bundle;
    }

    public static Application from(Bundle bundle)
    {
        Application application = new Application();
        application.setIdUser(bundle.getString("idUser")).setIdApplication(bundle.getString("idApplication"))
                .setIdWork(bundle.getString("idWork")).setDate(bundle.getString("date"))
                .setStatus(bundle.getBoolean("status"));
        return application;
    }
}
