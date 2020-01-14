package com.dybcatering.live4teach.Splash.Estudiante.MisActividades;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisActividades extends Fragment {
    View myView;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mis_actividades, container, false);

        cardView = myView.findViewById(R.id.misactividadesCardprimero);
       // final FragmentManager fragmentManager = getFragmentManager();
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // fragmentManager.beginTransaction()
                 //       .replace(R.id.content_frame
                   //             , new MisCursosTutor())
                     //   .commit();

                Intent iniciar = new Intent(getActivity(), MisActividadesDetalle.class);

                startActivity(iniciar);
            }
        });



        return myView;
    }
}
