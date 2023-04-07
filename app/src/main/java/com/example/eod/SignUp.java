package com.example.eod;

import com.example.eod.daos.UserDao;
import com.example.eod.models.User;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Arrays;
import java.util.List;

public class SignUp extends AppCompatActivity {

    private FirebaseAuth myFirebaseAuth;
    String photoUrl = null;

    public static final int RC_SIGN_IN = 1;


    private FirebaseAuth.AuthStateListener myAuthStateListener;

    List<AuthUI.IdpConfig> providers = Arrays.asList(
            new AuthUI.IdpConfig.EmailBuilder().build(),
            new AuthUI.IdpConfig.GoogleBuilder().build(),
            new AuthUI.IdpConfig.PhoneBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        myFirebaseAuth = FirebaseAuth.getInstance();

        myAuthStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

                FirebaseUser firebaseUseruser = firebaseAuth.getCurrentUser();

                if(firebaseUseruser != null)
                {
                    if(firebaseUseruser.getPhotoUrl() != null)
                    {
                       photoUrl = firebaseUseruser.getPhotoUrl().toString();
                    }
                User user = new User(firebaseUseruser.getUid(),firebaseUseruser.getDisplayName(),photoUrl);
                   UserDao usersDao = new UserDao();
                    usersDao.addUser(user);

                    Intent toMainActivity = new Intent(SignUp.this,MainScreen.class);
                    startActivity(toMainActivity);
                    finish();
                }
                else
                {
                    startActivityForResult(
                            AuthUI.getInstance().createSignInIntentBuilder().setIsSmartLockEnabled(false).setAvailableProviders(providers)
                                    .setLogo(R.drawable.social_media).setTheme(R.style.Theme_EOD).build(),RC_SIGN_IN
                    );
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        myFirebaseAuth.addAuthStateListener(myAuthStateListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        myFirebaseAuth.removeAuthStateListener(myAuthStateListener);
    }

}