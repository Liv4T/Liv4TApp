package com.dybcatering.live4teach.Tutor.Liv4T.Inicio.MisCursos.CrearSemana;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class CrearSemana extends Fragment {

    public CrearSemana() {
        // Required empty public constructor
    }

    View myView;

    EditText preguntaConductora, desarrolloClase, observacion;
    Button enviar;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        myView = inflater.inflate(R.layout.fragment_crear_semana, container, false);

        preguntaConductora = myView.findViewById(R.id.edPreguntaConductora);
        desarrolloClase = myView.findViewById(R.id.edDesarrolloClase);
        observacion = myView.findViewById(R.id.edObservacion);
        
        enviar = myView.findViewById(R.id.btnEnviar);
        
        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (preguntaConductora.getText().toString().isEmpty() || desarrolloClase.getText().toString().isEmpty() || observacion.getText().toString().isEmpty()){
                    Toast.makeText(getContext(), "Campos vacios", Toast.LENGTH_SHORT).show();
                }else{
                    enviarDatos(preguntaConductora.getText().toString(), desarrolloClase.getText().toString(), observacion.getText().toString());
                }
            }
        });

        return myView;
    }

    private void enviarDatos(String preguntaconductora, String desarrolloclase, String observacion) {



    }
}
