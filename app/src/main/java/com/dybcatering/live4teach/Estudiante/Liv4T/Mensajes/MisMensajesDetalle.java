package com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;


public class MisMensajesDetalle extends Fragment {

    public MisMensajesDetalle() {
        // Required empty public constructor
    }

    View myView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView =inflater.inflate(R.layout.fragment_mis_mensajes_detalle, container, false);

        return myView;
    }
}
