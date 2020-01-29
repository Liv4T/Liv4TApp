package com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Adapter.ExampleAdaptor;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Adapter.ExampleItem;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CursosFragment extends Fragment implements ExampleAdaptor.OnItemClickListener  {

    View myView;
    public ImageView imagen, imagen_segunda, imagen_tercera, imagen_cuarta, imagen_quinta, imagen_novena;
    public CardView primer_card,segundo_card, tercer_card, cuarto_card, quinto_card, sexto_card, septimo_card, octavo_card, noveno_card, decimo_card, onceavo_card, doceavo_card ;
    ImageView imagesinconexion;
    private RecyclerView mRecyclerView;
    private ExampleAdaptor mExampleAdaptor;
    private ArrayList<ExampleItem> mexampleItems;
    private RequestQueue mRequestQueue;
    private ProgressBar progressBar;

    private Button btn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.primer_fragment, container, false);
        new CheckInternetConnection(getActivity()).checkConnection();
      /**  imagen = myView.findViewById(R.id.imagen_primera);
        imagen_segunda = myView.findViewById(R.id.imagen_segunda);
        imagen_tercera = myView.findViewById(R.id.imagen_tercera);
        imagen_cuarta= myView.findViewById(R.id.imagen_cuarta);
        imagen_quinta = myView.findViewById(R.id.imagen_quinta);
        primer_card = myView.findViewById(R.id.primer_card);
        segundo_card = myView.findViewById(R.id.card_two);
        tercer_card = myView.findViewById(R.id.card_third);
        cuarto_card = myView.findViewById(R.id.card_fourth);
        // btn = myView.findViewById(R.id.btnAgregarCurso);

      /*  btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "has hecho click", Toast.LENGTH_SHORT).show();
            }
        });*/
//        Picasso.with(getActivity()).load("http://192.168.1.101/imagenes/primer_curso.jpg")
        /*****************
         Picasso.with(getActivity()).load("http://digitalandroidservices.com/personal/cover1.jpg")
**
  **              .fit().
    **            //      resize(400,400).
      **          // resize(100,100).
        **                centerInside().
**
**                   into(imagen);
**


        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/html.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });
        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/imagencurso.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_segunda, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/marketing.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_tercera, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/quimica.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_cuarta, new Callback() {
            @Override public void onSuccess() {}
            @Override public void onError() {}
        });

        Picasso.with(getActivity()).load("https://imageneslive4teach.000webhostapp.com/imagenes/varios.jpg").centerCrop()
                .placeholder(R.drawable.internetconnection).fit().into(imagen_quinta, new Callback() {
            @Override public void onSuccess() {}
            @Override public void onError() {}
        });


        primer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrimerCurso.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });


        segundo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SegundoCurso.class);
                startActivity(intent);
               // getActivity().finish();
            }
        });
        tercer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TercerCurso.class);
                startActivity(intent);
          //      getActivity().finish();
            }
        });
        cuarto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CuartoCurso.class);
                startActivity(intent);
            //    getActivity().finish();
            }
        });

     /*   imagen_quinta = myView.findViewById(R.id.imagen_quinta);
        Picasso.with(getActivity()).load("https://pruebalive4teach.000webhostapp.com/imagenes/primer_curso.jpg")
                .placeholder(R.drawable.internetconnection).fit().into(imagen_quinta, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });


        imagen_novena= myView.findViewById(R.id.imagen_novena);
        Picasso.with(getActivity()).load("https://pruebalive4teach.000webhostapp.com/imagenes/primer_curso.jpg")
                .placeholder(R.drawable.internetconnection).fit().into(imagen_novena, new Callback() {
            @Override public    void onSuccess() {}
            @Override public void onError() {}
        });
**/
        mRecyclerView = myView.findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        mexampleItems = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(getActivity());

        parseJSON();
        return myView;
    }

    private void parseJSON() {

        String url = "http://dybcatering.com/back_live_app/cursos/listarcursos.php";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("Registros");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String nombre = hit.getString("name");
                                String categoria = hit.getString("id_category");
                                String subcategoria = hit.getString("id_subcategory");
                                String metodologia = hit.getString("methodology");
                                String bienvenida = hit.getString("welcome");
                                String intensidad = hit.getString("intention");
                                String intensidadac = hit.getString("intensityAC");
                                String competencias = hit.getString("competences");
                                String intensidadta = hit.getString("intensityTA");
                                String logro = hit.getString("Achievement");
                                String indicadora = hit.getString("indicatorA");
                                String mapa = hit.getString("map");
                                String metodologiag = hit.getString("methodologyG");
                                String tipo = hit.getString("type");
                                String descripcion = hit.getString("description");
                                String presentacion = hit.getString("presentation");
                                String iduser = hit.getString("id_user");
                                String descripciono = hit.getString("descriptionO");
                                String actualizadoen = hit.getString("updated_at");
                                String creadoen = hit.getString("created_at");
                                String estado = hit.getString("state");
                                String publicado = hit.getString("publish");
                                String imagen= hit.getString("image");
                                mexampleItems.add(new ExampleItem( nombre, categoria, subcategoria, metodologia, bienvenida, intensidad, intensidadac, competencias
                                , intensidadta, logro, indicadora, mapa, metodologiag, tipo, descripcion, presentacion, iduser, descripciono, actualizadoen, creadoen, estado, publicado, imagen));

                            }

                            mExampleAdaptor = new ExampleAdaptor(getActivity(), mexampleItems);
                            mRecyclerView.setAdapter(mExampleAdaptor);
                            mExampleAdaptor.setOnClickItemListener(CursosFragment.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);

    }

    @Override
    public void onItemClick(int position) {


    }
}
