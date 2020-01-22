package com.dybcatering.live4teach.Splash;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dybcatering.live4teach.Login.PreLoginActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.Inicio.InicioActivity;

public class SplashActivity extends AppCompatActivity {


    private static int SPLASH_TIME_OUT = 3500;


    private SessionManager session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ActionBar actionbar = getSupportActionBar();
        actionbar.hide();

        session = new SessionManager(SplashActivity.this);

       // YoYo.with(Techniques.FadeInLeft)
         //       .duration(7000)
           //     .playOn(findViewById(R.id.bienvenido));
        new Handler().postDelayed(new Runnable() {


            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                if (!session.isLoggin()){

                    startActivity(new Intent(SplashActivity.this, PreLoginActivity.class));

                }else{
                    startActivity(new Intent(SplashActivity.this, InicioActivity.class));

                }
            }
        }, SPLASH_TIME_OUT);
    }


}
