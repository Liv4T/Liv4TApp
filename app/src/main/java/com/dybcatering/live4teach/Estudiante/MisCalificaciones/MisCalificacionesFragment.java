package com.dybcatering.live4teach.Estudiante.MisCalificaciones;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;


public class MisCalificacionesFragment extends Fragment {
    View myView;
    private CardView cardview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        myView = inflater.inflate(R.layout.fragment_mis_calificaciones, container, false);

        cardview = myView.findViewById(R.id.micalificacionCardprimero);

        cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent nuevo = new Intent(getActivity(),MisCalificacionesDetalle.class);
                startActivity(nuevo);
            }
        });
        return myView;
    }

}
