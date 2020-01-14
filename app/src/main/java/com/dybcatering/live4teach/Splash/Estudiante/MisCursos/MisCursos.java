package com.dybcatering.live4teach.Splash.Estudiante.MisCursos;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;

public class MisCursos extends Fragment implements NavigationView.OnNavigationItemSelectedListener {
    View myView;
    Button button;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.mis_cursos, container, false);

        button = myView.findViewById(R.id.btnQuimica);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Intent intent = new Intent(getActivity(), MisCursosDetalle.class);
               // startActivity(intent);

            }
        });



        return myView;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }
}
