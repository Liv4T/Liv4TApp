package com.dybcatering.live4teach.Tutor.MisCursos;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.dybcatering.live4teach.R;

public class MisCursosTutorFragment extends Fragment {

	View myView;
	Button button;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_mis_cursos_tutor, container, false);

		button = myView.findViewById(R.id.btnQuimica);

		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IniciarTransicion();
			}
		});

		return myView ;
	}

	public void IniciarTransicion(){
		Fragment someFragment = new MisCursosDetalleTutorDetalleFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}
}
