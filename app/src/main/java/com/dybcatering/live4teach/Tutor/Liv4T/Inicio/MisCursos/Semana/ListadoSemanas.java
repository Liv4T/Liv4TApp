package com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
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
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana.CrearSemana;
import com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana.EditarSemana;
import com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana.Model.AdaptadorSemanas;
import com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana.Model.SemanaItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class ListadoSemanas extends Fragment implements AdaptadorSemanas.OnItemClickListener{

    public ListadoSemanas() {
        // Required empty public constructor
    }

    View myView;


    private AdaptadorSemanas mAdaptadorSemanas;
    private ArrayList<SemanaItem> msemanaItem;
    private RecyclerView mRecyclerView;

    SessionManager sessionManager;

    String id;

    private RequestQueue mRequestQueue;


    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_listado_semanas, container, false);

        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);
        mRecyclerView = myView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        msemanaItem= new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        ObtenerDatos(id);

        fab = myView.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               transicionCrearSemana();
            }
        });


        return myView;
    }

    private void transicionCrearSemana() {
        Fragment planificacion = new CrearSemana();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, planificacion); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void ObtenerDatos(String id) {
        String url = "http://dybcatering.com/back_live_app/liv4t/semana/week_detail.php";
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //  Log.i(TAG, response);
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Registros");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);

                                String driving_question = hit.getString("driving_question");
                                String class_development = hit.getString("class_development");
                                String observation = hit.getString("observation");
                                String week = hit.getString("week");

                                msemanaItem.add(new SemanaItem(driving_question, class_development, observation, week));
                            }
                            mAdaptadorSemanas= new AdaptadorSemanas(getActivity(), msemanaItem);
                            mRecyclerView.setAdapter(mAdaptadorSemanas);
                            mAdaptadorSemanas.setOnClickItemListener(ListadoSemanas.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast toast = Toast.makeText(getContext(),
                                    ""+e, Toast.LENGTH_SHORT);
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
                Toasty.error(getContext(), ""+error, Toast.LENGTH_SHORT).show();
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



    @Override
    public void onItemClick(int position) {

        SemanaItem semanaItem = msemanaItem.get(position);

        Fragment planificacion = new EditarSemana();
        //tvname.setText("Daniel");
        Bundle arguments = new Bundle();
        arguments.putString( "week" , semanaItem.getWeeek());
        planificacion.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, planificacion); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();


    }
}
