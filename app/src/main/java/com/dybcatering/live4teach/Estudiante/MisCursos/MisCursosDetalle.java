package com.dybcatering.live4teach.Estudiante.MisCursos;

import android.os.Handler;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.Estudiante.CursosDisponibles.Adapter.ExpandableListAdapter;
import com.dybcatering.live4teach.Estudiante.CursosDisponibles.PrimerCurso;
import com.dybcatering.live4teach.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

public class MisCursosDetalle extends YouTubeBaseActivity {
    private ExpandableListView expandableListView;
    private int progressStatus = 0;
    YouTubePlayerView youTubePlayerView;
    LottieAnimationView button ;
    ProgressBar progressBar;
    YouTubePlayer.OnInitializedListener onInitializedListener;
    private Handler handler = new Handler();

    private static final String TAG = "MisCursosDetalle";
    String[] parent = new String[]{"Primera unidad", "Segunda Unidad", "Tercera Unidad", "Cuarta Unidad"};
    String[] q1 = new String[]{"Requisitos para realizar el curso","Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q2 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q3 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q4 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] des0 = new String[]{""};
    String[] des1 = new String[]{"A la yout that organizes its children into a single horizontal or vertical row. It creates a scrollbar if the length of the window exceeds the length of the screen."};
    String[] des2 = new String[]{"Enables you to specify the location of child objects relative to each other (child A to the left of child B) or to the parent (aligned to the top of the parent)."};
    String[] des3 = new String[]{"This list contains linear layout information"};
    String[] des4 = new String[]{"This list contains relative layout information,Displays a scrolling grid of columns and rows"};
    String[] des5 = new String[]{"Under the RecyclerView model, several different components work together to display your data. Some of these components can be used in their unmodified form; for example, your app is likely to use the RecyclerView class directly. In other cases, we provide an abstract class, and your app is expected to extend it; for example, every app that uses RecyclerView needs to define its own view holder, which it does by extending the abstract RecyclerView.ViewHolder class."};
    String[] des6 = new String[]{"Under the RecyclerView model, several different components work together to display your data. Some of these components can be used in their unmodified form; for example, your app is likely to use the RecyclerView class directly. In other cases, we provide an abstract class, and your app is expected to extend it; for example, every app that uses RecyclerView needs to define its own view holder, which it does by extending the abstract RecyclerView.ViewHolder class."};

    LinkedHashMap<String, String[]> thirdLevelq1 = new LinkedHashMap<>();
    LinkedHashMap<String, String[]> thirdLevelq2 = new LinkedHashMap<>();
    LinkedHashMap<String, String[]> thirdLevelq3 = new LinkedHashMap<>();
    LinkedHashMap<String, String[]> thirdLevelq4 = new LinkedHashMap<>();
    /**
     * Second level array list
     */
    List<String[]> secondLevel = new ArrayList<>();
    /**
     * Inner level data
     */

    List<String> vacio = new ArrayList<>();



    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();

    TextView descText, segunda_desc, tercera_desc;
    ImageButton show, hide, show2, hide2, show3, hide3;


    private ExpandableListView listView;
    private ExpandableListAdapter listAdapter;
    private List<String> listDataHeader;
    private HashMap<String, List<String >> listHashMap;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cursos_detalle);
   //     setUpAdapter();
        Log.d(TAG,"Iniciando recursos");
        youTubePlayerView = findViewById(R.id.youTube);
        button = findViewById(R.id.buttoniniciar);

        progressBar = findViewById(R.id.progressBar_horizontal);

