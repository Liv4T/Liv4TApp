package com.dybcatering.live4teach.Estudiante.ConsultasyTutorias.InsertConsultasyTutoriasEstudiante;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class InsertConsultasyTutorias extends Fragment {

	public InsertConsultasyTutorias() {
		// Required empty public constructor
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		View view = inflater.inflate(R.layout.fragment_insert_consultasy_tutorias, container, false);
		return view;
	}

}
