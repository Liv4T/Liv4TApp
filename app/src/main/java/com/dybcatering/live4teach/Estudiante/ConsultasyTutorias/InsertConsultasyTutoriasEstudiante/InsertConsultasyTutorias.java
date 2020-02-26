package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Fragments.APIService;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Model.User;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Notifications.Client;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Notifications.Data;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Notifications.MyResponse;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Notifications.Sender;
import com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.Notifications.Token;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertConsultasyTutorias extends Fragment implements AdapterView.OnItemSelectedListener {

	public InsertConsultasyTutorias() {
		// Required empty public constructor
	}

	SessionManager sessionManager;
	String id_usuario;

	DatabaseReference consulta, parent;

	Button button;
	EditText editText;
	TextView txt, txtNombreEstudiante;

	Spinner spinner;

	APIService apiServiceEstudiante;
	FirebaseUser firebaseUser;

	Intent intent;

	boolean notify = false;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_insert_consultasy_tutorias, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.USER_NAME);
		editText = view.findViewById(R.id.edtejemplo);
		button = view.findViewById(R.id.btn);
		txt = view.findViewById(R.id.txt);
		txtNombreEstudiante = view.findViewById(R.id.txtNombreEstudiante);

		txtNombreEstudiante.setText(id_usuario);
		spinner = view.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.consulta, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		spinner.setOnItemSelectedListener(this);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String nombreestudiante = txtNombreEstudiante.getText().toString();
				String mensaje = editText.getText().toString();

				if (editText.getText().equals("")){

					Toast.makeText(getContext(), "la consulta se encuentra vacia", Toast.LENGTH_SHORT).show();

				}else{

					sendMessage(nombreestudiante, "micategoria",mensaje );
				}
			}
		});

		apiServiceEstudiante = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);


		//consulta = FirebaseDatabase.getInstance().getReference().child(id_usuario);

//		HashMap<String, String> hashMap = new HashMap<>();
//		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

		return view;
	}

	//private void sendMessage(String sender, final String receiver, String message){
	private void sendMessage(String estudiante, final String category, String message){

		DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd G 'at' HH:mm:ss z");
		String currentDateandTime = sdf.format(new Date());
//		final String userId = intent.getStringExtra("userid");
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);


		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("sender", estudiante);
		hashMap.put("category", category);
//		hashMap.put("receiver", receiver);
		hashMap.put("message", message);
		hashMap.put("isseen", false);
		hashMap.put("date", currentDateandTime);

		reference.child("ConsultasEnviadas").push().setValue(hashMap);


		final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("ConsultasPeticion")
				.child(id_usuario)
				.child(category);

		chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				if (!dataSnapshot.exists()){
					chatRef.child("id").setValue(id_usuario);
					chatRef.child("usuariotutor").setValue("vacio");
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});


		final String msg = 	message;


		reference = FirebaseDatabase.getInstance().getReference("Users").child(id_usuario);
		reference.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				User user = dataSnapshot.getValue(User.class);
				if (notify){

					sendNotification(category, user.getUsername(), msg);
				}
				notify = false;
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});

	}


	private void sendNotification(String receiver, final String username, final String message){
		final String userId = intent.getStringExtra("userid");
		DatabaseReference tokens = FirebaseDatabase.getInstance().getReference("Tokens");
		Query query = tokens.orderByKey().equalTo(receiver);
		query.addValueEventListener(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()){
					Token token = dataSnapshot1.getValue(Token.class);
					Data data = new Data(id_usuario, R.drawable.logo, username+": "+ message, "Nueva Consulta de Estudiante", userId);

					Sender sender = new Sender(data, "/topics/tutores");

					apiServiceEstudiante.sendNotification(sender)
							.enqueue(new Callback<MyResponse>() {
								@Override
								public void onResponse(Call<MyResponse> call, Response<MyResponse> response) {
									if (response.code() == 200){
										if (response.body().success != 1){
											Toasty.error(getContext(), "Algo falló", Toast.LENGTH_SHORT).show();
										}
									}
								}

								@Override
								public void onFailure(Call<MyResponse> call, Throwable t) {

								}
							});
				}
			}

			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {

			}
		});
	}


	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String text =  parent.getItemAtPosition(position).toString();

		if (text.equals("Seleccionar...")){

			Toast.makeText(getContext(), "No puede seleccionarse este"+ text, Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getContext(), "Seleccion"+ text, Toast.LENGTH_SHORT).show();

		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}
}
