package com.dybcatering.live4teach.Estudiante.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.dybcatering.live4teach.Estudiante.Login.PreLoginActivity;
import com.dybcatering.live4teach.R;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 4000;
//    SessionManager sessionManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        YoYo.with(Techniques.FadeInLeft)
                .duration(7000)
                .playOn(findViewById(R.id.bienvenido));
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                startActivity(new Intent(SplashActivity.this, PreLoginActivity.class));
                finish();
            }
        }, SPLASH_TIME_OUT);
    }


}
