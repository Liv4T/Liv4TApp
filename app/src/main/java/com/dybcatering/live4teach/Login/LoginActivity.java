package com.dybcatering.live4teach.Login;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.Inicio.InicioActivity;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Consulta.ConsultasyTutorias.MessageActivity;
import com.dybcatering.live4teach.Tutor.InicioActivityTutor;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    private EditText email, password;
    private Button btn_login;
    private TextView link_regist, text_olvide_contrasena;
    private ProgressBar loading;
    private static String URL_LOGIN = "https://dybcatering.com/back_live_app/login.php";
    SessionManager sessionManager;
    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);


        loading = findViewById(R.id.loading);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        btn_login = findViewById(R.id.btn_login);
      //  spinner = findViewById(R.id.spinner);

         link_regist = findViewById(R.id.text_registro);
        text_olvide_contrasena = findViewById(R.id.text_olvide_contrasena);



        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mEmail = email.getText().toString().trim();
                String mPass = password.getText().toString().trim();

                if (!mEmail.isEmpty() || !mPass.isEmpty()) {
                    validarlogin(mEmail, mPass);
                } else {
                    email.setError("Por favor ingresa tu usuario");
                    password.setError("Por favor ingresa tu contraseña");
                }


            }
        });

        link_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        text_olvide_contrasena.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, OlvideContrasena.class);
                startActivity(intent);

            }
        });

    }

    public void validarlogin(final String user_name, final String password) {
        loading.setVisibility(View.VISIBLE);
        btn_login.setVisibility(View.GONE);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");

                            if (success.equals("1")) {

                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject object = jsonArray.getJSONObject(i);

                                    String id = object.getString("id").trim();
                                    String name = object.getString("name").trim();
                                    String last_name = object.getString("last_name").trim();
                                    // String photo = object.getString("photo").trim();
                                    String type_user = object.getString("type_user").trim();
                                    String user_name = object.getString("user_name").trim();
                                    sessionManager.createSession(name, user_name, id, type_user);

                                    Intent intent = new Intent(LoginActivity.this, InicioActivity.class);
                                    intent.putExtra("id", id);
                                    intent.putExtra("name", name);
                                    intent.putExtra("last_name", last_name);
                                    intent.putExtra("type_user", type_user);
                                    intent.putExtra("user_name", user_name);
                                    //intent.putExtra("photo", photo);
                                    // Toast.makeText(LoginActivity.this, id, Toast.LENGTH_SHORT).show();
                                    Intent intent1 = new Intent(LoginActivity.this, InicioActivityTutor.class);
                                    intent1.putExtra("id", id);
                                    intent1.putExtra("name", name);
                                    intent1.putExtra("last_name", last_name);
                                    intent1.putExtra("type_user", type_user);
                                    intent1.putExtra("user_name", user_name);

                                    if (type_user.equals("3")){
                                        FirebaseMessaging.getInstance().subscribeToTopic("estudiantes")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        String msg = getString(R.string.msg_subscribed);
                                                        if (!task.isSuccessful()) {
                                                            msg = getString(R.string.msg_subscribe_failed);
                                                        }
                                                        //Log.d(TAG, msg);
                                                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                        startActivity(intent);
                                        finish();
                                        loading.setVisibility(View.GONE);
                                    }else{
                                        FirebaseMessaging.getInstance().subscribeToTopic("tutores")
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        String msg = getString(R.string.msg_subscribed);
                                                        if (!task.isSuccessful()) {
                                                            msg = getString(R.string.msg_subscribe_failed);
                                                        }
                                                        //Log.d(TAG, msg);
                                                        Toast.makeText(LoginActivity.this, msg, Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                        startActivity(intent1);
                                        finish();
                                        loading.setVisibility(View.GONE);
                                    }

                                    loading.setVisibility(View.GONE);


                                }

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                                builder.setMessage("Contraseña incorrecta")
                                        .setCancelable(false)
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {
                                                //do things
                                            }
                                        });
                                AlertDialog alert = builder.create();
                                alert.show();
                                loading.setVisibility(View.GONE);
                                btn_login.setVisibility(View.VISIBLE);

                            }

                        } catch (JSONException e) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                            builder.setMessage("Cuenta no existe")
                                    .setCancelable(false)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int id) {
                                            //do things
                                        }
                                    });
                            AlertDialog alert = builder.create();
                            alert.show();
                            loading.setVisibility(View.GONE);
                            btn_login.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                        builder.setMessage("Error de conexión")
                                .setCancelable(false)
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        //do things
                                    }
                                });
                        AlertDialog alert = builder.create();
                        alert.show();
                        loading.setVisibility(View.GONE);
                        btn_login.setVisibility(View.VISIBLE);
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("user_name", user_name);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

}
