package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertConsultasyTutorias extends Fragment implements AdapterView.OnItemSelectedListener {

	public InsertConsultasyTutorias() {
		// Required empty public constructor
	}
	private static final String TAG = InsertConsultasyTutorias.class.getSimpleName(); //getting the info

	SessionManager sessionManager;
	String id_usuario, nombreusuario;

	DatabaseReference consulta, parent;

	Button button;
	EditText editText;
	TextView txt, txtNombreEstudiante, txtuuid;

	Spinner spinner;

	APIService apiServiceEstudiante;
	FirebaseUser firebaseUser;

	Intent intent;
	APIService apiService;

	DatabaseReference databaseReference;

	boolean notify = false;
	private static String URL_READ = "https://dybcatering.com/back_live_app/read_detail.php";



	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_insert_consultasy_tutorias, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		nombreusuario = user.get(SessionManager.USER_NAME);

		sessionManager = new SessionManager(getActivity());
		id_usuario = user.get(SessionManager.ID);
		ObternerUuid(id_usuario);
		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
		editText = view.findViewById(R.id.edtejemplo);
		button = view.findViewById(R.id.btn);
		txt = view.findViewById(R.id.txt);
		txtNombreEstudiante = view.findViewById(R.id.txtNombreEstudiante);
		txtuuid = view.findViewById(R.id.txtuuid);
		txtNombreEstudiante.setText(nombreusuario);
		spinner = view.findViewById(R.id.spinner);
		ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.consulta, android.R.layout.simple_spinner_item);
		arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(arrayAdapter);
		spinner.setOnItemSelectedListener(this);
		//Toast.makeText(getContext(), "el uid es "+ txtuuid.getText().toString(), Toast.LENGTH_SHORT).show();
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String nombreestudiante = txtNombreEstudiante.getText().toString();
				String mensaje = editText.getText().toString();
				String categoria = spinner.getSelectedItem().toString();

				if (editText.getText().equals("") || spinner.getSelectedItem().toString().equals("Seleccionar...")){

					Toast.makeText(getContext(), "la consulta se encuentra vacia", Toast.LENGTH_SHORT).show();

				}else {
						enviarMensajeTema(nombreestudiante, mensaje, categoria);
				}
			}
		});

		apiServiceEstudiante = Client.getClient("https://fcm.googleapis.com/").create(APIService.class);


		//consulta = FirebaseDatabase.getInstance().getReference().child(id_usuario);

