package com.dybcatering.live4teach.MisActividades;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.MisCursos.MisCursosDetalle;
import com.dybcatering.live4teach.R;

public class MisActividades extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    CardView cardView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mis_actividades, container, false);

        cardView = myView.findViewById(R.id.misactividadesCardprimero);

        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iniciar = new Intent(getActivity(), MisActividadesDetalle.class);
                startActivity(iniciar);
            }
        });



        return myView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
