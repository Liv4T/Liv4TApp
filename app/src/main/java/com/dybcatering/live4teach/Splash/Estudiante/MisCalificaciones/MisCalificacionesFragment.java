package com.dybcatering.live4teach.Splash.Estudiante.MisCalificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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

                swapFragment();
            }
        });
        return myView;
    }

    private void swapFragment(){
        Fragment someFragment = new MisCalificacionesDetalleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

}
