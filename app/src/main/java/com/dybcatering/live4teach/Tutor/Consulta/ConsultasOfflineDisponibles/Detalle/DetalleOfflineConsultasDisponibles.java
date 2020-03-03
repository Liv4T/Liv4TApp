package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.Detalle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class DetalleOfflineConsultasDisponibles extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_offline_consultas_disponibles);
		Intent intent = getIntent();
		String recibido = intent.getStringExtra("titulo");

		Toast.makeText(this, "el text es "+ recibido, Toast.LENGTH_SHORT).show();
	}
}
