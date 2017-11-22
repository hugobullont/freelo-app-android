package me.sadboyz.freelo.activities;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import me.sadboyz.freelo.R;
import me.sadboyz.freelo.global.SessionVariables;
import me.sadboyz.freelo.repositories.ProfilesRepository;
import me.sadboyz.freelo.repositories.UsersRepository;
import me.sadboyz.freelo.ui.BaseActivity;
import me.sadboyz.freelo.ui.BaseSplashActivity;

public class LoginActivity extends BaseSplashActivity {
    private CallbackManager callbackManager;
    private FirebaseAuth mAuth;

    private static final String TAG = "FacebookLogin";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        mAuth = FirebaseAuth.getInstance();

        callbackManager = CallbackManager.Factory.create();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        List<String> permissions = new ArrayList<String>();
        permissions.add("public_profile");
        permissions.add("email");
        permissions.add("user_birthday");
        loginButton.setReadPermissions(permissions);
        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // App code
                        AccessToken at = loginResult.getAccessToken();
                        handleFacebookAccessToken(at, at.getUserId());

                    }

                    @Override
                    public void onCancel() {
                        // App code
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // App code
                    }
                });
    }

    @Override
    protected void onStart() {
        super.onStart();
        /*FirebaseUser currentUser = mAuth.getCurrentUser();
        SessionVariables.CurrentFirebaseUser = currentUser;
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        this.getBaseContext().startActivity(intent);*/
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void handleFacebookAccessToken(final AccessToken token, final String userId) {
        Log.d(TAG, "handleFacebookAccessToken:" + token);

        AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            SessionVariables.getInstance().setCurrentFirebaseUser(user);
                            valUser(token, userId);
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            getBaseContext().startActivity(intent);
                            finishLoginActivity();


                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithCredential:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

    private void valUser(AccessToken token, String userId){
        UsersRepository.getInstance(userId).InitialLoad();
        SessionVariables.getInstance().setFacebookId(userId);
        UsersRepository.getInstance().setCurrentUser(UsersRepository.getInstance().getUserByFacebookId());
        if(UsersRepository.getInstance().getCurrentUser() == null) {
            UsersRepository.getInstance().AddUserToDatabase(userId);
            Profile profile = Profile.getCurrentProfile();
            ProfilesRepository.getInstance().AddProfileToDatabase(profile.getFirstName(),profile.getLastName(),"test@test.com","prueba",
                    "99999999","BCP","FSDALKJKLJJDASKFLAJ");
        }
        else
        {
            ProfilesRepository.getInstance().InitialLoad();
        }
    }

    private void finishLoginActivity(){
        finish();
    }
}
