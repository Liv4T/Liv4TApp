package com.dybcatering.live4teach.Estudiante.Liv4T.Tareas;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.dybcatering.live4teach.Estudiante.Liv4T.Memorama.HardLevel;
import com.dybcatering.live4teach.Estudiante.Liv4T.Pizarra.PizarraFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Memorama.Home;
import com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes.MisMensajesDetalle;
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

import java.util.ArrayList;


public class TareasFragment extends Fragment {


   View MyView;
    LinearLayout quimica, fisica;

    CardView objetivos1, objetivos2;

    TextView semana1, semana2, clase1semana1;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MyView =  inflater.inflate(R.layout.fragment_tareas, container, false);

        objetivos1 = MyView.findViewById(R.id.objetivos);
        objetivos2 = MyView.findViewById(R.id.objetivos2);

       // playerView = view.findViewById(R.id.video_view);

        semana1 = MyView.findViewById(R.id.txtSemana1);
        semana2 = MyView.findViewById(R.id.semana2);

        clase1semana1 = MyView.findViewById(R.id.clase1semana1);

        objetivos1.setVisibility(View.GONE);
        objetivos2.setVisibility(View.GONE);


        semana1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objetivos2.setVisibility(View.GONE);
                objetivos1.setVisibility(View.VISIBLE);
            }
        });
        semana2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                objetivos2.setVisibility(View.VISIBLE);
                objetivos1.setVisibility(View.GONE);
            }
        });


        clase1semana1.setOnClickListener(v -> {
          //  Toast.makeText(getContext(), "exito", Toast.LENGTH_SHORT).show();
            IniciarDetalle();

        });

/*
        quimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              IniciarMemorama();
            }
        });

        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarPizarra();
            }
        });
 */




        return MyView;
    }

    private void IniciarDetalle() {
        Fragment detalle = new DetalleClase();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, detalle);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void IniciarPizarra() {
        Fragment perfil = new PizarraFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void IniciarMemorama() {
        Fragment perfil = new HardLevel();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }




}
