package com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.Semana.CrearSemana;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class EditarSemana extends Fragment {

    public EditarSemana() {
        // Required empty public constructor
    }

    View myView;

    EditText preguntaConductora, desarrolloClase, observacion;
    Button enviar;

    private static String URL_READ = "http://dybcatering.com/back_live_app/liv4t/semana/week_edit_detail.php";
    private static String URL_UPDATE = "http://dybcatering.com/back_live_app/liv4t/semana/week_update_detail.php";

    SessionManager sessionManager;

    String idteacher;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_editar_semana, container, false);

        preguntaConductora = myView.findViewById(R.id.edPreguntaConductora);
        desarrolloClase = myView.findViewById(R.id.edDesarrolloClase);
        observacion = myView.findViewById(R.id.edObservacion);
        
        enviar = myView.findViewById(R.id.btnEnviar);
        Bundle arguments = getArguments();
        String week_recived = arguments.getString("week");

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntaConductora.getText().toString().isEmpty() || desarrolloClase.getText().toString().isEmpty() || observacion.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Campos vacios", Toast.LENGTH_SHORT).show();
                }else{
                    enviarDatos(week_recived, preguntaConductora.getText().toString(), desarrolloClase.getText().toString(), observacion.getText().toString());
                }
            }
        });


        getUserDetail(week_recived);


        return myView;
    }

    private void getUserDetail(String week) {

        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        idteacher = user.get(SessionManager.ID);


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

                                    String strdriving_question = object.getString("driving_question").trim();
                                    String strclass_development = object.getString("class_development").trim();
                                    String strobservation= object.getString("observation").trim();
                                    String strweek = object.getString("week").trim();
                                    preguntaConductora.setText(strdriving_question);
                                    desarrolloClase.setText(strclass_development);
                                    observacion.setText(strobservation);


                                }

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(getActivity(), "Error de conexión "+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getActivity(), "Error de conexión "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", week);
                params.put("idteacher", idteacher);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


    }


    private void enviarDatos(String week_recived, String preguntaconductora, String desarrolloclase, String observacion) {

        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        idteacher = user.get(SessionManager.ID);


        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_UPDATE,
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

                                    String strdriving_question = object.getString("driving_question").trim();
                                    String strclass_development = object.getString("class_development").trim();
                                    String strobservation= object.getString("observation").trim();
                                    String strweek = object.getString("week").trim();
                                    preguntaConductora.setText(strdriving_question);
                                    desarrolloClase.setText(strclass_development);
//                                    observacion.setText(strobservation);


                                }

                                Toast.makeText(getContext(), "Actualizado Correctamente", Toast.LENGTH_SHORT).show();
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                   //         Toast.makeText(getActivity(), "Error de conexión "+e, Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
               //         Toast.makeText(getActivity(), "Error de conexión "+error, Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("id", week_recived);
                params.put("idteacher", idteacher);
                params.put("question", preguntaconductora);
                params.put("development", desarrolloclase);
                params.put("observation", observacion);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(stringRequest);


      //  getActivity().onBackPressed();





    }
}
