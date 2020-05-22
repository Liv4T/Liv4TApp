package com.dybcatering.live4teach.Estudiante.Liv4T.Chat;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Estudiante.Liv4T.Chat.Adaptador.AdaptadorGrupo;
import com.dybcatering.live4teach.Estudiante.Liv4T.Chat.CrearGrupo.CrearGrupoActivity;
import com.dybcatering.live4teach.Estudiante.Liv4T.Chat.Model.ItemGrupo;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class ListadoConversaciones extends AppCompatActivity  implements AdaptadorGrupo.OnItemClickListener{


    private HashMap<String, String> user;
    private String name, email, photo, mobile;
    private String first_time;
    RecyclerView recyclergrupos;
    SessionManager sessionManager;
    private AdaptadorGrupo mAdaptadorGrupo;
    private ArrayList<Object> mItemGrupo;
    private RequestQueue mRequestQueue;

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_conversaciones);


        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        fab = findViewById(R.id.btn_add_func);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoConversaciones.this, CrearGrupoActivity.class);
                startActivity(intent);
            }
        });

        HashMap<String, String> user = sessionManager.getUserDetail();
        String nombreusuario = user.get(SessionManager.NAME);

        recyclergrupos = findViewById(R.id.rvgrupos);
        recyclergrupos.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

        mItemGrupo = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(ListadoConversaciones.this);

        ObtenerDatos();
    }

    private void ObtenerDatos() {
        String url = "http://192.168.0.18/webdyb/loginapp/listargrupos.php";
        final ProgressDialog progressDialog = new ProgressDialog(ListadoConversaciones.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Grupos");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String name = hit.getString("name");


                                mItemGrupo.add(new ItemGrupo(name));
                            }
                            mAdaptadorGrupo = new AdaptadorGrupo(ListadoConversaciones.this,mItemGrupo);
                            recyclergrupos.setAdapter(mAdaptadorGrupo);
                            mAdaptadorGrupo.setOnClickItemListener(ListadoConversaciones.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast toast= Toast.makeText(ListadoConversaciones.this,
                                    "En este momento no podemos cargar el chat", Toast.LENGTH_SHORT);
                            toast.setGravity(Gravity.CENTER_HORIZONTAL| Gravity.CENTER_HORIZONTAL, 0, 0);
                            toast.show();
                            //Toasty toasty = Toasty.error(getContext(), "Parece que algo salió mal o aun no has agregado cursos", Toast.LENGTH_SHORT).show();

                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                progressDialog.dismiss();
                    Toast.makeText(ListadoConversaciones.this, "error de bd" + error, Toast.LENGTH_SHORT).show();
            }
        }) ;
		/* {
			@Override
			protected Map<String, String > getParams(){
				Map<String, String> params = new HashMap<>();
				params.put("id", id);
				return params;
			}
		};*/

        RequestQueue requestQueue = Volley.newRequestQueue(ListadoConversaciones.this);
        requestQueue.add(stringRequest);
    }

    @Override
    public void onItemClick(int position) {

    }
}
