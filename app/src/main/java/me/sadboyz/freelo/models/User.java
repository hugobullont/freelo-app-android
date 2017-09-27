package me.sadboyz.freelo.models;

import android.os.Bundle;

/**
 * Created by Usuario on 27/09/2017.
 */

public class User {
    private String idUser;
    private String idFacebook;

    public User() {
    }

    public User(String idUser, String idFacebook) {
        this.setIdUser(idUser);
        this.setIdFacebook(idFacebook);
    }

    public String getIdUser() {
        return idUser;
    }

    public User setIdUser(String idUser) {
        this.idUser = idUser;
        return this;
    }

    public String getIdFacebook() {
        return idFacebook;
    }

    public User setIdFacebook(String idFacebook) {
        this.idFacebook = idFacebook;
        return this;
    }

    public Bundle toBundle()
    {
        Bundle bundle = new Bundle();
        bundle.putString("idUser",idUser);
        bundle.putString("idFacebook",idFacebook);
        return bundle;
    }

    public static User from(Bundle bundle)
    {
        User user = new User();
        user.setIdUser(bundle.getString("idUser")).setIdFacebook(bundle.getString("idFacebook"));
        return user;
    }
}

