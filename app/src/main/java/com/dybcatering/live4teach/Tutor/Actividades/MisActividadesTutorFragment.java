package com.dybcatering.live4teach.Tutor.Actividades;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Calificaciones.CalificacionesDetalleTutorFragment;


public class MisActividadesTutorFragment extends Fragment {

	View view;

	CardView cardView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {

		view = inflater.inflate(R.layout.fragment_mis_actividades_tutor, container, false);
			cardView = view.findViewById(R.id.misactividadesCardprimerfragment);


			cardView.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iniciartransicion();
			}
		});
		return view;

	}

	public void iniciartransicion(){
		Fragment someFragment = new MisActividadesTutorDetalleFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();

	}

}