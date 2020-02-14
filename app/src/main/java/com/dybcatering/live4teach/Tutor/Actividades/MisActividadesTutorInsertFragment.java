package com.dybcatering.live4teach.Tutor.Actividades;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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
import com.geniusforapp.fancydialog.FancyAlertDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MisActividadesTutorInsertFragment extends Fragment {

	private static final String TAG = MisActividadesTutorInsertFragment.class.getSimpleName(); //getting the info
	private static String URL_READ = "https://dybcatering.com/back_live_app/miscursos/detail_course.php";
	SessionManager sessionManager;
	String id_usuario;
	private Spinner SpinnerCurso, SpinnerUnidad, SpinnerActividad;
	ArrayList<String> Curso;
	ArrayList<String> Unidad;
	private RequestQueue mRequestQueue;
	private Button btninfocontext, btnactividad, btnentregables, btnregistrarse;
	private EditText edttiempoestimado, edttrabajoautonomo, edtcontextualizacion, edtactividad,
					edttiporecursos1, edttiporecursos2, edttiporecursos3,
					origenrecursos1, origenrecursos2, origenrecursos3,
					edtentregables, criteriosevaluacion1, criteriosevaluacion2,
					criteriosevaluacion3, evidenciasasociadas;
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
		SpinnerActividad = view.findViewById(R.id.sptipoactividad);
		edttiempoestimado = view.findViewById(R.id.edtTiempoEstimadoDuracion);
		edttrabajoautonomo = view.findViewById(R.id.edtTiempoTrabajoAutonomo);
		edtcontextualizacion = view.findViewById(R.id.edtContextualizacionTema);
		edtactividad = view.findViewById(R.id.edtActividad);
		edttiporecursos1= view.findViewById(R.id.edtTipoRecursos1);
		origenrecursos1 = view.findViewById(R.id.edtOrigenRecursos1);
		edttiporecursos2 = view.findViewById(R.id.edtTipoRecursos2);
		origenrecursos2 = view.findViewById(R.id.edtOrigenRecursos2);
		edttiporecursos3= view.findViewById(R.id.edtTipoRecursos3);
		origenrecursos3= view.findViewById(R.id.edtOrigenRecursos3);
		edtentregables = view.findViewById(R.id.edtEntregables);
		criteriosevaluacion1 = view.findViewById(R.id.edtCriteriosEvaluacion1);
		criteriosevaluacion2 = view.findViewById(R.id.edtCriteriosEvaluacion2);
		criteriosevaluacion3 = view.findViewById(R.id.edtCriteriosEvaluacion3);
		evidenciasasociadas = view.findViewById(R.id.edtEvidenciasAsociadas);
		btninfocontext = view.findViewById(R.id.btninfocontext);
		btnactividad = view.findViewById(R.id.btnactividadcontext);
		btnentregables = view.findViewById(R.id.btnentregables);
		btnregistrarse = view.findViewById(R.id.btnRegistrar);
		btninfocontext.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
						.setBackgroundColor(R.color.white)
						.setimageResource(R.drawable.ic_info)
						.setTextSubTitle("Contextualización del Tema")
						.setBody("Redactar la contextualización del tema a la que se va a referir la actividad planteada")
						.setPositiveButtonText("Aceptar")
						.setPositiveColor(R.color.colorbonton)
						.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
							@Override
							public void OnClick(View view, Dialog dialog) {
									dialog.dismiss();
								}
						})
						.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
						.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setCancelable(false)
						.build();
				alert.show();
			}
		});
		btnactividad.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
						.setBackgroundColor(R.color.white)
						.setimageResource(R.drawable.ic_info)
						.setTextSubTitle("Actividad")
						.setBody("Describir de forma clara y sencilla la actividad que debe realizar de acuerdo con el tema y el propósito a evaluar. (referir cuales son los recursos con los que cuenta el estudiante, Ej. Videos, lecturas, vClass y otros)")
						.setPositiveButtonText("Aceptar")
						.setPositiveColor(R.color.colorbonton)
						.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
							@Override
							public void OnClick(View view, Dialog dialog) {
								dialog.dismiss();
							}
						})
						.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
						.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setCancelable(false)
						.build();
				alert.show();
			}
		});
		btnentregables.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
						.setBackgroundColor(R.color.white)
						.setimageResource(R.drawable.ic_info)
						.setTextSubTitle("Entregables")
						.setBody("Describir de forma clara y sencilla los entregables que debe enviar el estudiante al tutor de acuerdo con el tema y el propósito a evaluar.")
						.setPositiveButtonText("Aceptar")
						.setPositiveColor(R.color.colorbonton)
						.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
							@Override
							public void OnClick(View view, Dialog dialog) {
								dialog.dismiss();
							}
						})
						.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
						.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
						.setCancelable(false)
						.build();
				alert.show();
			}
		});
		btnregistrarse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (edttiempoestimado.getText().toString().isEmpty() || edttrabajoautonomo.getText().toString().isEmpty() ||
						edtcontextualizacion.getText().toString().isEmpty() || edtactividad.getText().toString().isEmpty() ||
						edttiporecursos1.getText().toString().isEmpty() || origenrecursos1.getText().toString().isEmpty() ||
						edttiporecursos2.getText().toString().isEmpty() || origenrecursos2.getText().toString().isEmpty() ||
						edttiporecursos3.getText().toString().isEmpty() || origenrecursos3.getText().toString().isEmpty() ||
						edtentregables.getText().toString().isEmpty() || criteriosevaluacion1.getText().toString().isEmpty() ||
						criteriosevaluacion2.getText().toString().isEmpty() || criteriosevaluacion3.getText().toString().isEmpty() ||
						evidenciasasociadas.getText().toString().isEmpty()){


					final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
							.setBackgroundColor(R.color.white)
							.setimageResource(R.drawable.incompleto)
							.setTextTitle("Campos Incompletos")
							.setBody("Por favor valida que todos los campos esten diligenciados correctamente")
							.setPositiveButtonText("Aceptar")
							.setPositiveColor(R.color.colorbonton)
							.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
								@Override
								public void OnClick(View view, Dialog dialog) {
									dialog.dismiss();
								}
							})
							.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
							.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
							.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
							.setCancelable(false)
							.build();
					alert.show();


				}else{

					Guardar();
				}



			}
		});
		listarCurso(id_usuario);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),  R.array.Actividades, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		SpinnerActividad.setAdapter(adapter);


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

	public void Guardar(){
		final String stSpinnerCurso = this.SpinnerCurso.getSelectedItem().toString();
		final String stSpinnerUnidad = this.SpinnerUnidad.getSelectedItem().toString();
		final String stSpinnerTipoActividad = this.SpinnerActividad.getSelectedItem().toString();
		final String stTiempoestimado =this.edttiempoestimado.getText().toString().trim();
		final String stTrabajoautonomo = this.edttrabajoautonomo.getText().toString().trim();
		final String stContextualizacoin = this.edtcontextualizacion.getText().toString().trim();
		final String stActividad = this.edtactividad.getText().toString().trim();
		final String stTipoRecursos1 = this.edttiporecursos1.getText().toString().trim();
		final String stTipoRecursos2 = this.edttiporecursos2.getText().toString().trim();
		final String stTipoRecursos3 = this.edttiporecursos3.getText().toString().trim();
		final String stOrigenRecursos1= this.origenrecursos1.getText().toString().trim();
		final String stOrigenRecursos2= this.origenrecursos2.getText().toString().trim();
		final String stOrigenRecursos3= this.origenrecursos3.getText().toString().trim();
		final String stentregables = this.edtentregables.getText().toString().trim();
		final String stcriteriosevaluacion1= this.criteriosevaluacion1.getText().toString().trim();
		final String stcriteriosevaluacion2= this.criteriosevaluacion2.getText().toString().trim();
		final String stcriteriosevaluacion3= this.criteriosevaluacion3.getText().toString().trim();
		final String stevidenciasasociadas= this.evidenciasasociadas.getText().toString().trim();

		String url = "";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");

							if (success.equals("1")) {
								Toasty.error(getActivity(), "Registro exitoso!", Toast.LENGTH_SHORT).show();
								//startActivity(new Intent(Re.this, LoginActivity.class));
							} else if (success.equals("2")){
								final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
										.setBackgroundColor(R.color.white)
										.setimageResource(R.drawable.incompleto)
										.setTextTitle("Error en el registro")
										.setBody("Por favor valida que todos los campos esten diligenciados correctamente")
										.setPositiveButtonText("Aceptar")
										.setPositiveColor(R.color.colorbonton)
										.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
											@Override
											public void OnClick(View view, Dialog dialog) {
												dialog.dismiss();
											}
										})
										.setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
										.setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
										.setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
										.setCancelable(false)
										.build();
								alert.show();
							}


						} catch (JSONException e) {
							e.printStackTrace();
							Toasty.error(getActivity(), "Algo salio mal! " + e.toString(), Toast.LENGTH_SHORT).show();

						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toasty.error(getActivity(), "Algo salio mal! " + error.toString(), Toast.LENGTH_SHORT).show();

					}
				})

		{
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				params.put("NombreCurso", stSpinnerCurso);
				params.put("NombreUnidad", stSpinnerUnidad);
				params.put("TipoActividad", stSpinnerTipoActividad);
				params.put("estimated_duration_platform", stTiempoestimado);
				params.put("estimated_duration_autonomous_work", stTrabajoautonomo);
				params.put("theme_contextualization", stContextualizacoin);
				params.put("activity", stActividad);
				params.put("type_resources_1", stTipoRecursos1);
				params.put("type_resources_2", stTipoRecursos2);
				params.put("type_resources_3", stTipoRecursos3);
				params.put("origin_resource1", stOrigenRecursos1);
				params.put("origin_resource2", stOrigenRecursos2);
				params.put("origin_resource3", stOrigenRecursos3);
				params.put("deliverables", stentregables);
				params.put("evaluation_criteria1", stcriteriosevaluacion1);
				params.put("evaluation_criteria2", stcriteriosevaluacion2);
				params.put("evaluation_criteria3", stcriteriosevaluacion3);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);


	}

}
