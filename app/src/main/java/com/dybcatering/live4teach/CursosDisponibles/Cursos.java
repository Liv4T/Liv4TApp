package com.dybcatering.live4teach.CursosDisponibles;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.dybcatering.live4teach.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;
import com.squareup.picasso.Picasso;

public class Cursos extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;

    public ImageView imagen, imagen_quinta, imagen_novena;
    public CardView primer_card,segundo_card, tercer_card, cuarto_card, quinto_card, sexto_card, septimo_card, octavo_card, noveno_card, decimo_card, onceavo_card, doceavo_card ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.primer_fragment, container, false);
        new CheckInternetConnection(getActivity()).checkConnection();
        imagen = myView.findViewById(R.id.imagen_primera);
        primer_card = myView.findViewById(R.id.primer_card);
        segundo_card = myView.findViewById(R.id.card_two);
        tercer_card = myView.findViewById(R.id.card_third);
        cuarto_card = myView.findViewById(R.id.card_fourth);
        Picasso.with(getActivity()).load("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg")

                .fit().
                //      resize(400,400).
                // resize(100,100).
                        centerInside().

                into(imagen);

        primer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), PrimerCurso.class);
                startActivity(intent);
            }
        });


        segundo_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SegundoCurso.class);
                startActivity(intent);
            }
        });
        tercer_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), TercerCurso.class);
                startActivity(intent);
            }
        });
        cuarto_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), CuartoCurso.class);
                startActivity(intent);
            }
        });

        imagen_quinta = myView.findViewById(R.id.imagen_quinta);
        Picasso.with(getActivity()).load("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg")

                .fit().
                //      resize(400,400).
                // resize(100,100).
                        centerInside().

                into(imagen_quinta);


        imagen_novena= myView.findViewById(R.id.imagen_novena);
        Picasso.with(getActivity()).load("https://dev-res.thumbr.io/libraries/27/08/11/lib/1469777955350_1.jpg?size=854x493s&ext=jpg")

                .fit().
                //      resize(400,400).
                // resize(100,100).
                        centerInside().

                into(imagen_novena);


        return myView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
