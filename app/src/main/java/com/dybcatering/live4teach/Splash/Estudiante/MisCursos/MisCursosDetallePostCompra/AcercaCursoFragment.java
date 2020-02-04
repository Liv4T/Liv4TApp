package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.dybcatering.live4teach.R;

public class AcercaCursoFragment extends Fragment {
	View view;
	public AcercaCursoFragment() {
		// Required empty public constructor
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_acerca_curso, container, false);

		Bundle arguments = getParentFragment().getArguments();

		String idrecibido = arguments.getString("id");

		Toast.makeText(getContext(), "el texto es "+ idrecibido, Toast.LENGTH_SHORT).show();
		return view;
	}

}
