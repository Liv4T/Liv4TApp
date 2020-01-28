package com.dybcatering.live4teach.Splash.Estudiante.Perfil;

import android.app.ProgressDialog;
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
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class CambiarContrasenaFragment extends Fragment {

	public TextView contrasena, confirmar_contrasena;
	public Button btnConfirmar;
	public String stContrasena, stConfirmarContrasena;
	private static String URL_CHANGE_PASSWORD = "https://dybcatering.com/back_live_app/update_password.php";
	private HashMap<String,String> user;
	SessionManager sessionManager;

	String id;
	View myView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.cambiar_contrasena_fragment, container, false);
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id = user.get(SessionManager.ID);
		contrasena= myView .findViewById(R.id.password);
		confirmar_contrasena= myView .findViewById(R.id.password_confirmar);
		btnConfirmar = myView.findViewById(R.id.btn_confirmar);

		stContrasena= contrasena.getText().toString();
		stConfirmarContrasena = confirmar_contrasena.getText().toString();

		btnConfirmar.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (stContrasena.equals(stConfirmarContrasena)){

					enviarDatos();
				}else{
					contrasena.setError("Las contraseñas no coinciden");
					confirmar_contrasena.setError("Las contraseñas no coinciden");
				}
			}
		});

		return myView ;
	}

	public void enviarDatos(){
		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Guardando...");
		progressDialog.show();

		final String password = confirmar_contrasena.getText().toString();

		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CHANGE_PASSWORD,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();

						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");

							if (success.equals("1")) {
								Toasty.success(getContext(), "Exito al Actualizar tu contraseña", Toast.LENGTH_SHORT).show();
								//Toast.makeText(getActivity(), "Success!", Toast.LENGTH_SHORT).show();
							//	sessionManager.createSession(nombre, usuario, id, "3");
							}

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast.makeText(getActivity(), "Error " + e.toString(), Toast.LENGTH_SHORT).show();
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						progressDialog.dismiss();
						Toast.makeText(getActivity(), "Error " + error.toString(), Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				params.put("password", password);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);

	}

}
