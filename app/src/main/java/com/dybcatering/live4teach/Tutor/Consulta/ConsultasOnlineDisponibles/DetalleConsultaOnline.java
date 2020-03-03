package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOnlineDisponibles;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import es.dmoral.toasty.Toasty;

public class DetalleConsultaOnline extends AppCompatActivity {

	TextView NombreEstudiante, Consulta;
	Button Aceptar, Rechazar;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_consulta_online);
		NombreEstudiante = findViewById(R.id.txtNombreEstudiante);
		Consulta = findViewById(R.id.txtConsulta);
		Aceptar = findViewById(R.id.btnaceptar);
		Rechazar  = findViewById(R.id.btnrechazar);

		Intent iin= getIntent();
		Bundle b = iin.getExtras();
		if(b!=null)
		{
			String j =(String) b.get("titulo");
			String a = (String) b.get("detalle");
			Consulta.setText(a);
			NombreEstudiante.setText(j);

		}
		Aceptar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

		Rechazar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {

			}
		});

	}
}
