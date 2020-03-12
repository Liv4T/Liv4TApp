package com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.Detalle.DetalleOfflineConsultasDisponibles;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;

public class ListadoOfflineConsultasDisponibles extends AppCompatActivity implements AdaptadorOfflineConsultasDisponibles.OnItemClickListener {

	private ArrayList<ItemOfflineConsultasDisponibles> itemConsultasOffline;
	private RecyclerView rv;
	private AdaptadorOfflineConsultasDisponibles adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listado_consultas_disponibles);
		rv=(RecyclerView)findViewById(R.id.recycler_view);
		rv.setHasFixedSize(true);
		rv.setLayoutManager(new LinearLayoutManager(this));
		itemConsultasOffline=new ArrayList<>();
		Intent iin= getIntent();
		Bundle b = iin.getExtras();
		/*if(b!=null)
		{
			String j =(String) b.get("titulo");
			String a = (String) b.get("detalle");
			Toasty.success(this, "el mensaje recibido es desde offline"+ j + "el detalle es " + a, Toast.LENGTH_SHORT).show();;
		}*/


		final Query nm= FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOffline")
				.orderByChild("estado")
				.equalTo("no resuelta");
		nm.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				if (dataSnapshot.exists()){
					for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
						ItemOfflineConsultasDisponibles l=npsnapshot.getValue(ItemOfflineConsultasDisponibles.class);
						itemConsultasOffline.add(l);
					}
					adapter=new AdaptadorOfflineConsultasDisponibles(getApplicationContext(), itemConsultasOffline);
					rv.setAdapter(adapter);
					adapter.setOnClickItemListener(ListadoOfflineConsultasDisponibles.this);

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});
	}

	@Override
	public void onItemClick(int position) {
		Intent iin= getIntent();
		Bundle b = iin.getExtras();

			String j =(String) b.get("titulo");
			String a = (String) b.get("detalle");
			Toasty.success(this, "el mensaje recibido es desde offline"+ j + "el detalle es " + a, Toast.LENGTH_SHORT).show();;



		Intent intent = new Intent(this, DetalleOfflineConsultasDisponibles.class);
		intent.putExtra("titulo", j);
		intent.putExtra("detalle", a);
		startActivity(intent);

	}
}
