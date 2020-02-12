package com.dybcatering.live4teach.Tutor.Actividades;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisActividadesTutorInsertFragment extends Fragment {

	public MisActividadesTutorInsertFragment() {
		// Required empty public constructor
	}


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

				View view = inflater.inflate(R.layout.fragment_mis_actividades_tutor_insert, container, false);
		// Inflate the layout for this fragment
		return view;
	}

}
