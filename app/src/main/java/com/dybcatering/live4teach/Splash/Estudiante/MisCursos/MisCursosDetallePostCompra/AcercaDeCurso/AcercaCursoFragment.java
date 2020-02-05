package com.dybcatering.live4teach.Splash.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.dybcatering.live4teach.R;

public class AcercaCursoFragment extends Fragment {
	View view;
	public AcercaCursoFragment() {
		// Required empty public constructor
	}
	LinearLayout acercade, compartir, actividades;
	public static final String EXTRAID= "id";
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		view = inflater.inflate(R.layout.fragment_acerca_curso, container, false);
		acercade = view.findViewById(R.id.about);
		compartir = view.findViewById(R.id.share);
		actividades = view.findViewById(R.id.activity);
		acercade.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				TransicionAcercade();
				//Toast.makeText(getActivity(), "hola", Toast.LENGTH_SHORT).show();
			}
		});
		compartir.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				CompartirCurso();
			}
		});

		actividades.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				IniciarPantallaActividades();
			}
		});




		//Toast.makeText(getContext(), "el texto es "+ idrecibido, Toast.LENGTH_SHORT).show();
		return view;
	}

	public void TransicionAcercade(){
//		Fragment someFragment = new AcercaCursoDetalleFragment();
//		MisCursosItem clickedItem = mcursosItems.get(position);
	//	Bundle arguments = new Bundle();
	//	arguments.putString(EXTRAID, "id");
//		FragmentTransaction transaction = getFragmentManager().beginTransaction();

//		transaction.replace(R.id.fragment_container, someFragment ).addToBackStack("tag"); // give your fragment container id in first parameter
//		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
//		transaction.commit();
		Bundle arguments = getParentFragment().getArguments();

		String idrecibido = arguments.getString("id");

		Intent intent = new Intent(getActivity(), AcercaCursoActivity.class);
		intent.putExtra(EXTRAID, idrecibido);


		startActivity(intent);
	}

	private void CompartirCurso() {
	}

	private void IniciarPantallaActividades() {
	}



}