        descText = (TextView) findViewById(R.id.description_text);
        show = (ImageButton) findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                show.setVisibility(View.INVISIBLE);
                hide.setVisibility(View.VISIBLE);
                descText.setMaxLines(Integer.MAX_VALUE);
               //ocultar los otros, se debe implementar en los demas

                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);

            }
        });
        hide = (ImageButton) findViewById(R.id.hide);
        hide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);

            }
        });

        segunda_desc = findViewById(R.id.segunda_descripcion);
        show2 = findViewById(R.id.vermassegundo);
        show2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show2.setVisibility(View.INVISIBLE);
                hide2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(Integer.MAX_VALUE);

                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);

            }
        });
        hide2 = findViewById(R.id.hide2);
        hide2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);
            }
        });

        tercera_desc = findViewById(R.id.tercera_descripcion);
        show3= findViewById(R.id.vermastercero);
        show3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                show3.setVisibility(View.INVISIBLE);
                hide3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(Integer.MAX_VALUE);

                hide.setVisibility(View.INVISIBLE);
                show.setVisibility(View.VISIBLE);
                descText.setMaxLines(5);
                hide2.setVisibility(View.INVISIBLE);
                show2.setVisibility(View.VISIBLE);
                segunda_desc.setMaxLines(5);


            }
        });
        hide3 = findViewById(R.id.hide3);
        hide3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hide3.setVisibility(View.INVISIBLE);
                show3.setVisibility(View.VISIBLE);
                tercera_desc.setMaxLines(5);
            }
        });

        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                Log.d(TAG, "Iniciando video");
                youTubePlayer.loadVideo("YQRHrco73g4");
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                    Log.d(TAG, "Falla");
            }
        };

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG,"se realizo click");
                youTubePlayerView.initialize(YouTubeConfig.getApiKey(), onInitializedListener);


                Log.d(TAG, "exito");

                if (progressStatus == 100) {
                    progressStatus = 0;
                }

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        while (progressStatus < 100) {
                            // Update the progress status
                            progressStatus += 1;

                            // Try to sleep the thread for 20 milliseconds
                            try {
                                Thread.sleep(80);  //3 seconds
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                            // Update the progress bar
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    progressBar.setProgress(progressStatus);
                                    // Show the progress on TextView
                                    //tv.setText(progressStatus + "/100");
                                }
                            });
                        }
                    }
                }).start();
            }
        });


    }


    private void initData() {
        listDataHeader = new ArrayList<>();
        listHashMap = new HashMap<>();

        listDataHeader.add("¿Qué tipo de contenidos crear para atraer tráfico?");
        listDataHeader.add("¿Qué canales usar según tu tipo de negocio?");
        listDataHeader.add("¿Cómo segmentar tu público según tu negocio?");
        listDataHeader.add("¿Qué es un crecimiento orgánico y uno pago?");
        listDataHeader.add("¿Cómo medir mi presupuesto publicitario?");

        List<String> edmt = new ArrayList<>();
        edmt.add("Lista Expandible");

        List<String> androidstudio = new ArrayList<>();
        androidstudio.add("Expandcible lista");
        androidstudio.add("Google");
        androidstudio.add("Hola");
        androidstudio.add("Chat App Firebase");



        List<String> xamarin= new ArrayList<>();
        xamarin.add("Segunda Expandcible lista");
        xamarin.add("Segunda Google");
        xamarin.add("Segunda Hola");
        xamarin.add("Segunda Chat App Firebase");


        List<String> uwp= new ArrayList<>();
        uwp.add("UWP Expandcible lista");
        uwp.add("UWP Google");
        uwp.add("UWP Hola");
        uwp.add("UWP Chat App Firebase");

        List<String> a = new ArrayList<>();

//        a.add("");
        listHashMap.put(listDataHeader.get(0), edmt);
        listHashMap.put(listDataHeader.get(1), androidstudio);
        listHashMap.put(listDataHeader.get(2), xamarin);
        listHashMap.put(listDataHeader.get(3), uwp);
        listHashMap.put(listDataHeader.get(4), a);



    }

   /* private void setUpAdapter() {
        secondLevel.add(q1);
        secondLevel.add(q2);
        secondLevel.add(q3);
        secondLevel.add(q4);
        //thirdLevelq1.put(String.valueOf(vacio.get(0)), des0);
        thirdLevelq1.put(q1[0], des0);
        thirdLevelq1.put(q1[1], des1);
        thirdLevelq1.put(q1[2], des2);
        thirdLevelq1.put(q1[3], des3);
        thirdLevelq1.put(q1[4], des4);
        thirdLevelq2.put(q2[0], des3);
        thirdLevelq2.put(q2[1], des4);
        thirdLevelq2.put(q2[2], des5);
        thirdLevelq2.put(q2[3], des6);
        thirdLevelq3.put(q3[0], des5);
        thirdLevelq3.put(q3[1], des6);
        thirdLevelq3.put(q3[2], des1);
        thirdLevelq3.put(q3[3], des2);
        thirdLevelq4.put(q4[0], des1);
        thirdLevelq4.put(q4[1], des2);
        thirdLevelq4.put(q4[2], des3);
        thirdLevelq4.put(q4[3], des4);
        data.add(thirdLevelq1);
        data.add(thirdLevelq2);
        data.add(thirdLevelq3);
        data.add(thirdLevelq4);

        expandableListView = findViewById(R.id.expandible_listview);
        //passing three level of information to constructor
        ThreeLevelListAdapter threeLevelListAdapterAdapter = new ThreeLevelListAdapter(this, parent, secondLevel, data);
        expandableListView.setAdapter(threeLevelListAdapterAdapter);
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            int previousGroup = -1;

            @Override
            public void onGroupExpand(int groupPosition) {
                if (groupPosition != previousGroup)
                    expandableListView.collapseGroup(previousGroup);
                previousGroup = groupPosition;
            }
        });



    }*/

}