//		HashMap<String, String> hashMap = new hashMap<>();
//		firebaseUser = FirebaseAuth.getInstance().getCurrentUser();

		return view;
	}





	public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
		String text =  parent.getItemAtPosition(position).toString();

		if (text.equals("Seleccionar...")){

			Toast.makeText(getContext(), "Debes Seleccionar una Opción "+ text, Toast.LENGTH_SHORT).show();
		}else{
			Toast.makeText(getContext(), "Seleccion"+ text, Toast.LENGTH_SHORT).show();

		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {

	}

	public void ObternerUuid(final String id){

			final ProgressDialog progressDialog = new ProgressDialog(getActivity());
			progressDialog.setMessage("Cargando...");
			progressDialog.show();
			progressDialog.setCancelable(false);
			StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
					new com.android.volley.Response.Listener<String>() {
						@Override
						public void onResponse(String response) {
							progressDialog.dismiss();
							Log.i(TAG, response);



							try {
								JSONObject jsonObject = new JSONObject(response);
								String success = jsonObject.getString("success");
								JSONArray jsonArray = jsonObject.getJSONArray("read");

								if (success.equals("1")) {



										JSONObject object = jsonArray.getJSONObject(0);

										String strUuid = object.getString("uuid").trim();

										enviarDatos(strUuid);


								}

							} catch (JSONException e) {
								e.printStackTrace();
								progressDialog.dismiss();
								Toast.makeText(getActivity(), "Error de conexión ", Toast.LENGTH_SHORT).show();
							}

						}
					},
					new com.android.volley.Response.ErrorListener() {
						@Override
						public void onErrorResponse(VolleyError error) {
							progressDialog.dismiss();
							Toast.makeText(getActivity(), "Error de conexión  ", Toast.LENGTH_SHORT).show();
						}
					}) {
				@Override
				protected Map<String, String> getParams() {
					Map<String, String> params = new HashMap<>();
					params.put("id", id);
					return params;
				}
			};

			RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
			requestQueue.add(stringRequest);


	}

	private void enviarDatos(String valor) {
		txtuuid.setText(valor);
		Toast.makeText(getContext(), "el valor es" + valor, Toast.LENGTH_SHORT).show();
	}

	private void enviarMensajeTema(String nombreestudiante, String mensaje, String categoria){

		RequestQueue myRequest = Volley.newRequestQueue(getContext());
		JSONObject json  = new JSONObject();

		try {


			json.put("to", "/topics/"+"tutores");
			JSONObject notificacion = new JSONObject();

			notificacion.put("titulo", nombreestudiante);
			notificacion.put("detalle", mensaje);
			notificacion.put("categoria", categoria);

			json.put("data", notificacion);

			String URL = "https://fcm.googleapis.com/fcm/send";

			JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, URL, json,null, null){
				@Override
				public Map<String, String> getHeaders() throws AuthFailureError {
					Map<String, String> header = new HashMap<>();

					header.put("content-type", "application/json");
					header.put("authorization", "key=AAAAb-m5_F0:APA91bEZtZOAaL8YO7vy_y3PCO-Xg_0B6jfDSDRmYjbZyDtCnHOS9BdjlrBzR-aVuagDnCpEe4wWCsjyqdrh5oytC_qa6R-y7A7_4n1pwpUgJkNMbcye8KESJP0OhkyU6EKLZytrb2b0");

					return header;

				}
			};
			myRequest.add(request);
			if (categoria.equals("Consulta Offline")){
				guardarMensajeOffline(nombreestudiante, categoria, mensaje);
			}else{
				guardarMensajeOnline(nombreestudiante, categoria, mensaje);
			}


		}catch (JSONException e){
			e.printStackTrace();

		}
	}

	private void guardarMensajeOnline(String nombreestudiante, String categoria, String mensaje) {
		SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		String hora = s.format(new Date());
		DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
		String receptor ="vacio";
		String estado = "no resuelta";
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("id", firebaseUser.getUid());
		hashMap.put("remitente", nombreestudiante);
		hashMap.put("categoria", categoria);
		hashMap.put("mensaje", mensaje);
		hashMap.put("mensaje_receptor", receptor);
		hashMap.put("receptor", receptor);
		hashMap.put("estado", estado);
		hashMap.put("hora", hora);
		reference.child("ConsultasEnviadasOnline").push().setValue(hashMap);
		final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOnline")
				//.child(firebaseUser.getUid())
				.child(firebaseUser.getUid())
				.child(nombreestudiante);

		chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				if (!dataSnapshot.exists()){
					chatRef.child("id").child(firebaseUser.getUid());
				}
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
	}

	private void guardarMensajeOffline(final String nombreestudiante, final String categoria, String mensaje) {


		SimpleDateFormat s = new SimpleDateFormat("yyyy.MM.dd G 'at' HH:mm:ss z");
		String hora = s.format(new Date());
		DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
		String receptor ="vacio";
		String estado = "no resuelta";
		HashMap<String, Object> hashMap = new HashMap<>();
		hashMap.put("id", firebaseUser.getUid());
		hashMap.put("remitente", nombreestudiante);
		hashMap.put("categoria", categoria);
		hashMap.put("mensaje", mensaje);
		hashMap.put("receptor", receptor);
		hashMap.put("estado", estado);
		hashMap.put("hora",hora);
		reference.child("ConsultasEnviadasOffline").push().setValue(hashMap);
		final DatabaseReference chatRef = FirebaseDatabase.getInstance().getReference("ConsultasEnviadasOffline")
				//.child(firebaseUser.getUid())
				.child(firebaseUser.getUid())
				.child(nombreestudiante);

		chatRef.addListenerForSingleValueEvent(new ValueEventListener() {
			@Override
			public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
				if (!dataSnapshot.exists()){
					chatRef.child("id").child(firebaseUser.getUid());
				}
			}
			@Override
			public void onCancelled(@NonNull DatabaseError databaseError) {
			}
		});
		}


}
