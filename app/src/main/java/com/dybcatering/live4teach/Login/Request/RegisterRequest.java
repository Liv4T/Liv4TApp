package com.dybcatering.live4teach.Login.Request;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 *	Creado por Daniel Buitrago
 * Clase utilizada para el envio de peticiones a un servidor o URL o servidor teniendo en cuenta los parametros
 * enviados
 */


public class RegisterRequest extends StringRequest {

	private static final String REGISTER_URL = "https://dybcatering.com/back_live_app/register.php";
	private Map<String, String> parameters;
	public RegisterRequest(String uuid,String name, String last_name, String email, String user_name, String password, String age, String birthday, String phone,  Response.Listener<String> listener) {
		super(Method.POST, REGISTER_URL, listener, null);
		parameters = new HashMap<>();
		parameters.put("uuid", uuid);
		parameters.put("name", name);
		parameters.put("last_name", last_name);
		parameters.put("email", email);
		parameters.put("user_name", user_name);
		parameters.put("password", password);
		parameters.put("age", age);
		parameters.put("birthday", birthday);
		parameters.put("phone", phone);
		//parameters.put("image", photo);

	}
	@Override
	protected Map<String, String> getParams() throws AuthFailureError {
		return parameters;
	}
}
