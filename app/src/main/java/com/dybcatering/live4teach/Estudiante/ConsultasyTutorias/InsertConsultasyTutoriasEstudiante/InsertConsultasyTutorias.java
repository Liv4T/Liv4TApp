package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class InsertConsultasyTutorias extends Fragment {

	public InsertConsultasyTutorias() {
		// Required empty public constructor
	}

	SessionManager sessionManager;
	String id_usuario;

	DatabaseReference consulta, parent;

	Button button;
	EditText editText;
	TextView txt;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_insert_consultasy_tutorias, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);
		editText = view.findViewById(R.id.edtejemplo);
		button = view.findViewById(R.id.btn);
		txt = view.findViewById(R.id.txt);


		consulta = FirebaseDatabase.getInstance().getReference().child(id_usuario);

		HashMap<String, String> hashMap = new HashMap<>();


		return view;
	}

}
