package com.dybcatering.live4teach.Tutor.Actividades.InsertCuestionario4Opciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;
public class MisActividadesTutorInsertCuestionario4Opciones extends Fragment {
	public MisActividadesTutorInsertCuestionario4Opciones() {
		// Required empty public constructor
	}


	public Button guardar;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view =inflater.inflate(R.layout.fragment_mis_actividades_tutor_insert_cuestionario4_opciones, container, false);

		guardar = view.findViewById(R.id.btnguardar);

		String respuesta = "Continuar";



		return view;
	}

}
