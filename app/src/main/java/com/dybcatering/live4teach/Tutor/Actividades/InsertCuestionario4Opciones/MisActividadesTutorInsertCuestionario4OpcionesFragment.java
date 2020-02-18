package com.dybcatering.live4teach.Tutor.Actividades.InsertCuestionario4Opciones;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;
import com.geniusforapp.fancydialog.FancyAlertDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MisActividadesTutorInsertCuestionario4OpcionesFragment extends Fragment {
	public MisActividadesTutorInsertCuestionario4OpcionesFragment() {
		// Required empty public constructor
	}


	public Button guardar;

	private TextView txtpregunta, txtrespuestacorrecta, txtrespuestaincorrecta1, txtrespuestaincorrecta2, txtrespuestaincorrecta3;

	public String pregunta, respuestacorrecta, respuestaincorrecta1, respuestaincorrecta2, respuestaincorrecta3;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =inflater.inflate(R.layout.fragment_mis_actividades_tutor_insert_cuestionario4_opcionesfragment, container, false);

		guardar = view.findViewById(R.id.btnguardar);
		txtpregunta = view.findViewById(R.id.txtPregunta);
		txtrespuestacorrecta = view.findViewById(R.id.txtRespuestaCorrecta);
		txtrespuestaincorrecta1 = view.findViewById(R.id.txtRespuestaIncorrecta1);
		txtrespuestaincorrecta2 = view.findViewById(R.id.txtRespuestaIncorrecta2);
		txtrespuestaincorrecta3 = view.findViewById(R.id.txtRespuestaIncorrecta3);

		pregunta = txtpregunta.getText().toString();
		respuestacorrecta = txtrespuestacorrecta.getText().toString();
		respuestaincorrecta1 = txtrespuestaincorrecta1.getText().toString();
		respuestaincorrecta2 = txtrespuestaincorrecta2.getText().toString();
		respuestaincorrecta3 = txtrespuestaincorrecta3.getText().toString();


		guardar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {


				final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
						.setBackgroundColor(R.color.white)
						.setimageResource(R.drawable.ic_info)
						.setTextSubTitle("Registro Exitoso!")
						.setBody("Si deseas guardar mas preguntas durante el registro, por favor dale clic en Aceptar")
						.setPositiveButtonText("Aceptar")
						.setNegativeButtonText("Cancelar")
						.setPositiveColor(R.color.colorbonton)
						.setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
							@Override
							public void OnClick(View view, Dialog dialog) {
								dialog.dismiss();
								GuardarDatos(txtpregunta.getText().toString(), txtrespuestacorrecta.getText().toString(), txtrespuestaincorrecta2.getText().toString(), txtrespuestaincorrecta3.getText().toString(), txtrespuestaincorrecta1.getText().toString());
								txtpregunta.setText("");
								txtrespuestaincorrecta3.setText("");
								txtrespuestaincorrecta2.setText("");
								txtrespuestaincorrecta1.setText("");
								txtrespuestacorrecta.setText("");
							}
						})

						.setOnNegativeClicked(new FancyAlertDialog.OnNegativeClicked() {
							@Override
							public void OnClick(View view, Dialog dialog) {
								getActivity().finish();
								getActivity().finish();
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



		return view;
	}

	private void GuardarDatos(final String pregunta, final String respuestacorrecta, final String respuestaincorrecta1, final String respuestaincorrecta2, final String respuestaincorrecta3) {

		Bundle arguments = getArguments();

		final String idrecibido = arguments.getString("idactividad");
		arguments.putString("idactividad", idrecibido);

		String url = "https://dybcatering.com/back_live_app/miscursos/misactividades/tutor/insertarcuestionario4opciones.php";

		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");

							if (success.equals("1")) {

								Toast.makeText(getContext(), "Registro exitoso de la pregunta", Toast.LENGTH_SHORT).show();

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
				params.put("id_activity", idrecibido);
				params.put("question", pregunta);
				params.put("answer1", respuestaincorrecta1);
				params.put("correct_answer", respuestacorrecta);
				params.put("answer2", respuestaincorrecta2);
				params.put("answer3", respuestaincorrecta3);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}

}
