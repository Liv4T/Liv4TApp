package com.dybcatering.live4teach.Tutor.MisCursos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador.AdaptorMisCalificaciones;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.MisCursos.Adaptador.AdaptadorMisCursosTutor;
import com.dybcatering.live4teach.Tutor.MisCursos.Adaptador.ItemMisCursosTutor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MisCursosTutorFragment extends Fragment implements AdaptorMisCalificaciones.OnItemClickListener {

	View myView;
	Button button;
	private RecyclerView mRecyclerView;
	private AdaptadorMisCursosTutor misCursosAdaptor;
	private ArrayList<ItemMisCursosTutor> mcursosItems;
	private RequestQueue mRequestQueue;
	public static final String EXTRAID = "id";
	public static final String EXTRANAME = "name";
	public static final String EXTRAMETHODOLOGY = "methodology";
	public static final String EXTRAWELCOME = "welcome";
	public static final String EXTRAINTENTION = "intention";
	public static final String EXTRAINTENSITYAC = "intensityAC";
	public static final String EXTRACOMPETENCES = "competences";
	public static final String EXTRAINTENSITYTA = "intensityTA";
	public static final String EXTRAACHIEVEMENT = "achievement";
	public static final String EXTRAINDICATORA = "indicatorA";
	public static final String EXTRAMETHODOLOGYG = "methodologyG";
	public static final String EXTRATYPE = "type";
	public static final String EXTRADESCRIPTION = "description";
	public static final String EXTRADESCRIPTIONO = "descriptionO";
	public static final String EXTRAUPDATED_AT = "updated_at";
	public static final String EXTRASTATE = "state";
	public static final String EXTRAIMAGE = "image";
	public static final String EXTRAVIDEO_PRESENTACION = "video_presentacion";
	public static final String EXTRATOPIC = "topic";
	public static final String EXTRAPUBLISH = "publish";
	public static final String EXTRAIDTEMAS = "idtemas";
	SessionManager sessionManager;
	private static final String TAG = MisCursosTutorFragment.class.getSimpleName(); //getting the info
	String id_usuario;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_mis_cursos_tutor, container, false);

		new CheckInternetConnection(getActivity()).checkConnection();
		sessionManager = new SessionManager(getActivity());
		HashMap<String, String> user = sessionManager.getUserDetail();
		id_usuario = user.get(SessionManager.ID);

		mRecyclerView = myView.findViewById(R.id.recycler_view);
		mRecyclerView.setHasFixedSize(true);
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

		mcursosItems = new ArrayList<>();

		mRequestQueue = Volley.newRequestQueue(getActivity());

		ObtenerDatos(id_usuario);
		return myView ;
	}

	private void ObtenerDatos(final String id) {

		String url = "http://dybcatering.com/back_live_app/miscursos/cursos_tutor.php";
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
								String nombre = hit.getString("name");
								String idcategory = hit.getString("id_category");
								String idsubcategory = hit.getString("id_subcategory");
								String methodology = hit.getString("methodology");
								String welcome = hit.getString("welcome");
								String intention = hit.getString("intention");
								String intensityAC = hit.getString("intensityAC");
								String competences = hit.getString("competences");
								String intensityTA = hit.getString("intensityTA");
								String Achievement = hit.getString("achievement");
								String map = hit.getString("map");
								String indicatorA = hit.getString("indicatorA");
								String methodologyG = hit.getString("methodologyG");
								String type = hit.getString("type");
								String description = hit.getString("description");
								String presentation = hit.getString("presentation");
								String iduser = hit.getString("id_user");
								String descriptionO = hit.getString("descriptionO");
								String updated_at = hit.getString("updated_at");
								String state = hit.getString("state");
								String image = hit.getString("image");
								String video_presentacion = hit.getString("video_presentation");
								String topic = hit.getString("topic");
								String publish = hit.getString("publish");
								String idunidad = hit.getString("idunidad");
								String total = hit.getString("total");
								mcursosItems.add(new ItemMisCursosTutor(id, nombre, idcategory, idsubcategory, methodology, welcome, intention, intensityAC, competences,
										intensityTA, Achievement, indicatorA, map, methodologyG, type, description, presentation, iduser, descriptionO, updated_at, state, image,
										video_presentacion, topic, publish, idunidad, total));
							}
							misCursosAdaptor = new AdaptadorMisCursosTutor(getActivity(), mcursosItems);
							mRecyclerView.setAdapter(misCursosAdaptor);
							misCursosAdaptor.setOnClickItemListener(MisCursosTutorFragment.this);

						} catch (JSONException e) {
							e.printStackTrace();
							progressDialog.dismiss();
							Toast toast= Toast.makeText(getContext(),
									"Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT);
							toast.setGravity(Gravity.TOP| Gravity.CENTER_HORIZONTAL, 0, 0);
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
			protected Map<String, String > getParams(){
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};

		RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
		requestQueue.add(stringRequest);
	}

	public void IniciarTransicion(){
		Fragment someFragment = new MisCursosDetalleTutorDetalleFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}

	@Override
	public void onItemClick(int position) {

	}
}
