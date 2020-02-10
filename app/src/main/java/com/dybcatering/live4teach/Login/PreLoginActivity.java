package com.dybcatering.live4teach.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.BuildConfig;
import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.InicioActivityTutor;

public class PreLoginActivity extends AppCompatActivity  {

    TextView txtRegistrarse, txtversion;

    public Button btnLogin;
    boolean separateOnClickActive;
    public Button btnTutor, btnEstudiante;

    public String a;

    public String boton;

    Boolean isclick = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_login);
            new CheckInternetConnection(this).checkConnection();
            txtRegistrarse = findViewById(R.id.text_registro);
            btnEstudiante = findViewById(R.id.btn_loginestu);
            btnTutor = findViewById(R.id.btn_logintutor);
            btnLogin = findViewById(R.id.btn_loginestu2);
            txtversion = findViewById(R.id.version);
            txtversion.setText("Versión: "+ BuildConfig.VERSION_NAME);

            txtRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PreLoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                }
            });
           // final boolean[] isclick = {false};


            btnEstudiante.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isclick = true;
                    Toast.makeText(PreLoginActivity.this, "Usted ha Seleccionado Estudiante", Toast.LENGTH_SHORT).show();

                }
            });



            btnTutor.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    isclick = false;

                    Toast.makeText(PreLoginActivity.this, "Usted ha Seleccionado Tutor", Toast.LENGTH_SHORT).show();

                }
            });


        btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isclick == null){
                        Toast.makeText(PreLoginActivity.this, "Por favor seleccione una opción", Toast.LENGTH_SHORT).show();
                    }else if ((isclick)) {
                        Intent intent = new Intent(PreLoginActivity.this, InicioActivity.class);
                        startActivity(intent);
                        finish();

                        //Toast.makeText(PreLoginActivity.this, "prueba a", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(PreLoginActivity.this, InicioActivityTutor.class);
                        startActivity(intent);
                        finish();
                       // Toast.makeText(PreLoginActivity.this, "prueba b", Toast.LENGTH_SHORT).show();
                    }
                }
            });

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
