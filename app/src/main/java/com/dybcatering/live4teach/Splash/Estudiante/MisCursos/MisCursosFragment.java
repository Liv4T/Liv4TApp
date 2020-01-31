package com.dybcatering.live4teach.Splash.Estudiante.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetalle.MisCursosDetalleFragment;

public class MisCursosFragment extends Fragment {
    View myView;
    Button button;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_cursos, container, false);

        button = myView.findViewById(R.id.btnQuimica);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             swapFragment();
            }
        });

        return myView;
    }


    private void swapFragment(){
        Fragment someFragment = new MisCursosDetalleFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, someFragment ).addToBackStack("tag"); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
