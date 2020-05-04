package com.dybcatering.live4teach.Estudiante.Liv4T.Tareas;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;


public class TareasFragment extends Fragment {

    public TareasFragment() {
        // Required empty public constructor
    }

   View MyView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MyView =  inflater.inflate(R.layout.fragment_tareas, container, false);
        return MyView;
    }
}
