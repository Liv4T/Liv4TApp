package com.dybcatering.live4teach.MisCertificados;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.MisCalificaciones.MisCalificacionesDetalle;
import com.dybcatering.live4teach.R;

public class MisCertificados extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mis_certificados, container, false);

       button = myView.findViewById(R.id.dfd);

       button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

              Intent nuevo = new Intent(getActivity(),MisCertificadosDetalle.class);
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
