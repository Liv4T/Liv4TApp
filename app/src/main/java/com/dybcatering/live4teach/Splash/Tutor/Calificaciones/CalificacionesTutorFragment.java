package com.dybcatering.live4teach.Splash.Tutor.Calificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dybcatering.live4teach.R;


public class CalificacionesTutorFragment extends Fragment {
	View myView;

	LinearLayout linearLayout;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_calificaciones_tutor, container, false);


		linearLayout= myView.findViewById(R.id.LnQuimicaCalificaciones);

		linearLayout.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iniciartransicion();
			}
		});

		return myView;
	}

	private void iniciartransicion(){
		Fragment someFragment = new CalificacionesDetalleTutorFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}

}
