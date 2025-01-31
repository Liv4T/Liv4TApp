package com.dybcatering.live4teach.Estudiante.Liv4T.Tareas;

import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.dybcatering.live4teach.Estudiante.MisCursos.AdapterDetalleVideosMisCursos.VideosAdaptor;
import com.dybcatering.live4teach.Estudiante.MisCursos.AdapterDetalleVideosMisCursos.VideosItem;
import com.dybcatering.live4teach.R;
import com.google.android.exoplayer2.DefaultLoadControl;
import com.google.android.exoplayer2.DefaultRenderersFactory;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

import java.io.File;
import java.util.ArrayList;


public class DetalleClase extends Fragment {

    public DetalleClase() {
        // Required empty public constructor
    }
    View myView;

    PlayerView playerView;
    private boolean playWhenReady = true;
    private int currentWindow = 0;
    private long playbackPosition = 0;
    //public ClasesFragment() {
    // Required empty public constructor
    //}
    private RecyclerView mRecyclerView;
    private VideosAdaptor misVideosAdaptor;
    private ArrayList<VideosItem> mvideosItems;
    private RequestQueue mRequestQueue;
    private SimpleExoPlayer player;
    TextView enlace, nombreclase, descripcion, documento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_detalle_clase, container, false);

        Bundle bundle = getArguments();
        String mNombre = bundle.getString("nombre");
        String mDescripcion = bundle.getString("descripcion");
        String mDocumento = bundle.getString("documento");
        String mNombreDocumento = bundle.getString("nombre_documento");
        String mIdWeekly = bundle.getString("id_weekly");
        String mUrl = bundle.getString("url");
        String mVideo = bundle.getString("video");


         playerView = myView.findViewById(R.id.video_view);

         enlace = myView.findViewById(R.id.enlace);
         nombreclase = myView.findViewById(R.id.nombreclase);
         descripcion = myView.findViewById(R.id.descripcion);
         documento = myView.findViewById(R.id.documento);

         nombreclase.setText(mNombre);
         descripcion.setText(mDescripcion);
         enlace.setText(mUrl);

        enlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initializePlayer(mVideo);
            }
        });


        return myView;
    }

    private void initializePlayer(String mVideo) {

        SimpleExoPlayer player = ExoPlayerFactory.newSimpleInstance(
                new DefaultRenderersFactory(getContext()),
                new DefaultTrackSelector(), new DefaultLoadControl());



   //     String filePath = "https://www.liv4t.com/uploads/clases/Nomenclatura_orgánica.mp4";
        //Environment.getExternalStorageDirectory() + File.separator +
	//			"video" + File.separator + "video1.mp4";
      //  String link = "https://www.liv4t.com/uploads/clases/Nomenclatura_orgánica.mp4";
        Log.e("filepath", mVideo);
        Uri uri = Uri.parse(mVideo);
        ExtractorMediaSource audioSource = new ExtractorMediaSource(
                uri,
                new DefaultDataSourceFactory(getContext(), "MyExoplayer"),
                new DefaultExtractorsFactory(),
                null,
                null
        );

//		player.prepare(audioSource);
//		playerView.setPlayer(player);
//		player.setPlayWhenReady(true);

        player.setPlayWhenReady(playWhenReady);
        player.seekTo(currentWindow, playbackPosition);
        playerView.setPlayer(player);
        player.prepare(audioSource, true, true);

//		player.prepare(audioSource);
//		playerView.setPlayer(player);
//		player.setPlayWhenReady(false);


    }



    @Override
    public void onPause() {
        super.onPause();
        if (Util.SDK_INT < 24) {
            releasePlayer();
        }
    }


    private void releasePlayer() {
        if (player != null) {
            playWhenReady = player.getPlayWhenReady();
            playbackPosition = player.getCurrentPosition();
            currentWindow = player.getCurrentWindowIndex();
            player.release();
            player = null;
        }
    }


}
