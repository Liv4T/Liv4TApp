package com.dybcatering.live4teach.Tutor.MisCursos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;

public class MisCursosTutor extends Fragment {

	View view;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_mis_cursos_tutor, container, false);
		// Inflate the layout for this fragment
		return  view;//inflater.inflate(R.layout.fragment_mis_cursos_tutor, container, false);
	}


}
