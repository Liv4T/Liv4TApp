package com.dybcatering.live4teach.Tutor.Calificaciones.InsertCalificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class CalificacionesTutorInsertFragment extends Fragment {

	public CalificacionesTutorInsertFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_calificaciones_tutor_insert, container, false);
		return view;
	}

}
