package com.dybcatering.live4teach.Tutor.Actividades;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor.AdaptadorMisActividadesTutor;
import com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor.ItemAdaptadorActividadesTutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class MisActividadesTutorFragment extends Fragment  implements AdaptadorMisActividadesTutor.OnItemClickListener {

	View view;
	private static final String TAG = MisActividadesTutorFragment.class.getSimpleName(); //getting the info
	String id_usuario;
	private RecyclerView mRecyclerView;
	private AdaptadorMisActividadesTutor misActividadesAdaptor;
	private ArrayList<ItemAdaptadorActividadesTutor> mactividadesItems;
	private RequestQueue mRequestQueue;
	private FloatingActionButton fab;
	SessionManager sessionManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_mis_actividades_tutor, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);

		mRecyclerView = view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mactividadesItems = new ArrayList<>();

		mRequestQueue = Volley.newRequestQueue(getActivity());

		ObtenerDatos(id_usuario);
		fab = view.findViewById(R.id.fab);
		ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
		anim.setFillBefore(true);
		anim.setFillAfter(true);
		anim.setFillEnabled(true);
		anim.setDuration(1500);
		anim.setInterpolator(new OvershootInterpolator());
		fab.startAnimation(anim);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iniciartransicion();
			}
		});
		return view;

	}

	public void iniciartransicion(){
		Fragment someFragment = new MisActividadesTutorInsertFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();

	}



	@Override
	public void onItemClick(int position) {

	}

	private void ObtenerDatos(final String id) {

		String url = "http://dybcatering.com/back_live_app/miscursos/misactividades/tutor/actividadestutor.php";
		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);
		StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						Log.i(TAG, response);
						progressDialog.dismiss();
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray jsonArray = jsonObject.getJSONArray("Registros");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject hit = jsonArray.getJSONObject(i);
								String id = hit.getString("id");
								String nombrecurso = hit.getString("nombrecurso");
								String name = hit.getString("name");
								String id_user = hit.getString("id_user");
								String activitytype = hit.getString("activitytype");
								String estimated_duration_platform = hit.getString("estimated_duration_platform");
								String estimated_duration_autonomous_work = hit.getString("estimated_duration_autonomous_work");
								String theme_contextualization = hit.getString("theme_contextualization");
								String activity = hit.getString("activity");
								String type_resources_1 = hit.getString("type_resources_1");
								String type_resources_2 = hit.getString("type_resources_2");
								String type_resources_3 = hit.getString("type_resources_3");
								String origin_resource1 = hit.getString("origin_resource1");
								String origin_resource2 = hit.getString("origin_resource2");
								String origin_resource3 = hit.getString("origin_resource3");
								String deliverables = hit.getString("deliverables");
								String evaluation_criteria1 = hit.getString("evaluation_criteria1");
								String evaluation_criteria2 = hit.getString("evaluation_criteria2");
								String evaluation_criteria3 = hit.getString("evaluation_criteria3");
								String work_time = hit.getString("work_time");
								String moment_evaluation_from = hit.getString("moment_evaluation_from");
								String moment_evaluation_up = hit.getString("moment_evaluation_up");
								String evidence_send = hit.getString("evidence_send");
								String intervening_actor = hit.getString("intervening_actor");
								String feedback_date = hit.getString("feedback_date");
								String name_category = hit.getString("name_category");

								mactividadesItems.add(new ItemAdaptadorActividadesTutor(id, nombrecurso, name, id_user, activitytype, estimated_duration_platform, estimated_duration_autonomous_work,
										theme_contextualization, activity, type_resources_1, type_resources_2, type_resources_3, origin_resource1, origin_resource2, origin_resource3, deliverables, evaluation_criteria1,
										evaluation_criteria2, evaluation_criteria3, work_time, moment_evaluation_from, moment_evaluation_up, evidence_send, intervening_actor, feedback_date, name_category));
							}
							misActividadesAdaptor = new AdaptadorMisActividadesTutor(getActivity(), mactividadesItems);
							mRecyclerView.setAdapter(misActividadesAdaptor);
							misActividadesAdaptor.setOnClickItemListener(MisActividadesTutorFragment.this);

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast toast = Toast.makeText(getContext(),
									"Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_HORIZONTAL, 0, 0);
							toast.show();
							//Toasty toasty = Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();

						}
					}
				}, new Response.ErrorListener() {
			@Override
			public void onErrorResponse(VolleyError error) {
				error.printStackTrace();
				progressDialog.dismiss();
				Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();
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