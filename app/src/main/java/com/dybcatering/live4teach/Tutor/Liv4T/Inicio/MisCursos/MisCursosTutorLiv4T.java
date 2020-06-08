package com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Liv4T.PlanificacionGeneral.PlanificacionGeneralTutorFragment;

public class MisCursosTutorLiv4T extends Fragment {


    public MisCursosTutorLiv4T() {
        // Required empty public constructor
    }

    View myView;

    Button btnGeneral, btnCrearSemana;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_cursos_tutor_liv4_t, container, false);

        btnGeneral = myView.findViewById(R.id.btnGeneral);
        btnCrearSemana = myView.findViewById(R.id.btnCrearSemana);

        btnGeneral.setOnClickListener(v -> {
             transicionMisCursos();

        });


        return myView;
    }

    private void transicionMisCursos() {
        Fragment planificacion = new PlanificacionGeneralTutorFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, planificacion); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

}
