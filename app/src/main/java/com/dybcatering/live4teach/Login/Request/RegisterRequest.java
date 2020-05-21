package com.dybcatering.live4teach.Login.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *	Creado por Daniel Buitrago
 * Clase utilizada para el envio de peticiones a un servidor o URL teniendo en cuenta los parametros
 * enviados
 */


public class RegisterRequest extends StringRequest {

	private static final String REGISTER_URL = "http://192.168.0.11/webdyb/back_live_app/register.php";
	private Map<String, String> parameters;
	public RegisterRequest(String name, String last_name, String type_user, String address, String picture, String phone, String id_number, String user_name, String email, String password,  Response.Listener<String> listener) {
		super(Method.POST, REGISTER_URL, listener, null);
		parameters = new HashMap<>();

		parameters.put("name", name);
		parameters.put("last_name", last_name);
		parameters.put("type_user", type_user);
		parameters.put("address", address);
		parameters.put("picture", picture);
		parameters.put("phone", phone);
		parameters.put("id_number", id_number);
		parameters.put("user_name", user_name);
		parameters.put("email", email);
		parameters.put("password", password);
		//parameters.put("image", photo);

	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return parameters;
	}
}
