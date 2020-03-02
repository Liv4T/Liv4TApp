package com.dybcatering.live4teach.Tutor.Consulta.ConsultasDisponibles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ListadoConsultasDisponibles extends AppCompatActivity {

	private List<ItemConsultasDisponibles> listData;
	private RecyclerView rv;
	private AdaptadorConsultasDisponibles adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_consultas_disponibles);
		rv=(RecyclerView)findViewById(R.id.recycler_view);
		rv.setHasFixedSize(true);
		rv.setLayoutManager(new LinearLayoutManager(this));
		listData=new ArrayList<>();
		Intent iin= getIntent();
		Bundle b = iin.getExtras();
		if(b!=null)
		{
			String j =(String) b.get("titulo");
			String a = (String) b.get("detalle");
			Toasty.success(this, "el mensaje recibido es"+ j + "el detalle es " + a, Toast.LENGTH_SHORT).show();;
		}


		final Query nm= FirebaseDatabase.getInstance().getReference("ConsultasEnviadas")
				.orderByChild("estado")
				.equalTo("no resuelta");
		nm.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()){
					for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
						ItemConsultasDisponibles l=npsnapshot.getValue(ItemConsultasDisponibles.class);
						listData.add(l);
					}
					adapter=new AdaptadorConsultasDisponibles(listData);
					rv.setAdapter(adapter);

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}
}
