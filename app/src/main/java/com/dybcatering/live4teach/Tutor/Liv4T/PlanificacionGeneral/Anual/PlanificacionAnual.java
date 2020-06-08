package com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral.Anual;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
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
import com.geniusforapp.fancydialog.FancyAlertDialog;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class PlanificacionAnual extends Fragment {


    public PlanificacionAnual() {
        // Required empty public constructor
    }

    View myView;
    SessionManager sessionManager;

    String id;

    private static String URL_READ = "http://dybcatering.com/back_live_app/liv4t/planificacionanual/annual_detail.php";

    EditText logro1, logro2, logro3, logro4;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_planificacion_anual, container, false);

        new CheckInternetConnection(getContext()).checkConnection();
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);

        logro1 = myView.findViewById(R.id.logro_uno);
        logro2 = myView.findViewById(R.id.logro_dos);
        logro3 = myView.findViewById(R.id.logro_tres);
        logro4 = myView.findViewById(R.id.logro_cuatro);

        getUserDetail();

        return myView;
    }


    private void getUserDetail() {

        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_READ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();


                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("read");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String logro_1 = object.getString("achievement_1").trim();
                                    String logro_2 = object.getString("achievement_2").trim();
                                    String logro_3= object.getString("achievement_3").trim();
                                    String logro_4 = object.getString("achievement_4").trim();
                                    logro1.setText(logro_1);
                                    logro2.setText(logro_2);
                                    logro3.setText(logro_3);
                                    logro4.setText(logro_4);
                                    logro1.setEnabled(false);
                                    logro2.setEnabled(false);
                                    logro3.setEnabled(false);
                                    logro4.setEnabled(false);
                                }

                            }else{
                                logro1.setEnabled(true);
                                logro2.setEnabled(true);
                                logro3.setEnabled(true);
                                logro4.setEnabled(true);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error de conexión ", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                error -> {
                    progressDialog.dismiss();
                    Toast.makeText(getActivity(), "Error de conexión  ", Toast.LENGTH_SHORT).show();
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
