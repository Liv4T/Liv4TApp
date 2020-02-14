package com.dybcatering.live4teach.Tutor.Actividades;

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

public class MisActividadesTutorInsertFragment extends Fragment {

	private static final String TAG = MisActividadesTutorInsertFragment.class.getSimpleName(); //getting the info
	private static String URL_READ = "https://dybcatering.com/back_live_app/miscursos/detail_course.php";
	SessionManager sessionManager;
	String id_usuario;
	private Spinner SpinnerCurso, SpinnerUnidad, SpinnerActividad;
	ArrayList<String> Curso;
	ArrayList<String> Unidad;
	private RequestQueue mRequestQueue;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mis_actividades_tutor_insert, container, false);
		// Inflate the layout for this fragment
		Curso = new ArrayList<>();
		Unidad = new ArrayList<>();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);
		SpinnerCurso = view.findViewById(R.id.spcurso);
		SpinnerUnidad = view.findViewById(R.id.spunidad);
		listarCurso(id_usuario);
		//listarUnidad();
		return view;
	}

	private void listarCurso(final String id){
		String URL_CARGAR = "https://dybcatering.com/back_live_app/miscursos/misactividades/tutor/listarcursos.php";
		RequestQueue requestQueue=Volley.newRequestQueue(getActivity().getApplicationContext());
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

				listarUnidad(SpinnerCurso.getSelectedItem().toString());

			}

			@Override
			public void onNothingSelected(AdapterView<?> parentView) {
				// your code here
			}

		});


	}

	private void listarUnidad(final String Curso){
			Unidad.clear();
			String URL_CARGAR = "https://dybcatering.com/back_live_app/miscursos/misactividades/tutor/listarunidad.php";
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
							if (name.isEmpty()){
								Unidad.clear();
							}else{

								Unidad.add(name);
							}
						}
						SpinnerUnidad.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Unidad));

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
					params.put("name", Curso);
					return params;
				}
			};
			int socketTimeout = 30000;
			RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
			stringRequest.setRetryPolicy(policy);
			requestQueue.add(stringRequest);

	}

}
