package com.dybcatering.live4teach.Tutor.Calificaciones.InsertCalificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CalificacionesTutorInsertFragment extends Fragment {

	public CalificacionesTutorInsertFragment() {
		// Required empty public constructor
	}

	SessionManager sessionManager;
	String id_usuario;
	private Spinner SpinnerCurso, SpinnerEstudianteInscrito, SpinnerActividad;
	ArrayList<String> Curso;
	ArrayList<String> EstudianteInscrito;
	ArrayList<String> Actividad;
	RequestQueue requestQueue;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_calificaciones_tutor_insert, container, false);
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);
		Curso = new ArrayList<>();
		EstudianteInscrito = new ArrayList<>();
		Actividad = new ArrayList<>();
		SpinnerCurso = view.findViewById(R.id.spinnerCurso);
		SpinnerEstudianteInscrito = view.findViewById(R.id.spinnerEstudiante);
		SpinnerActividad = view.findViewById(R.id.spinnerActividad);
		requestQueue = Volley.newRequestQueue(getActivity());
		listarCurso(id_usuario);
		return view;
	}

		private void listarCurso(final String id){
			Curso.clear();
			String URL_CARGAR = "https://dybcatering.com/back_live_app/calificaciones/listarcursos.php";
			RequestQueue requestQueue= Volley.newRequestQueue(getActivity().getApplicationContext());
			StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CARGAR, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try{
						JSONObject jsonObject=new JSONObject(response);
						JSONArray jsonArray=jsonObject.getJSONArray("Registros");
						for(int i=0;i<jsonArray.length();i++){
							JSONObject jsonObject1=jsonArray.getJSONObject(i);
							String curso=jsonObject1.getString("name");
							Curso.add(curso);
						}
						SpinnerCurso.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Curso));

					}catch (JSONException e){
						e.printStackTrace();
						Toast.makeText(getContext(), "algo salio mal" + e, Toast.LENGTH_SHORT).show();}
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					error.printStackTrace();
					Toast.makeText(getActivity(), "algo salio mal" + error, Toast.LENGTH_SHORT).show();
				}
			}) {
				@Override
				protected Map<String, String> getParams() {
					Map<String, String> params = new HashMap<>();
					params.put("id", id);
					return params;
				}
			};
			int socketTimeout = 30000;
			RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
			stringRequest.setRetryPolicy(policy);
			requestQueue.add(stringRequest);
			SpinnerCurso.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
					// your code here
					//listarEstudianteInscrito(SpinnerCurso.getSelectedItem().toString());
					listarEstudianteInscrito(SpinnerCurso.getSelectedItem().toString());
				}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
					// your code here
				}

			});

		}



		private void listarEstudianteInscrito(final String Curso) {
			EstudianteInscrito.clear();
			String URL_CARGAR = "https://dybcatering.com/back_live_app/calificaciones/listarestudiantesinscritos.php";
			RequestQueue requestQueue=Volley.newRequestQueue(getActivity().getApplicationContext());
			StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CARGAR, new Response.Listener<String>() {
				@Override
				public void onResponse(String response) {
					try{
						JSONObject jsonObject=new JSONObject(response);
						JSONArray jsonArray=jsonObject.getJSONArray("Registros");
						for(int i=0;i<jsonArray.length();i++){
							JSONObject jsonObject1=jsonArray.getJSONObject(i);
							String name=jsonObject1.getString("name");
							String last_name = jsonObject1.getString("last_name");
							if (name.isEmpty()){
								EstudianteInscrito.clear();
							}else{

								EstudianteInscrito.add(name+" "+last_name);
							}
						}
						SpinnerEstudianteInscrito.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, EstudianteInscrito));

					}catch (JSONException e){
						//EstudianteInscrito.removeAll(EstudianteInscrito);
						e.printStackTrace();
						Toast.makeText(getContext(), "algo salio mal" + e, Toast.LENGTH_SHORT).show();
					}
				}
			}, new Response.ErrorListener() {
				@Override
				public void onErrorResponse(VolleyError error) {
					error.printStackTrace();
					Toast.makeText(getActivity(), "algo salio mal" + error, Toast.LENGTH_SHORT).show();
				}
			}) {
				@Override
				protected Map<String, String> getParams() {
					Map<String, String> params = new HashMap<>();
					params.put("name", Curso);
					return params;
				}
			};
			int socketTimeout = 30000;
			RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
			stringRequest.setRetryPolicy(policy);
			requestQueue.add(stringRequest);
			SpinnerEstudianteInscrito.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
				@Override
				public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
					// your code here

					listarActividad(SpinnerEstudianteInscrito.getSelectedItem().toString());

				}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
					// your code here
				}

			});
		}

	private void listarActividad(final String EstudianteInscrito) {
	}
}
