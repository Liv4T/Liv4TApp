package com.dybcatering.live4teach.Tutor.Calificaciones.InsertCalificaciones;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class CalificacionesTutorInsertFragment extends Fragment {

	public CalificacionesTutorInsertFragment() {
		// Required empty public constructor
	}

	SessionManager sessionManager;
	String id_usuario;
	private Spinner SpinnerCurso, SpinnerEstudianteInscrito, SpinnerActividad;
	private Button btnregistrar, btnfecha, btnultimafecha;
	private EditText txtfase1, txtfase2, componente10, componente9, componente8, componente7, componente6, subtotal, total, observacion;
	private TextView labelfecha, labelultimafecha;
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
		btnfecha = view.findViewById(R.id.btnfecha);
		labelfecha = view.findViewById(R.id.labelfecha);
		btnultimafecha= view.findViewById(R.id.btnfechamaxima);
		labelultimafecha = view.findViewById(R.id.labelfechamaxima);
		componente10 = view.findViewById(R.id.txtComponente10);
		componente9= view.findViewById(R.id.txtComponente9);
		componente8 = view.findViewById(R.id.txtComponente8);
		componente7  = view.findViewById(R.id.txtComponente7);
		componente6  = view.findViewById(R.id.txtComponente6);
		subtotal = view.findViewById(R.id.txtSubTotal);
		total = view.findViewById(R.id.txtTotal);
		observacion = view.findViewById(R.id.txtObservacion);
		requestQueue = Volley.newRequestQueue(getActivity());
		btnregistrar = view.findViewById(R.id.btnRegistrarCalificacion);
		listarCurso(id_usuario);
		btnfecha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

							}
						}, year, month, dayOfMonth);
				labelfecha.setText(dayOfMonth+ "-" + (month + 1) + "-" + year);
				datePickerDialog.show();
			}
		});
		btnultimafecha.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				final int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

							}
						}, year, month, dayOfMonth);
				labelultimafecha.setText(dayOfMonth+ "-" + (month + 1) + "-" + year);
				datePickerDialog.show();
			}
		});
		btnregistrar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (componente10.getText().toString().isEmpty() || componente9.getText().toString().isEmpty() || componente8.getText().toString().isEmpty() ||
						componente6.getText().toString().isEmpty() || subtotal.getText().toString().isEmpty() || total.getText().toString().isEmpty() ||
						observacion.getText().toString().isEmpty()){
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

				}else{
					GuardarDatos(id_usuario);
				}
			}
		});
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
					listarEstudianteInscrito(SpinnerCurso.getSelectedItem().toString());
					listarActividad(SpinnerCurso.getSelectedItem().toString());
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



				}

				@Override
				public void onNothingSelected(AdapterView<?> parentView) {
					// your code here
				}

			});
		}

	private void listarActividad(final String Curso) {
		Actividad.clear();
		String URL_CARGAR = "https://dybcatering.com/back_live_app/calificaciones/listaractividades.php";
		RequestQueue requestQueue=Volley.newRequestQueue(getActivity().getApplicationContext());
		StringRequest stringRequest=new StringRequest(Request.Method.POST, URL_CARGAR, new Response.Listener<String>() {
			@Override
			public void onResponse(String response) {
				try{
					JSONObject jsonObject=new JSONObject(response);
					JSONArray jsonArray=jsonObject.getJSONArray("Registros");
					for(int i=0;i<jsonArray.length();i++){
						JSONObject jsonObject1=jsonArray.getJSONObject(i);
						String name=jsonObject1.getString("activity");
						if (name.isEmpty()){
							Actividad.clear();
						}else{

							Actividad.add(name);
						}
					}
					SpinnerActividad.setAdapter(new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, Actividad));

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


	}
	public void ObtenerUsuario(){

	}

	public void GuardarDatos(String id_usuario){
		final String stSpinnerCurso = this.SpinnerCurso.getSelectedItem().toString();
		final String stSpinnerEstudianteInscrito = this.SpinnerEstudianteInscrito.getSelectedItem().toString();
		final String stSpinnerActividad = this.SpinnerActividad.getSelectedItem().toString();
		final String stFecha = this.labelfecha.getText().toString();
		final String stUltimaFecha = this.labelultimafecha.getText().toString();
		final String stComponente10 = this.componente10.getText().toString();
		final String stComponente9 = this.componente9.getText().toString();
		final String stComponente8 = this.componente8.getText().toString();
		final String stComponente7 = this.componente7.getText().toString();
		final String stComponente6 = this.componente6.getText().toString();
		final String stSubtotal = this.subtotal.getText().toString();
		final String stTotal = this.total.getText().toString();
		final String stObservacion = this.observacion.getText().toString();

		String url = "https://dybcatering.com/back_live_app/calificaciones/insertarcalificacion.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");

							if (success.equals("1")) {
								final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
										.setBackgroundColor(R.color.white)
										.setimageResource(R.drawable.ic_info)
										.setTextSubTitle("Registro Exitoso!")
										.setBody("")
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
							} else if (success.equals("2")){
								final Dialog mailDialog = new Dialog(getActivity());
								mailDialog.getWindow();
								android.app.AlertDialog alertDialog = new android.app.AlertDialog.Builder(getActivity(), R.style.AppTheme).create();
								alertDialog.setTitle("Error");
								alertDialog.setMessage("Error en el registro, intenta nuevamente");
								alertDialog.setButton(android.app.AlertDialog.BUTTON_NEUTRAL, "OK",
										new DialogInterface.OnClickListener() {
											public void onClick(DialogInterface dialog, int which) {

											}
										});
								alertDialog.show();
							}


						} catch (JSONException e) {
							e.printStackTrace();
							Toast.makeText(getActivity(), "Algo salio mal! " + e.toString(), Toast.LENGTH_SHORT).show();

						}
					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Toast.makeText(getActivity(), "Algo salio mal! " + error.toString(), Toast.LENGTH_SHORT).show();

					}
				})

		{
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
			/*	params.put("id_courses", stSpinnerCurso);
				params.put("id_unit", stSpinnerUnidad);
				params.put("id_user", id);
				params.put("activitytype", stSpinnerTipoActividad);
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
				*/
				return params;

			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}
}
