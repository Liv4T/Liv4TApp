package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOnlineDisponibles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

import es.dmoral.toasty.Toasty;

public class DetalleConsultaOnline extends AppCompatActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detalle_consulta_online);
		Intent iin= getIntent();
		Bundle b = iin.getExtras();
		if(b!=null)
		{
			String j =(String) b.get("titulo");
			String a = (String) b.get("detalle");
			Toasty.success(this, "el mensaje recibido es"+ j + "el detalle es " + a, Toast.LENGTH_SHORT).show();;
		}
	}
}
