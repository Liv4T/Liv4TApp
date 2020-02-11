package com.dybcatering.live4teach.Estudiante.MisCalificaciones;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador.AdaptorMisCalificaciones;
import com.dybcatering.live4teach.Estudiante.MisCalificaciones.Adaptador.ItemMisCalificaciones;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class MisCalificacionesFragment extends Fragment implements AdaptorMisCalificaciones.OnItemClickListener {
    View myView;
    private CardView cardview;
    SessionManager sessionManager;
    String id_usuario;
    private RecyclerView mRecyclerView;
    private AdaptorMisCalificaciones misCalificacionesAdaptor;
    private ArrayList<ItemMisCalificaciones> mcalificacionesItems;
    private RequestQueue mRequestQueue;
    private static final String TAG = MisCalificacionesFragment.class.getSimpleName(); //getting the info
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_mis_calificaciones, container, false);
        new CheckInternetConnection(getActivity()).checkConnection();
        sessionManager = new SessionManager(getActivity());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id_usuario = user.get(SessionManager.ID);
        mRecyclerView = myView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mcalificacionesItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());
        ObtenerDatos(id_usuario);


        return myView;
    }

    private void ObtenerDatos(final String id) {

        String url = "http://dybcatering.com/back_live_app/calificaciones/calificaciones.php";
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
                                String name = hit.getString("name");
                                String activity= hit.getString("activity");
                                String user_name= hit.getString("user_name");
                                String activitytype = hit.getString("activitytype");
                                String last_date= hit.getString("last_date");
                                String date= hit.getString("date");
                                String subtotal= hit.getString("subtotal");
                                String total= hit.getString("total");
                                String observation= hit.getString("observation");
                                String updated_at= hit.getString("updated_at");
                                String created_at= hit.getString("created_at");
                                mcalificacionesItems.add(new ItemMisCalificaciones(name, activity, user_name, activitytype,last_date,
                                        date, subtotal, total, observation, updated_at));
                            }
                            misCalificacionesAdaptor= new AdaptorMisCalificaciones(getActivity(), mcalificacionesItems);
                            mRecyclerView.setAdapter(misCalificacionesAdaptor);
                            misCalificacionesAdaptor.setOnClickItemListener(MisCalificacionesFragment.this);

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
        Fragment someFragment = new MisCalificacionesDetalleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
