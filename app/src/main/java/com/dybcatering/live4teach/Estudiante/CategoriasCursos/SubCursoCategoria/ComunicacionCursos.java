package com.dybcatering.live4teach.Estudiante.CategoriasCursos.SubCursoCategoria;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dybcatering.live4teach.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.Estudiante.Perfil.ActualizarDatosFragment;
import com.dybcatering.live4teach.R;

public class ComunicacionCursos extends Fragment {

	View myView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_comunicacion_cursos, container, false);
		new CheckInternetConnection(getActivity()).checkConnection();



		return myView;

	}

	private void swapFragment(){
		Fragment someFragment = new ActualizarDatosFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}


}
