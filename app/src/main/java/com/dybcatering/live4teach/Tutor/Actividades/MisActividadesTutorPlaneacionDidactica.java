package com.dybcatering.live4teach.Tutor.Actividades;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Actividades.InsertCuestionario4Opciones.MisActividadesTutorInsertCuestionario4Opciones;
import com.geniusforapp.fancydialog.FancyAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class MisActividadesTutorPlaneacionDidactica extends Fragment {

	public MisActividadesTutorPlaneacionDidactica() {
		// Required empty public constructor
	}
	private Button btnfechadesde, btnfechahasta, btnfecharetroalimentacion, btnplaneaciondidactica;

	private TextView labelfechadesde, labelfechahasta, labelfecharetroalimentacion;

	private EditText tiempotrabajo, actividad, actorinterviniente, evidencia, envio;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_mis_actividades_tutor_planeacion_didactica, container, false);
		tiempotrabajo = view.findViewById(R.id.txtTiempoTrabajo);
		actividad = view.findViewById(R.id.txtActividad);
		labelfechadesde = view.findViewById(R.id.labelFechadesde);
		labelfechahasta = view.findViewById(R.id.labelfechahasta);
		evidencia = view.findViewById(R.id.txtEvidencia);

		actorinterviniente = view.findViewById(R.id.txtActorInterviniente);
		labelfecharetroalimentacion = view.findViewById(R.id.labelFechaRetroalimentacion);
		btnfechadesde = view.findViewById(R.id.btnFechaDesde);
		btnfechahasta = view.findViewById(R.id.btnFechaHasta);
		btnfecharetroalimentacion = view.findViewById(R.id.btnFechaRetroAlimentacion);
		btnplaneaciondidactica = view.findViewById(R.id.btnGuardarPlaneacionDidactica);

		btnfechadesde.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

							}
						}, year, month, dayOfMonth);
				datePickerDialog.show();
				labelfechadesde.setText(dayOfMonth+ "-" + (month + 1) + "-" + year);
			}
		});
		btnfechahasta.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

							}
						}, year, month, dayOfMonth);
				datePickerDialog.show();
				datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
				labelfechahasta.setText(dayOfMonth+ "-" + (month + 1) + "-" + year);
			}
		});
		btnfecharetroalimentacion.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar calendar = Calendar.getInstance();
				int year = calendar.get(Calendar.YEAR);
				int month = calendar.get(Calendar.MONTH);
				int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
				DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
						new DatePickerDialog.OnDateSetListener() {
							@Override
							public void onDateSet(DatePicker datePicker, int year, int month, int day) {

							}
						}, year, month, dayOfMonth);
				datePickerDialog.show();
				datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
				labelfecharetroalimentacion.setText(dayOfMonth+ "/" + (month + 1) + "/" + year);
			}
		});
		btnplaneaciondidactica.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
					if (tiempotrabajo.getText().toString().isEmpty() || actividad.getText().toString().isEmpty() || labelfechadesde.getText().toString().isEmpty() ||
						labelfechahasta.getText().toString().isEmpty() || evidencia.getText().toString().isEmpty() || actorinterviniente.getText().toString().isEmpty() ||
					labelfecharetroalimentacion.getText().toString().isEmpty()){

					}else{
						Bundle arguments = getArguments();

						String idrecibido = arguments.getString("idactividad");
						Toast.makeText(getContext(), "el id recibido de la actividad es " + idrecibido, Toast.LENGTH_SHORT).show();
						GuardarDatos(idrecibido);
					}
			}
		});

		return view;
	}

	public void GuardarDatos(final String id){
		final String stTiempoTrabajo= this.tiempotrabajo.getText().toString();

		final String stFechaDesde= this.labelfechadesde.getText().toString();
		final String stFechaHasta=this.labelfechahasta.getText().toString().trim();
		final String stEvidencia= this.evidencia.getText().toString().trim();

		final String stActorInterviniente= this.actorinterviniente.getText().toString().trim();
		final String stFechaRetroAlimentacion = this.labelfecharetroalimentacion.getText().toString().trim();

		String url = "https://dybcatering.com/back_live_app/miscursos/misactividades/tutor/insertarplaneaciondidactica.php";

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
										.setBody("Debes Registrar el tipo de actividad ")
										.setPositiveButtonText("Aceptar")
										.setPositiveColor(R.color.colorbonton)
										.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
											@Override
											public void OnClick(View view, Dialog dialog) {
												dialog.dismiss();
												iniciarTransicionGuardarActividad();
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
				params.put("id", id);
				params.put("work_time", stTiempoTrabajo);
				params.put("moment_evaluation_from", stFechaDesde);
				params.put("moment_evaluation_up", stFechaHasta);
				params.put("evidence_send", stEvidencia);
				params.put("intervening_actor", stActorInterviniente);
				params.put("feedback_date", stFechaRetroAlimentacion);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}

	private void iniciarTransicionGuardarActividad() {
		Fragment someFragment = new MisActividadesTutorInsertCuestionario4Opciones();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		Bundle arguments = getArguments();

		String idrecibido = arguments.getString("idactividad");
		arguments.putString("idactividad", idrecibido);
		//arguments.putString("daniel", "hola");
		someFragment.setArguments(arguments);
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}

}
