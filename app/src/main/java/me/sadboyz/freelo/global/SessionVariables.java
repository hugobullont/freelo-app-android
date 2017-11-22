package me.sadboyz.freelo.global;

import com.google.firebase.auth.FirebaseUser;

/**
 * Created by Usuario on 27/09/2017.
 */

public class SessionVariables {
    private  String CurrentidUser;
    private String FacebookId;
    private FirebaseUser CurrentFirebaseUser;
    private static SessionVariables instance;

    public static SessionVariables getInstance(){
        if(instance != null) return instance;
        instance = new SessionVariables();
        return instance;
    }


    public  String getCurrentidUser() {
        return CurrentidUser;
    }

    public  void setCurrentidUser(String currentidUser) {
        CurrentidUser = currentidUser;
    }

    public  String getFacebookId() {
        return FacebookId;
    }

    public  void setFacebookId(String facebookId) {
        FacebookId = facebookId;
    }

    public  FirebaseUser getCurrentFirebaseUser() {
        return CurrentFirebaseUser;
    }

    public  void setCurrentFirebaseUser(FirebaseUser currentFirebaseUser) {
        CurrentFirebaseUser = currentFirebaseUser;
    }
}
