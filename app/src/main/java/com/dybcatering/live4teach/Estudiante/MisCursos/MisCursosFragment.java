package com.dybcatering.live4teach.Estudiante.MisCursos;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;

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
         //      Intent intent = new Intent(getActivity(), MisCursosDetalle.class);
           //     startActivity(intent);
               // MisCursosDetalleFragment fragment = new MisCursosDetalleFragment();
               // FragmentManager fragmentManager = getFragmentManager();
               // FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
               // fragmentTransaction.replace(R.id.layo_ini_mis_cursos, fragment);
             //   fragmentTransaction.commit();
            swapFragment();
            }
        });

        return myView;
    }


    private void swapFragment(){
        MisCursosDetalleFragment misCursosDetalleFragment= new MisCursosDetalleFragment();
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.layo_ini, misCursosDetalleFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
