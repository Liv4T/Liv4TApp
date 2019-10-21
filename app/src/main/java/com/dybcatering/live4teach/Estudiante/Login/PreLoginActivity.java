package com.dybcatering.live4teach.Estudiante.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.PrincipalActivity;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.TutorPrincipalActivity;

public class PreLoginActivity extends AppCompatActivity  {

    TextView txtRegistrarse;

    public Button btnLogin;
    boolean separateOnClickActive;
    public Button btnTutor, btnEstudiante;

    public String a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
            new CheckInternetConnection(this).checkConnection();
            txtRegistrarse = findViewById(R.id.text_registro);
            btnLogin = findViewById(R.id.btn_loginestu2);
            btnTutor = findViewById(R.id.btn_logintutor);
            btnEstudiante = findViewById(R.id.btn_loginestu);

            txtRegistrarse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PreLoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                }
            });

            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(PreLoginActivity.this, PrincipalActivity.class);
                    startActivity(intent);

                }
            });

            btnTutor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                        Intent intent = new Intent(PreLoginActivity.this, TutorPrincipalActivity.class);
                        startActivity(intent);
                }
            });



    }



    private Boolean validarBoton(){

        return true;
    }


    @Override
    protected void onResume() {
        new CheckInternetConnection(this).checkConnection();
        super.onResume();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
