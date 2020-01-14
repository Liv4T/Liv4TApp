package com.dybcatering.live4teach.Splash.Tutor.Calificaciones;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.dybcatering.live4teach.Splash.Estudiante.CursosDisponibles.Adapter.ExpandableListAdapter;
import com.dybcatering.live4teach.Splash.Estudiante.InternetConnection.CheckInternetConnection;
import com.dybcatering.live4teach.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CalificacionesDetalleTutorFragment extends Fragment {

	View myView;
	private ExpandableListView listView;
	private ExpandableListAdapter listAdapter;
	private List<String> listDataHeader;
	private HashMap<String, List<String >> listHashMap;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView= inflater.inflate(R.layout.fragment_calificaciones_detalle_tutor, container, false);
		new CheckInternetConnection(getContext()).checkConnection();
		listView = myView.findViewById(R.id.expandmiscalificaciones);
		initData();
		listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listHashMap);
		listView.setAdapter(listAdapter);

		listView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				Toast.makeText(getActivity(), "Mensaje Toast de Ejemplo", Toast.LENGTH_SHORT).show();
				return false;
			}
		});

		listView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
			int previousGroup = -1;
			@Override
			public void onGroupExpand(int groupPosition) {
				if(groupPosition != previousGroup)
					listView.collapseGroup(previousGroup);
				previousGroup = groupPosition;
			}
		});
		return myView;
	}
	private void initData() {
		listDataHeader = new ArrayList<>();
		listHashMap = new HashMap<>();

		listDataHeader.add("Química Orgánica II");
		listDataHeader.add("Ejercicios de formulación Química Orgánica");
		listDataHeader.add("¿Cómo segmentar tu público según tu negocio?");
		listDataHeader.add("¿Qué es un crecimiento orgánico y uno pago?");
		listDataHeader.add("¿Cómo medir mi presupuesto publicitario?");
		listDataHeader.add("Formulas Quimicas");

		List<String> edmt = new ArrayList<>();
		edmt.add("La química del carbono");
		edmt.add("Nota: 9");
		edmt.add("Estado: Completado");

		List<String> androidstudio = new ArrayList<>();
		androidstudio.add("La química del carbono");
		androidstudio.add("Nota: 7");
		androidstudio.add("Estado: Completado");



		List<String> xamarin= new ArrayList<>();
		xamarin.add("Mi primer ejercicio de química");
		xamarin.add("Nota: 8");
		xamarin.add("Estado: Pendiente");

		List<String> uwp= new ArrayList<>();
		uwp.add("Mi tercer tema de quimica");
		uwp.add("Nota: 8");
		uwp.add("Estado: Completado");

		List<String> a = new ArrayList<>();
		a.add("Mi cuarto tema de quimica");
		a.add("Nota: 10");
		a.add("Estado: Completado");

		List<String> vacio= new ArrayList<>();



		listHashMap.put(listDataHeader.get(0), edmt);
		listHashMap.put(listDataHeader.get(1), androidstudio);
		listHashMap.put(listDataHeader.get(2), xamarin);
		listHashMap.put(listDataHeader.get(3), uwp);
		listHashMap.put(listDataHeader.get(4), a);
		listHashMap.put(listDataHeader.get(5),vacio);



	}
}
