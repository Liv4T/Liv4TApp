package com.dybcatering.live4teach.Tutor.Liv4T.MisClases;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
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
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model.AdaptadorMisClases;
import com.dybcatering.live4teach.Tutor.Liv4T.MisClases.Model.MisClasesItem;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.PlanificacionGeneral.Trimestral.Model.AdaptadorPlanificacionTrimestral;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.PlanificacionGeneral.Trimestral.Model.PlanificacionTrimestralItem;
import com.dybcatering.live4teach.Tutor.Liv4T.MisCursos.PlanificacionGeneral.Trimestral.PlanificacionTrimestral;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import es.dmoral.toasty.Toasty;

public class MisClasesFragment extends Fragment implements AdaptadorMisClases.OnItemClickListener {

    public MisClasesFragment() {
        // Required empty public constructor
    }

    View myView;

    private AdaptadorMisClases mMisClases;
    private ArrayList<MisClasesItem> mClasesItem;
    private RecyclerView mRecyclerView;

    SessionManager sessionManager;

    String id;

    private RequestQueue mRequestQueue;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_clases, container, false);


        new CheckInternetConnection(getContext()).checkConnection();
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);
        mRecyclerView = myView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mClasesItem= new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        ObtenerDatos(id);

        return myView;
    }

    public void Alert(){
/*

            AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setTitle("Información");

            LayoutInflater inflater = this.getLayoutInflater();
            View order_address_comment = inflater.inflate(R.layout.sopas_item, null);




                alertDialog.setView(order_address_comment);


                alertDialog.setPositiveButton("ACEPTAR", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    }



            });


                alertDialog.show();

 */

    }

    private void ObtenerDatos(final String id) {

        String url = "http://dybcatering.com/back_live_app/liv4t/clases/week_list.php";
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

                                String strSemana= hit.getString("week");
                                String strId = hit.getString("id");

                                mClasesItem.add(new MisClasesItem( strId, strSemana));
                            }
                            mMisClases = new AdaptadorMisClases(getActivity(), mClasesItem);
                            mRecyclerView.setAdapter(mMisClases);
                            mMisClases.setOnClickItemListener(MisClasesFragment.this);

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

    }


    }



