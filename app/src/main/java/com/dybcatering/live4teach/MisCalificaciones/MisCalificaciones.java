package com.dybcatering.live4teach.MisCalificaciones;

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
import android.widget.Toast;

import com.dybcatering.live4teach.MisActividades.MisActividadesDetalle;
import com.dybcatering.live4teach.R;

public class MisCalificaciones extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    private CardView cardview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mis_calificaciones, container, false);

        cardview = myView.findViewById(R.id.micalificacionCardprimero);

       cardview.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              Intent nuevo = new Intent(getActivity(),MisCalificacionesDetalle.class);
              startActivity(nuevo);
           }
       });



        return myView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
