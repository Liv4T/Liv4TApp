package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Splash.Estudiante.Inicio.InicioActivity;


public class AcercaCursoDetalleFragment extends Fragment {

	public AcercaCursoDetalleFragment() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		return inflater.inflate(R.layout.fragment_acerca_curso_detalle, container, false);
	}

	@Override
	public void onDestroy() {
		Intent intent = new Intent(getActivity(), InicioActivity.class);
		startActivity(intent);
		super.onDestroy();

	}
}
