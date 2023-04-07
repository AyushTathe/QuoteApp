package com.example.eod;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class SplashScreen extends AppCompatActivity {
    LottieAnimationView lottieAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        lottieAnimation = findViewById(R.id.lottieAnimation);

        lottieAnimation.animate().translationX(1000).setDuration(1000).setStartDelay(1900);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent splash = new Intent(getApplicationContext(),SignUp.class);
                startActivity(splash);
                finish();
            }
        },3000);
    }
}