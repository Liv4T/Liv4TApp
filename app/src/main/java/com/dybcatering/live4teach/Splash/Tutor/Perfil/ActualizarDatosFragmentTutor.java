package com.dybcatering.live4teach.Splash.Tutor.Perfil;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class ActualizarDatosFragmentTutor extends Fragment {

 //   public String nombre;

    public EditText actualizardatos_nombre;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate (R.layout.fragment_actualizar_datos_tutor, container, false);

        actualizardatos_nombre = view.findViewById(R.id.nombre_update);
        Bundle bundle = getArguments();
        if (bundle != null) {
            String nombre = bundle.getString("nombre");
            //Toast.makeText(getActivity(), "El mensaje es " + nombre, Toast.LENGTH_SHORT).show();
            actualizardatos_nombre.setText(nombre);
        }

        return view;
    }

}
