package com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.AdaptorMisActividades.ItemMisActividades;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.AdaptorMisActividades.MisActividadesAdaptor;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.MisActividadesDetalle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class AcercaCursoFragment extends Fragment implements MisActividadesAdaptor.OnItemClickListener{
	View view;
	public AcercaCursoFragment() {
		// Required empty public constructor
	}
	LinearLayout acercade, compartir, actividades;
	public static final String EXTRAID= "id";
	public static final String EXTRANOMBRE = "name";
	public static final String EXTRANOMBRECURSO = "nombrecurso";
	public static final String EXTRAIDUSER = "id_user";
	public static final String EXTRAACTIVITYTYPE = "activitytype";
	public static final String EXTRAESTIMATEDDURATIONPLATFORM = "estimated_duration_platform";
	public static final String EXTRAESTIMATEDDURATIONAUTONOMOUSWORK="estimated_duration_autonomous_work";
	public static final String EXTRATHEMECONTEXTUALIZATION="theme_contextualization";
	public static final String EXTRAACTIVITY="activity";
	public static final String EXTRATYPERESOURCES1 = "type_resources_1";
	public static final String EXTRATYPERESOURCES2 = "type_resources_2";
	public static final String EXTRATYPERESOURCES3 = "type_resources_3";
	public static final String EXTRAORIGINRESOURCES1 = "origin_resource1";
	public static final String EXTRAORIGINRESOURCES2 = "origin_resource2";
	public static final String EXTRAORIGINRESOURCES3 = "origin_resource3";
	public static final String EXTRADELIVERABLES = "deliverables";
	public static final String EXTRAEVALUATIONCRITERIA1 = "evaluation_criteria1";
	public static final String EXTRAEVALUATIONCRITERIA2 = "evaluation_criteria2";
	public static final String EXTRAEVALUATIONCRITERIA3 = "evaluation_criteria3";
	public static final String EXTRAWORKTIME= "work_time";
	public static final String EXTRAMOMENTEVALUATIONFROM= "moment_evaluation_from";
	public static final String EXTRAMOMENTEVALUATIONUP= "moment_evaluation_up";
	public static final String EXTRAEVIDENCESEND= "evidence_send";
	public static final String EXTRAINTERVENINGACTOR = "intervening_actor";
	public static final String EXTRAFEEDBACKDATE = "feedback_date";

	private RecyclerView mRecyclerView;
	private MisActividadesAdaptor misActividadesAdaptor;
	private ArrayList<ItemMisActividades> mItemsMisActividades;
	private RequestQueue mRequestQueue;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_acerca_curso, container, false);
		acercade = view.findViewById(R.id.about);
		compartir = view.findViewById(R.id.share);
		//actividades = view.findViewById(R.id.activity);
		mRecyclerView = view.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		mItemsMisActividades= new ArrayList<>();

		mRequestQueue = Volley.newRequestQueue(getActivity());
		acercade.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TransicionAcercade();
				//Toast.makeText(getActivity(), "hola", Toast.LENGTH_SHORT).show();
			}
		});
		Bundle arguments = getParentFragment().getArguments();

		String idrecibido = arguments.getString("id");
		Intent intent = new Intent(getActivity(), AcercaCursoActivity.class);
		intent.putExtra(EXTRAID, idrecibido);

		compartir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CompartirCurso();
			}
		});


		ObtenerDatos(idrecibido);


		//Toast.makeText(getContext(), "el texto es "+ idrecibido, Toast.LENGTH_SHORT).show();
		return view;
	}
	public void TransicionAcercade(){

		Bundle arguments = getParentFragment().getArguments();

		String idrecibido = arguments.getString("id");

		Intent intent = new Intent(getActivity(), AcercaCursoActivity.class);
		intent.putExtra(EXTRAID, idrecibido);


		startActivity(intent);
	}

	private void CompartirCurso() {
		Intent sendIntent = new Intent();
		sendIntent.setAction(Intent.ACTION_SEND);
		sendIntent.putExtra(Intent.EXTRA_TEXT, "Existen excelentes cursos en Live4Teach, ven y dales un vistazo");
		sendIntent.setType("text/plain");
		startActivity(sendIntent);
	}

	private void IniciarPantallaActividades() {
	}
	private void ObtenerDatos(final String id) {
		final ProgressDialog progressDialog = new ProgressDialog(getActivity());
		progressDialog.setMessage("Cargando...");
		progressDialog.show();
		progressDialog.setCancelable(false);
		StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://dybcatering.com/back_live_app/miscursos/misactividades/misactividadescurso.php",
				new Response.Listener<String>() {
					@Override
					public void onResponse(String response) {
						progressDialog.dismiss();
						try {
							JSONObject jsonObject = new JSONObject(response);
							JSONArray jsonArray = jsonObject.getJSONArray("Registros");
							for (int i = 0; i < jsonArray.length(); i++) {
								JSONObject hit = jsonArray.getJSONObject(i);
								String id = hit.getString("id");
								String name = hit.getString("name");
								String nombrecurso = hit.getString("nombrecurso");
								String id_user= hit.getString("id_user");
								String activitytype = hit.getString("activitytype");
								String estimated_duration_platform= hit.getString("estimated_duration_platform");
								String estimated_duration_autonomous_work= hit.getString("estimated_duration_autonomous_work");
								String theme_contextualization= hit.getString("theme_contextualization");
								String activity= hit.getString("activity");
								String type_resources_1= hit.getString("type_resources_1");
								String type_resources_2= hit.getString("type_resources_2");
								String type_resources_3= hit.getString("type_resources_3");
								String origin_resource1= hit.getString("origin_resource1");
								String origin_resource2= hit.getString("origin_resource2");
								String origin_resource3= hit.getString("origin_resource3");
								String deliverables= hit.getString("deliverables");
								String evaluation_criteria1= hit.getString("evaluation_criteria1");
								String evaluation_criteria2= hit.getString("evaluation_criteria2");
								String evaluation_criteria3= hit.getString("evaluation_criteria3");
								String work_time= hit.getString("work_time");
								String moment_evaluation_from= hit.getString("moment_evaluation_from");
								String moment_evaluation_up= hit.getString("moment_evaluation_up");
								String evidence_send= hit.getString("evidence_send");
								String intervening_actor= hit.getString("intervening_actor");
								String feedback_date= hit.getString("feedback_date");
								mItemsMisActividades.add(new ItemMisActividades(id, name, nombrecurso, id_user,activitytype, estimated_duration_platform, estimated_duration_autonomous_work,
										theme_contextualization, activity, type_resources_1,type_resources_2, type_resources_3, origin_resource1, origin_resource2, origin_resource3, deliverables,
										evaluation_criteria1, evaluation_criteria2, evaluation_criteria3, work_time, moment_evaluation_from, moment_evaluation_up,
										evidence_send, intervening_actor, feedback_date));
							}
							misActividadesAdaptor = new MisActividadesAdaptor(getActivity(), mItemsMisActividades);
							mRecyclerView.setAdapter(misActividadesAdaptor);
							misActividadesAdaptor.setOnClickItemListener(AcercaCursoFragment.this);

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();
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
			protected Map<String, String > getParams(){
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};


		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}

	@Override
	public void onItemClick(int position) {
		Intent intent = new Intent(getActivity(), MisActividadesDetalle.class);


		ItemMisActividades  clickeditem = mItemsMisActividades.get(position);
		intent.putExtra(EXTRAID, clickeditem.getId());
		intent.putExtra(EXTRANOMBRE, clickeditem.getName());
		intent.putExtra(EXTRANOMBRECURSO, clickeditem.getNombrecurso());
		intent.putExtra(EXTRAIDUSER, clickeditem.getId_user());
		intent.putExtra(EXTRAACTIVITYTYPE, clickeditem.getActivitytype());
		intent.putExtra(EXTRAESTIMATEDDURATIONPLATFORM, clickeditem.getDuracionestimadaplataforma());
		intent.putExtra(EXTRAESTIMATEDDURATIONAUTONOMOUSWORK, clickeditem.getDuracionestimadatrabajoautonomo());
		intent.putExtra(EXTRATHEMECONTEXTUALIZATION, clickeditem.getContextualizaciondeltema());
		intent.putExtra(EXTRAACTIVITY, clickeditem.getActividad());
		intent.putExtra(EXTRATYPERESOURCES1, clickeditem.getTiporecursos1());
		intent.putExtra(EXTRATYPERESOURCES2, clickeditem.getTiporecursos2());
		intent.putExtra(EXTRATYPERESOURCES3, clickeditem.getTiporecursos3());
		intent.putExtra(EXTRAORIGINRESOURCES1, clickeditem.getOrigenrecurso1());
		intent.putExtra(EXTRAORIGINRESOURCES2, clickeditem.getOrigenrecurso2());
		intent.putExtra(EXTRAORIGINRESOURCES3, clickeditem.getOrigenrecurso3());
		intent.putExtra(EXTRADELIVERABLES, clickeditem.getEntregables());
		intent.putExtra(EXTRAEVALUATIONCRITERIA1, clickeditem.getCriteriosevaluacion1());
		intent.putExtra(EXTRAEVALUATIONCRITERIA2, clickeditem.getCriteriosevaluacion2());
		intent.putExtra(EXTRAEVALUATIONCRITERIA3, clickeditem.getCriteriosevaluacion3());
		intent.putExtra(EXTRAWORKTIME, clickeditem.getTiempotrabajo());
		intent.putExtra(EXTRAMOMENTEVALUATIONFROM, clickeditem.getMomentoevaluaciondesde());
		intent.putExtra(EXTRAMOMENTEVALUATIONUP, clickeditem.getMomentoevaluacionhasta());
		intent.putExtra(EXTRAEVIDENCESEND, clickeditem.getEvidenciaenvio());
		intent.putExtra(EXTRAINTERVENINGACTOR, clickeditem.getActorinterviniente());
		intent.putExtra(EXTRAFEEDBACKDATE, clickeditem.getFecharetroalimentacion());

		//String prueba = clickeditem.getName();
		startActivity(intent);

	}


}
