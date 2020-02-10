package com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAID;

public class AcercaCursoActivity extends AppCompatActivity {

	TextView textView;
	private static String URL_READ = "https://dybcatering.com/back_live_app/miscursos/detail_course.php";

	private static final String TAG = AcercaCursoActivity.class.getSimpleName(); //getting the info
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_acerca_curso);
		textView = findViewById(R.id.textdescripcion);
		Intent intent = getIntent();
		final String enviado = intent.getStringExtra(EXTRAID);

		getCourseDetail(enviado);
		Toolbar toolbar = findViewById(R.id.toolbarId);
		setSupportActionBar(toolbar);
		ActionBar actionBar = getSupportActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		actionBar.setTitle("Descripción del curso");
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				AcercaCursoActivity.this.onBackPressed();
			}
		});

	}


	private void getCourseDetail(final String id) {

		final ProgressDialog progressDialog = new ProgressDialog(AcercaCursoActivity.this);
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

									String strDescription = object.getString("description").trim();
									textView.setText(strDescription);

								}

							}

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast.makeText(AcercaCursoActivity.this, "Error de conexión ", Toast.LENGTH_SHORT).show();
						}

					}
				},
				new Response.ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						progressDialog.dismiss();
						Toast.makeText(AcercaCursoActivity.this, "Error de conexión  ", Toast.LENGTH_SHORT).show();
					}
				}) {
			@Override
			protected Map<String, String> getParams() {
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(AcercaCursoActivity.this);
		requestQueue.add(stringRequest);

	}


}
