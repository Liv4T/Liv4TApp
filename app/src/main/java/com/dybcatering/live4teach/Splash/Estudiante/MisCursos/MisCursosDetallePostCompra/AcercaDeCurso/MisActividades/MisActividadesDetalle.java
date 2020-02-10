package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.MisActividades.Cuestionario.CuestionarioActivity;

import java.util.HashMap;
import java.util.List;

import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAACTIVITY;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAACTIVITYTYPE;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRADELIVERABLES;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAESTIMATEDDURATIONAUTONOMOUSWORK;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAESTIMATEDDURATIONPLATFORM;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAEVALUATIONCRITERIA1;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAEVALUATIONCRITERIA2;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAEVALUATIONCRITERIA3;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAEVIDENCESEND;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAFEEDBACKDATE;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAID;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAIDUSER;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAINTERVENINGACTOR;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAMOMENTEVALUATIONFROM;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAMOMENTEVALUATIONUP;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRANOMBRE;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRANOMBRECURSO;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAORIGINRESOURCES1;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAORIGINRESOURCES2;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAORIGINRESOURCES3;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRATHEMECONTEXTUALIZATION;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRATYPERESOURCES1;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRATYPERESOURCES2;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRATYPERESOURCES3;
import static com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment.EXTRAWORKTIME;

public class MisActividadesDetalle extends AppCompatActivity {
   // private ExpandableListView listView;
   // private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String >> listHashMap;
    ImageButton show, hide, show2, hide2, show3, hide3;
    TextView txtNombreCurso, txtDescripcion, txtIntension, txtCompetencias, txtImagen;

    TextView  segunda_desc, tercera_desc;
    Button btnIniciarActividad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_actividades_detalle);
        new CheckInternetConnection(this).checkConnection();
        Intent intent = getIntent();

        final String id = intent.getStringExtra(EXTRAID);
        final String name = intent.getStringExtra(EXTRANOMBRE);
        final String nombrecurso = intent.getStringExtra(EXTRANOMBRECURSO);
        final String idusuario = intent.getStringExtra(EXTRAIDUSER);
        final String acttivitytype = intent.getStringExtra(EXTRAACTIVITYTYPE);
        final String durationplatform = intent.getStringExtra(EXTRAESTIMATEDDURATIONPLATFORM);
        final String durationeautonomouswork = intent.getStringExtra(EXTRAESTIMATEDDURATIONAUTONOMOUSWORK);
        final String themecontextualization = intent.getStringExtra(EXTRATHEMECONTEXTUALIZATION);
        final String activity = intent.getStringExtra(EXTRAACTIVITY);
        final String typeresources1 = intent.getStringExtra(EXTRATYPERESOURCES1);
        final String typeresources2 = intent.getStringExtra(EXTRATYPERESOURCES2);
        final String typeresources3 = intent.getStringExtra(EXTRATYPERESOURCES3);
        final String originresources1 = intent.getStringExtra(EXTRAORIGINRESOURCES1);
        final String originresources2 = intent.getStringExtra(EXTRAORIGINRESOURCES2);
        final String originresources3 = intent.getStringExtra(EXTRAORIGINRESOURCES3);
        final String deliverables = intent.getStringExtra(EXTRADELIVERABLES);
        final String evaluationcriteria1 = intent.getStringExtra(EXTRAEVALUATIONCRITERIA1);
        final String evaluationcriteria2 = intent.getStringExtra(EXTRAEVALUATIONCRITERIA2);
        final String evaluationcriteria3 = intent.getStringExtra(EXTRAEVALUATIONCRITERIA3);
        final String worktime = intent.getStringExtra(EXTRAWORKTIME);
        final String momentevaluationfrom = intent.getStringExtra(EXTRAMOMENTEVALUATIONFROM);
        final String momentevaluationup = intent.getStringExtra(EXTRAMOMENTEVALUATIONUP);
        final String evidencesend = intent.getStringExtra(EXTRAEVIDENCESEND);
        final String interveningactor = intent.getStringExtra(EXTRAINTERVENINGACTOR);
        final String feedbackdate = intent.getStringExtra(EXTRAFEEDBACKDATE);
        txtNombreCurso = findViewById(R.id.txtNombreCurso);
        txtDescripcion = findViewById(R.id.txtDescripcion);
        txtIntension = findViewById(R.id.description_text);
        txtCompetencias = findViewById(R.id.txtCompetencias);
        txtImagen = findViewById(R.id.textImagen);
        show = findViewById(R.id.show);
        hide = findViewById(R.id.hide);
        segunda_desc = findViewById(R.id.segunda_descripcion);
        show2 = findViewById(R.id.vermassegundo);
        hide2 = findViewById(R.id.hide2);
        tercera_desc = findViewById(R.id.tercera_descripcion);
        show3 = findViewById(R.id.vermastercero);
        hide3 = findViewById(R.id.hide3);
        btnIniciarActividad = findViewById(R.id.iniciaract);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                txtIntension.setMaxLines(Integer.MAX_VALUE);
                //ocultar los otros, se debe implementar en los demas

                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);

            }
        });
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                txtIntension.setMaxLines(5);

            }
        });

        show3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3.setVisibility(View.INVISIBLE);
                hide3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(Integer.MAX_VALUE);

                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                txtIntension.setMaxLines(5);
                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);


            }
        });
        show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2.setVisibility(View.INVISIBLE);
                hide2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(Integer.MAX_VALUE);

                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                txtIntension.setMaxLines(5);
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);

            }
        });
        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);
            }
        });

        hide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);
            }
        });
        btnIniciarActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarActividad();
            }
        });


