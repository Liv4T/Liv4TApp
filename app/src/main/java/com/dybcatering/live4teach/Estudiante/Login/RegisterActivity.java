package com.dybcatering.live4teach.Estudiante.Login;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.dybcatering.live4teach.R;

public class RegisterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private EditText nombre, apellido, email, usuario, password, c_password, telefono;
    private Button btn_regist;
    private ProgressBar loading;
    private static String URL_REGIST = "http://192.168.1.18:8080/Androidlogin/register.php";

    public Spinner spinnerRegistro;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinnerRegistro = findViewById(R.id.spinnerregistro);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(this, R.array.spinner, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerRegistro.setAdapter(arrayAdapter);
        spinnerRegistro.setOnItemSelectedListener(this);
        nombre = findViewById(R.id.txtNombre);
        apellido= findViewById(R.id.txtApellido);
        email = findViewById(R.id.txtMail);
        usuario = findViewById(R.id.txtUsuario);
        password = findViewById(R.id.txtPassword);
        c_password = findViewById(R.id.txtPasswordConfirm);
        telefono = findViewById(R.id.txtTelefono);
        btn_regist = findViewById(R.id.btn_registrarse);

        nombre.setEnabled(false);
        apellido.setEnabled(false);
        email.setEnabled(false);
        usuario.setEnabled(false);
        password.setEnabled(false);
        c_password.setEnabled(false);
        telefono.setEnabled(false);
        btn_regist.setEnabled(false);
        // loading = findViewById(R.id.loading);
       // name = findViewById(R.id.name);
       // email = findViewById(R.id.email);
       // password = findViewById(R.id.password);
        /// c_password = findViewById(R.id.c_password);
        // btn_regist = findViewById(R.id.btn_regist);

/*        btn_regist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Regist();
            }
        });

    }

    private void Regist(){
        loading.setVisibility(View.VISIBLE);
        btn_regist.setVisibility(View.GONE);

        final String name = this.name.getText().toString().trim();
        final String email = this.email.getText().toString().trim();
        final String password = this.password.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try{
                        JSONObject jsonObject = new JSONObject(response);
                        String success = jsonObject.getString("success");

                        if (success.equals("1")) {
                            Toast.makeText(RegisterActivity.this, "El registro tuvo exito", Toast.LENGTH_SHORT).show();
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "El registro no tuvo exito" + e.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error! " + error.toString(), Toast.LENGTH_SHORT).show();
                        loading.setVisibility(View.GONE);
                        btn_regist.setVisibility(View.VISIBLE);
                    }
                })

        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("name", name);
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);


    }*/
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text =  parent.getItemAtPosition(position).toString();
       if (text.equals("Tutor")){

           String url = "http://www.dybcatering.com";
           Intent i = new Intent(Intent.ACTION_VIEW);
           i.setData(Uri.parse(url));
           startActivity(i);
           nombre.setEnabled(false);
           apellido.setEnabled(false);
           email.setEnabled(false);
           usuario.setEnabled(false);
           password.setEnabled(false);
           c_password.setEnabled(false);
           telefono.setEnabled(false);
           btn_regist.setEnabled(false);

        } else if (text.equals("Estudiante")) {
           nombre.setEnabled(true);
           apellido.setEnabled(true);
           email.setEnabled(true);
           usuario.setEnabled(true);
           password.setEnabled(true);
           c_password.setEnabled(true);
           telefono.setEnabled(true);
           btn_regist.setEnabled(true);
        } else if (text.equals("Seleccionar...")){
           nombre.setEnabled(false);
           apellido.setEnabled(false);
           email.setEnabled(false);
           usuario.setEnabled(false);
           password.setEnabled(false);
           c_password.setEnabled(false);
           telefono.setEnabled(false);
           btn_regist.setEnabled(false);

       }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
