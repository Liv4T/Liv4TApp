package com.dybcatering.live4teach.MisCursos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class MisCursosDetalle extends YouTubeBaseActivity {
    private ExpandableListView expandableListView;

    YouTubePlayerView youTubePlayerView;
    LottieAnimationView button ;
    YouTubePlayer.OnInitializedListener onInitializedListener;

    private static final String TAG = "MisCursosDetalle";
    String[] parent = new String[]{"Primera unidad", "Segunda Unidad", "Tercera Unidad", "Cuarta Unidad"};
    String[] q1 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q2 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q3 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] q4 = new String[]{"Primer Tema", "Segundo Tema", "Tercer Tema", "Cuarto Tema"};
    String[] des1 = new String[]{"A layout that organizes its children into a single horizontal or vertical row. It creates a scrollbar if the length of the window exceeds the length of the screen."};
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
    List<LinkedHashMap<String, String[]>> data = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mis_cursos_detalle);
        setUpAdapter();
        Log.d(TAG,"Iniciando recursos");
        youTubePlayerView = findViewById(R.id.youTube);
        button = findViewById(R.id.buttoniniciar);



        onInitializedListener = new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                //List<String> videolist = new ArrayList<>();
                //videolist.add("YQRHrco73g4");
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
            }
        });
    }

    private void setUpAdapter() {
        secondLevel.add(q1);
        secondLevel.add(q2);
        secondLevel.add(q3);
        secondLevel.add(q4);
        thirdLevelq1.put(q1[0], des1);
        thirdLevelq1.put(q1[1], des2);
        thirdLevelq1.put(q1[2], des3);
        thirdLevelq1.put(q1[3], des4);
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

        expandableListView = (ExpandableListView) findViewById(R.id.expandible_listview);
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



    }

}
