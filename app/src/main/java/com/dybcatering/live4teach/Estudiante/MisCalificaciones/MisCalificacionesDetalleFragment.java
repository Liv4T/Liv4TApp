package com.dybcatering.live4teach.Estudiante.MisCalificaciones;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisCalificacionesDetalleFragment extends Fragment {

	View myView;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		inflater.inflate(R.layout.fragment_mis_calificaciones_detalle, container, false);


		return  myView;//.inflate(R.layout.fragment_mis_calificaciones_detalle, container, false);
	}
}

