package com.dybcatering.live4teach.Estudiante.Liv4T.Tareas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dybcatering.live4teach.Estudiante.Liv4T.Memorama.HardLevel;
import com.dybcatering.live4teach.Estudiante.Liv4T.Pizarra.PizarraFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Memorama.Home;
import com.dybcatering.live4teach.Estudiante.Liv4T.Mensajes.MisMensajesDetalle;
import com.dybcatering.live4teach.R;


public class TareasFragment extends Fragment {


   View MyView;
    LinearLayout quimica, fisica;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        MyView =  inflater.inflate(R.layout.fragment_tareas, container, false);

        quimica = MyView.findViewById(R.id.linearquimica);
        fisica = MyView.findViewById(R.id.linearfisica);

        quimica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              IniciarMemorama();
            }
        });

        fisica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IniciarPizarra();
            }
        });

        return MyView;
    }

    private void IniciarPizarra() {
        Fragment perfil = new PizarraFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }

    private void IniciarMemorama() {
        Fragment perfil = new HardLevel();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
