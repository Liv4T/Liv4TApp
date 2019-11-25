package com.dybcatering.live4teach.Tutor.Calificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;


public class CalificacionesTutorFragment extends Fragment {
	View myView;

	Button button;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_calificaciones_tutor, container, false);



		return myView;
	}

}
