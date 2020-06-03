package com.dybcatering.live4teach.Tutor.Liv4T.Perfil;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilTutor extends Fragment {


    public PerfilTutor() {
        // Required empty public constructor
    }

    View myView;

    SessionManager sessionManager;

    String id;

    TextView tvname, tvemail, tvphone;

    private static String URL_READ = "http://192.168.0.18/webdyb/back_live_app/read_detail.php";

    CircleImageView profilepicestudiante;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.fragment_perfil_estudiante, container, false);
        new CheckInternetConnection(getContext()).checkConnection();
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);

        profilepicestudiante = myView.findViewById(R.id.profilepicestudiante);
        tvname = myView.findViewById(R.id.txtUsuario);
        tvemail = myView.findViewById(R.id.txtCorreo);
        tvphone = myView.findViewById(R.id.txtTelefono);

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

                                    String strName = object.getString("name").trim();
                                    String strEmail = object.getString("email").trim();
                                    final String strPicture= object.getString("picture").trim();
                                    String strTelefono = object.getString("phone").trim();
                                    tvname.setText(strName);
                                    tvemail.setText(strEmail);
                                    tvphone.setText(strTelefono);
                                    if (strPicture.equals("")) {
                                        profilepicestudiante.setImageResource(R.drawable.imagenperfil);
                                    } else {

                                        Picasso.with(getContext()).load(strPicture).into(profilepicestudiante);

                                    }

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

        profilepicestudiante.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final FancyAlertDialog.Builder alert = new FancyAlertDialog.Builder(getActivity())
                        .setBackgroundColor(R.color.white)
                        .setimageResource( R.drawable.imagenperfil)
                        .setTextTitle("Perfil")
                        .setPositiveButtonText("Aceptar")
                        .setPositiveColor(R.color.colorbonton)
                        .setOnPositiveClicked(new FancyAlertDialog.OnPositiveClicked() {
                            @Override
                            public void OnClick(View view, Dialog dialog) {

                                dialog.dismiss();


                            }
                        })
                        .setBodyGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setTitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setSubtitleGravity(FancyAlertDialog.TextGravity.CENTER)
                        .setCancelable(false)
                        .build();
                alert.show();
            }
        });

    }

}
