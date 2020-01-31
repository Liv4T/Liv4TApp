package com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
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
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Adapter.ExampleAdaptor;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.Adapter.ExampleItem;

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
    public static final String EXTRAID = "id";
    public static final String EXTRANOMBRE = "name";
    public static final String EXTRACATEGORIA = "id_category";
    public static final String EXTRASUBCATEGORIA = "id_subcategory";
    public static final String EXTRAMETODOLOGIA = "methodology";
    public static final String EXTRABIENVENIDA = "welcome";
    public static final String EXTRAINTENSIDAD = "intention";
    public static final String EXTRAINTENSIDADAC = "intensityAC";
    public static final String EXTRACOMPENTENCIAS = "competences";
    public static final String EXTRAINTENSIDADTA = "intensityTA";
    public static final String EXTRALOGRO ="Achievement";
    public static final String EXTRAINDICADORA = "indicatorA";
    public static final String EXTRAMAPA = "map";
    public static final String EXTRAMETODOLOGIAG = "methodologyG";
    public static final String EXTRATIPO = "type";
    public static final String EXTRADESCRIPCION ="description";
    public static final String EXTRAPRESENTACION ="presentation";
    public static final String EXTRAIDUSER = "id_user";
    public static final String EXTRADESCRIPCIONO = "descriptionO";
    public static final String EXTRAACTUALIZADOEN = "updated_at";
    public static final String EXTRACREADOEN = "created_at";
    public static final String EXTRAESTADO = "state";
    public static final String EXTRAPUBLICADO = "publish";
    public static final String EXTRAIMAGEN = "image";
    public static final String EXTRAPRECIO = "price";
    public static final String EXTRAVIDEOPRESENTACION = "video_presentacion";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.primer_fragment, container, false);
        new CheckInternetConnection(getActivity()).checkConnection();
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
                                String id = hit.getString("id");
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
                                String precio = hit.getString("price");
                                String videopresentacion = hit.getString("video_presentacion");
                                mexampleItems.add(new ExampleItem(id, nombre, categoria, subcategoria, metodologia, bienvenida, intensidad, intensidadac, competencias, intensidadta, logro, indicadora, mapa, metodologiag, tipo, descripcion, presentacion, iduser, descripciono, actualizadoen, creadoen, estado, publicado, imagen, precio, videopresentacion));

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
        Intent intent = new Intent(getActivity(), CursosDetalle.class);
        ExampleItem clickedItem =mexampleItems.get(position);
        intent.putExtra(EXTRAID, clickedItem.getId());
        intent.putExtra(EXTRANOMBRE, clickedItem.getNombre());
        intent.putExtra(EXTRACATEGORIA, clickedItem.getCategoria());
        intent.putExtra(EXTRASUBCATEGORIA, clickedItem.getSubCategoria());
        intent.putExtra(EXTRAMETODOLOGIA, clickedItem.getMetodologia());
        intent.putExtra(EXTRABIENVENIDA, clickedItem.getBienvenida());
        intent.putExtra(EXTRAINTENSIDAD, clickedItem.getIntensidad());
        intent.putExtra(EXTRAINTENSIDADAC, clickedItem.getIntensidadAC());
        intent.putExtra(EXTRACOMPENTENCIAS, clickedItem.getCompetencias());
        intent.putExtra(EXTRAINTENSIDADTA, clickedItem.getIntensidadTA());
        intent.putExtra(EXTRALOGRO, clickedItem.getLogro());
        intent.putExtra(EXTRAINDICADORA, clickedItem.getIndicadorA());
        intent.putExtra(EXTRAMAPA, clickedItem.getMapa());
        intent.putExtra(EXTRAMETODOLOGIAG, clickedItem.getMetodologiaG());
        intent.putExtra(EXTRATIPO, clickedItem.getTipo());
        intent.putExtra(EXTRADESCRIPCION, clickedItem.getDescripcion());
        intent.putExtra(EXTRAPRESENTACION, clickedItem.getPresentacion());
        intent.putExtra(EXTRAIDUSER, clickedItem.getIdUsuario());
        intent.putExtra(EXTRADESCRIPCIONO, clickedItem.getDescripcionO());
        intent.putExtra(EXTRAACTUALIZADOEN, clickedItem.getActualizadoEn());
        intent.putExtra(EXTRACREADOEN, clickedItem.getCreadoEn());
        intent.putExtra(EXTRAESTADO, clickedItem.getEstado());
        intent.putExtra(EXTRAPUBLICADO, clickedItem.getPublicar());
        intent.putExtra(EXTRAIMAGEN, clickedItem.getImagen());
        intent.putExtra(EXTRAPRECIO, clickedItem.getPrecio());
        intent.putExtra(EXTRAVIDEOPRESENTACION, clickedItem.getVideoPresentacion());
        startActivity(intent);

    }
}
