package com.dybcatering.live4teach.Estudiante.Carrito;

import android.app.ProgressDialog;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.squareup.picasso.Picasso.Priority.HIGH;

public class CarritoDetalleActivity extends AppCompatActivity {
    private TextView itemName, quantity, dateAdded, imageName, tachado;
    private int groceryId;
    private Button btnComprar;
    private static final String TAG = CarritoDetalleActivity.class.getSimpleName(); //getting the info
    private Button Comprar;
    private RequestQueue mRequestQueue;
    private ImageView imgcarritodetalle;
    SessionManager sessionManager;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito_detalle);
        itemName = (TextView) findViewById(R.id.itemNameDet);
        quantity = (TextView) findViewById(R.id.quantityDet);
        dateAdded = (TextView) findViewById(R.id.dateAddedDet);
        imageName = findViewById(R.id.quantityImage);
        tachado = findViewById(R.id.tachado);
        imgcarritodetalle = findViewById(R.id.imgcarritodetalle);
        Comprar = findViewById(R.id.comprar);
		tachado.setPaintFlags(tachado.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        sessionManager = new SessionManager(CarritoDetalleActivity.this);
        mRequestQueue = Volley.newRequestQueue(CarritoDetalleActivity.this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.ID);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null) {
            final String nombrecurso = bundle.getString("name");
            Picasso.with(this).load(bundle.getString("imagen"))
                    .priority(HIGH)
                    .fit()
                    .centerInside()
                    .into(imgcarritodetalle);

            Comprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    envioDatosInscribir(nombrecurso, id);
                }
            });

            Toast.makeText(this, "el nombre del curso es"+ nombrecurso, Toast.LENGTH_SHORT).show();


            dateAdded.setText(bundle.getString("date"));
            groceryId = bundle.getInt("id");
        }

    }
    private void envioDatosInscribir(final String nombrecurso, final String idusuario) {
        String url = "http://dybcatering.com/back_live_app/carrito/registercarrito.php";
        final ProgressDialog progressDialog = new ProgressDialog(CarritoDetalleActivity.this);
        progressDialog.setMessage("Cargando...");
        progressDialog.show();
        progressDialog.setCancelable(false);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.i(TAG, response);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("Registros");
                                for (int i = 0; i < jsonArray.length(); i++) {

                                    JSONObject hit = jsonArray.getJSONObject(i);

                                    String strIdCourses= hit.getString("id_courses").trim();
                                    String strIdUsuario= hit.getString("id_user").trim();
                                   // textView.setText(strDescription);
                                    Toast.makeText(CarritoDetalleActivity.this, "El curso fue adicionado a MisCursos"+ strIdCourses, Toast.LENGTH_SHORT).show();

                                }



                        } catch (JSONException e) {
                            e.printStackTrace();
                            progressDialog.dismiss();
                            Toast.makeText(CarritoDetalleActivity.this, "Error de conexión ", Toast.LENGTH_SHORT).show();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(CarritoDetalleActivity.this, "Error de conexión  ", Toast.LENGTH_SHORT).show();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("name", nombrecurso);
                params.put("id_user", idusuario);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(CarritoDetalleActivity.this);
        requestQueue.add(stringRequest);

    }
}