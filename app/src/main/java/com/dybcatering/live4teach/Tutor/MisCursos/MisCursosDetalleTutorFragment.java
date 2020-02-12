package com.dybcatering.live4teach.Tutor.MisCursos;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.Clases.ClasesFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.ViewPagerAdapter;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAID;

public class MisCursosDetalleTutorFragment extends Fragment {

	View view;

	TextView txtTotalInscritos;
	private TabLayout tabLayout;
	private AppBarLayout appBarLayout;
	private ViewPager viewPager;
	private static String URL_READ = "https://dybcatering.com/back_live_app/miscursos/inscritos_curso.php";

	private static final String TAG = MisCursosDetalleTutorFragment.class.getSimpleName(); //getting the info
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		view =	inflater.inflate(R.layout.fragment_mis_cursos_detalle_tutor_detalle, container, false);
		tabLayout = view.findViewById(R.id.tablayout_id);
		appBarLayout = view.findViewById(R.id.appBarId);
		viewPager = view.findViewById(R.id.viewpager);
		txtTotalInscritos = view.findViewById(R.id.txtTotalInscritos);


		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
		//adapter = new ViewPagerAdapter(getChildFragmentManager(), MisCursosDetalleFragment.this);
		adapter.AddFragment(new ClasesFragment(), "Listado de Clases");
		adapter.AddFragment(new AcercaCursoFragment(), "Acerca de este curso");
		//adapter.AddFragment(new TerceroFragment(), "TerceroFragment");
		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);
		Bundle arguments = getArguments();
		String id_curso = arguments.getString(EXTRAID);
		ObtenerInscritos(id_curso);

		return view;
	}

	private void ObtenerInscritos(final String id) {
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

									String strDescription = object.getString("inscritos").trim();
									txtTotalInscritos.setText("Total Inscritos: "  +strDescription);

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
