package com.dybcatering.live4teach.Tutor.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;
public class MisCursosDetalleTutorDetalleFragment extends Fragment {

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment

		view =	inflater.inflate(R.layout.fragment_mis_cursos_detalle_tutor_detalle, container, false);
		return view;
	}

}
