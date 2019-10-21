package com.dybcatering.live4teach.Estudiante.Perfil;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.dybcatering.live4teach.R;

public class ActualizarDatos extends AppCompatActivity {

    TextView txtNomUp, txtEmailUp, txtTeleUp, txtIdentiUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        txtNomUp = findViewById(R.id.nombre_update);
        txtEmailUp = findViewById(R.id.email_update);
        txtTeleUp = findViewById(R.id.telefono_update);
        txtIdentiUp= findViewById(R.id.identificacion_update);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            txtNomUp.setText(bundle.getString("nombre"));
            txtEmailUp.setText(bundle.getString("correo"));
            txtTeleUp.setText(bundle.getString("telefono"));
            txtIdentiUp.setText(bundle.getString("identificacion"));
        }
    }
}
