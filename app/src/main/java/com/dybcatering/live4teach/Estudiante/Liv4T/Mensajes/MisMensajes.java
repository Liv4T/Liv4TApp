package com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisMensajes extends Fragment {


    public MisMensajes() {
        // Required empty public constructor
    }
    View myView;
    CardView mensajeuno;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

                myView = inflater.inflate(R.layout.fragment_mis_mensajes, container, false);
        // Inflate the layout for this fragment

        mensajeuno = myView.findViewById(R.id.carduno);

        mensajeuno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarMensajesDetalle();
            }
        });

        return myView;
    }

    private void IniciarMensajesDetalle() {
        Fragment perfil = new MisMensajesDetalle();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
