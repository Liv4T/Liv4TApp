package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetalle;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class ClasesFragment extends Fragment {
	View view;
	public ClasesFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view =inflater.inflate(R.layout.fragment_clases, container, false);

		return view;
	}
}
