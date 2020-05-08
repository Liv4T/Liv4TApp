package com.dybcatering.live4teach.Estudiante.Liv4T.Notas;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class NotasFragment extends Fragment {

    public NotasFragment() {
        // Required empty public constructor
    }
    View myView;
    LinearLayout quimica, fisica;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_notas, container, false);

        quimica = myView.findViewById(R.id.linearquimica);
        fisica = myView.findViewById(R.id.linearfisica);

        quimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IniciarNotasQuimica();
            }
        });

        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarNotasFisica();
            }
        });
        return myView;
    }

    private void IniciarNotasQuimica() {
        Fragment perfil = new NotasDetalleFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void IniciarNotasFisica() {
        Toast.makeText(getContext(), "aa", Toast.LENGTH_SHORT).show();
    }


   
}
