package com.dybcatering.live4teach.Tutor.Liv4T.MisClases.DetalleSemanaClases;

import android.app.ProgressDialog;
import android.os.Bundle;
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
import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.Liv4T.Tareas.DetalleClase;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.DetalleSemanaClases.Model.AdaptadorClase;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.DetalleSemanaClases.Model.ClaseItem;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.MisClasesFragment;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model.AdaptadorMisClases;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model.MisClasesItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;


public class ListadoClases extends Fragment implements AdaptadorClase.OnItemClickListener {

    public ListadoClases() {
        // Required empty public constructor
    }
    View myView;

    private AdaptadorClase mMisClases;
    private ArrayList<ClaseItem> mClasesItem;
    private RecyclerView mRecyclerView;

    SessionManager sessionManager;

    String id;

    private RequestQueue mRequestQueue;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_listado_clases, container, false);

        Bundle bundle = getArguments();
        String mid = bundle.getString("idweek");
        new CheckInternetConnection(getContext()).checkConnection();
        mRecyclerView = myView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mClasesItem= new ArrayList<>();


        mRequestQueue = Volley.newRequestQueue(getActivity());



        ObtenerDatos(mid);


        return myView;
    }

    private void ObtenerDatos(final String id) {

        String url = "http://dybcatering.com/back_live_app/liv4t/clases/class_list.php";
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

                                String strName = hit.getString("name");
                                String strDescription = hit.getString("description");
                                String strname_document = hit.getString("name_document");
                                String strdocument = hit.getString("document");
                                String strurl = hit.getString("url");
                                String strvideo = hit.getString("video");
                                String strweekly = hit.getString("id_weekly_plan");

                                mClasesItem.add(new ClaseItem(strName, strDescription, strname_document, strdocument, strurl, strvideo,strweekly));
                            }
                            mMisClases = new AdaptadorClase(getActivity(), mClasesItem);
                            mRecyclerView.setAdapter(mMisClases);
                            mMisClases.setOnClickItemListener(ListadoClases.this);

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
        ClaseItem misClasesItem = mClasesItem.get(position);
        Fragment someFragment = new DetalleClase();
        Bundle arguments = new Bundle();
        arguments.putString("nombre", misClasesItem.getNombre());
        arguments.putString("descripcion", misClasesItem.getDescripcion());
        arguments.putString("documento", misClasesItem.getDocumento());
        arguments.putString("nombre_documento", misClasesItem.getNombre_Documento());
        arguments.putString("id_weekly", misClasesItem.getId_Weekly());
        arguments.putString("url", misClasesItem.getUrl());
        arguments.putString("video", misClasesItem.getVideo());
        someFragment.setArguments(arguments);
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ).addToBackStack("tag"); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
