package com.dybcatering.live4teach.Tutor.Liv4T;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dybcatering.live4teach.Estudiante.Liv4T.Horario.HorarioFragment;
import com.dybcatering.live4teach.Estudiante.Liv4T.Perfil.PerfilEstudiante;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;

import java.util.HashMap;

public class InicioTutor extends Fragment {

    public InicioTutor() {
        // Required empty public constructor
    }

    View myView;
    SessionManager sessionManager;
    String id;

    LinearLayout linearhorario;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_inicio_tutor, container, false);
        linearhorario = myView.findViewById(R.id.linearhorario);
        sessionManager = new SessionManager(getContext());
        HashMap<String, String> user = sessionManager.getUserDetail();
        id = user.get(SessionManager.NAME);
        Toast.makeText(getContext(), ""+id, Toast.LENGTH_SHORT).show();

        linearhorario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transicionhorario();
            }
        });


        return myView;
    }

    private void transicionhorario() {
        Fragment perfil = new HorarioFragment();
        //tvname.setText("Daniel");
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, perfil); // give your fragment container id in first parameter
        transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
        transaction.commit();
    }
}
