package com.dybcatering.live4teach.Splash.Estudiante.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.MisCursosAdaptor;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.MisCursosItem;
import com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePreCompra.MisCursosDetalleFragment;

import java.util.ArrayList;

public class MisCursosFragment extends Fragment {
    View myView;
    private RecyclerView mRecyclerView;
    private MisCursosAdaptor mExampleAdaptor;
    private ArrayList<MisCursosItem> mexampleItems;
    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_mis_cursos, container, false);


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
