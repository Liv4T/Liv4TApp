package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante.MisConsultas.ConsultasOffline;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.MessageActivity;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.AdaptadorOfflineConsultasDisponibles;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.Detalle.DetalleOfflineConsultasDisponibles;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.ItemOfflineConsultasDisponibles;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasOfflineDisponibles.ListadoOfflineConsultasDisponibles;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class MisConsultasOffline extends Fragment implements AdaptadorMisConsultasOffline.OnItemClickListener {

	public MisConsultasOffline() {
		// Required empty public constructor
	}

	private ArrayList<ItemMisConsultasOffline> itemMisConsultasOffline;
	private RecyclerView rv;
	private AdaptadorMisConsultasOffline adapter;
	View view;
	SessionManager sessionManager;
	String username;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_mis_consultas_offline, container, false);
		rv= view.findViewById(R.id.recycler_view);
		rv.setHasFixedSize(true);
		rv.setLayoutManager(new LinearLayoutManager(getContext()));
		itemMisConsultasOffline=new ArrayList<>();
		sessionManager = new SessionManager(getContext());
		HashMap<String, String> user = sessionManager.getUserDetail();
		username = user.get(SessionManager.USER_NAME);


		final Query nm= FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOffline")
				.orderByChild("remitenteestado")
				.equalTo(username+"_"+"no resuelta");
		nm.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(DataSnapshot dataSnapshot) {
				itemMisConsultasOffline = new ArrayList<ItemMisConsultasOffline>();
				if (dataSnapshot.exists()){
					for (DataSnapshot npsnapshot : dataSnapshot.getChildren()){
						ItemMisConsultasOffline l=npsnapshot.getValue(ItemMisConsultasOffline.class);
						itemMisConsultasOffline.add(l);
					}
					adapter=new AdaptadorMisConsultasOffline(getActivity(), itemMisConsultasOffline);
					rv.setAdapter(adapter);
					adapter.setOnClickItemListener(MisConsultasOffline.this);

				}
			}

			@Override
			public void onCancelled(DatabaseError databaseError) {

			}
		});

		return view;
	}

	@Override
	public void onItemClick(int position) {


	}
}
