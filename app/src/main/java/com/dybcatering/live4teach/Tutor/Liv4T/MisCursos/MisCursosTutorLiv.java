package com.dybcatering.live4teach.Tutor.Liv4T.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisCursosTutorLiv extends Fragment {


    public MisCursosTutorLiv() {
        // Required empty public constructor
    }

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_cursos_tutor_liv, container, false);
        return myView;
    }
}