/*        initData();
        listAdapter = new ExpandableListAdapter(this, listDataHeader, listHashMap);
        listView.setAdapter(listAdapter);

        listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(MisActividadesDetalle.this, "gg", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;
            @Override
            public void onGroupExpand(int groupPosition) {
                if(groupPosition != previousGroup)
                    listView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });
    }

 /*   private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("Química Orgánica II");
        listDataHeader.add("Ejercicios de formulación Química Orgánica");
        listDataHeader.add("¿Cómo segmentar tu público según tu negocio?");
        listDataHeader.add("¿Qué es un crecimiento orgánico y uno pago?");
        listDataHeader.add("¿Cómo medir mi presupuesto publicitario?");

        List<String> edmt = new ArrayList<>();
        edmt.add("La química del carbono");
        edmt.add("Nota: 9");
        edmt.add("Ensayo");
        edmt.add("Estado: Completado");

        List<String> androidstudio = new ArrayList<>();
        androidstudio.add("La química del carbono");
        androidstudio.add("Nota: 7");
        androidstudio.add("Caso de estudio");
        androidstudio.add("Recurso necesario:");
        androidstudio.add("Estado: En curso");



        List<String> xamarin= new ArrayList<>();
        xamarin.add("Mi primer ejercicio de química");
        xamarin.add("Nota: 8");
        xamarin.add("Cuestionario V-F");
        xamarin.add("Recurso necesario:");
        xamarin.add("Estado: Pendiente");

        List<String> uwp= new ArrayList<>();
          uwp.add("Mi tercer tema de quimica");
          uwp.add("Nota: 8");
          uwp.add("Cuestionario 4 opciones");
          uwp.add("Recurso necesario:");
          uwp.add("Estado: Completado");

        List<String> a = new ArrayList<>();

        a.add("Mi cuarto tema de quimica");
        a.add("Nota: 10");
        a.add("Juego Memorama");
        a.add("Recurso necesario:");
        a.add("Estado: En Curso");


        listHashMap.put(listDataHeader.get(0), edmt);
        listHashMap.put(listDataHeader.get(1), androidstudio);
        listHashMap.put(listDataHeader.get(2), xamarin);
        listHashMap.put(listDataHeader.get(3), uwp);
        listHashMap.put(listDataHeader.get(4), a);



    }*/
    }

    public void IniciarActividad(){
        Intent intent = new Intent(MisActividadesDetalle.this, CuestionarioActivity.class);
        startActivity(intent);
    }
}
