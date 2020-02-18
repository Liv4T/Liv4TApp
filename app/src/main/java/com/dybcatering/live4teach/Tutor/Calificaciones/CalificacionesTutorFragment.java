package com.dybcatering.live4teach.Tutor.Calificaciones;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import com.android.volley.RequestQueue;
import com.dybcatering.live4teach.Login.SessionManager;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor.AdaptadorMisActividadesTutor;
import com.dybcatering.live4teach.Tutor.Actividades.AdaptadorActividadesTutor.ItemAdaptadorActividadesTutor;
import com.dybcatering.live4teach.Tutor.Actividades.MisActividadesTutorFragment;
import com.dybcatering.live4teach.Tutor.Calificaciones.InsertCalificaciones.CalificacionesTutorInsertFragment;

import java.util.ArrayList;


public class CalificacionesTutorFragment extends Fragment {
	View myView;

	LinearLayout linearLayout;
	private static final String TAG = MisActividadesTutorFragment.class.getSimpleName(); //getting the info
	String id_usuario;
	private RecyclerView mRecyclerView;
	private AdaptadorMisActividadesTutor misActividadesAdaptor;
	private ArrayList<ItemAdaptadorActividadesTutor> mactividadesItems;
	private RequestQueue mRequestQueue;
	private FloatingActionButton fab;
	SessionManager sessionManager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		myView = inflater.inflate(R.layout.fragment_calificaciones_tutor, container, false);



		fab = myView.findViewById(R.id.fab);
		ScaleAnimation anim = new ScaleAnimation(0,1,0,1);
		anim.setFillBefore(true);
		anim.setFillAfter(true);
		anim.setFillEnabled(true);
		anim.setDuration(1500);
		anim.setInterpolator(new OvershootInterpolator());
		fab.startAnimation(anim);
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				iniciartransicion();
			}
		});


		return myView;
	}

	private void iniciartransicion(){
		Fragment someFragment = new CalificacionesTutorInsertFragment();
		FragmentTransaction transaction = getFragmentManager().beginTransaction();
		transaction.replace(R.id.fragment_container, someFragment ); // give your fragment container id in first parameter
		transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
		transaction.commit();
	}

}
