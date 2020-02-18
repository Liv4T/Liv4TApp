package com.dybcatering.live4teach.Tutor.Perfil;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PerfilFragmentFotoPerfilTutor extends Fragment {

	public PerfilFragmentFotoPerfilTutor() {
		// Required empty public constructor
	}
	SessionManager sessionManager;
	String id;
	private static final String TAG = PerfilFragmentFotoPerfilTutor.class.getSimpleName(); //getting the info
	private static String URL_READ = "https://dybcatering.com/back_live_app/read_detail.php";
	ImageView primage;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_perfil_fragment_foto_perfil, container, false);

		primage = view.findViewById(R.id.primage);
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id = user.get(SessionManager.ID);
		getUserDetail(id);
		return view;
	}

	private void getUserDetail(final String id) {

		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);
		StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();
						Log.i(TAG, response);

						try {
							JSONObject jsonObject = new JSONObject(response);
							String success = jsonObject.getString("success");
							JSONArray jsonArray = jsonObject.getJSONArray("read");

							if (success.equals("1")) {

								for (int i = 0; i < jsonArray.length(); i++) {

									JSONObject object = jsonArray.getJSONObject(i);


									String strPicture= object.getString("picture").trim();

									if (strPicture.equals("")) {
										primage.setImageResource(R.drawable.imagenperfil);
									} else {
										Picasso.with(getActivity()).load(strPicture).into(primage);
									}


								}


							}

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast.makeText(getActivity(), "Error de conexión ", Toast.LENGTH_SHORT).show();
						}

					}
				},
				new Response.ErrorListener() {
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


}
