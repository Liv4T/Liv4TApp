package com.dybcatering.live4teach.Estudiante.MisCursos;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.dybcatering.live4teach.R;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.AcercaDeCurso.AcercaCursoFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.Clases.ClasesFragment;
import com.dybcatering.live4teach.Estudiante.MisCursos.MisCursosDetallePostCompra.ViewPagerAdapter;
import com.pierfrancescosoffritti.youtubeplayer.player.YouTubePlayer;

public class  MisCursosDetalleFragment extends Fragment {

	YouTubePlayer youTubePlayer;
	LottieAnimationView scrolldebajo;
	View view;
	TextView descText, segunda_desc, tercera_desc;
	ProgressBar progressBar;
	ImageButton show, hide, show2, hide2, show3, hide3;
	private static final String TAG = "MisCursosDetalle";

	private TabLayout tabLayout;
	private AppBarLayout appBarLayout;
	private ViewPager viewPager;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View view = inflater.inflate(R.layout.fragment_mis_cursos_detalle, container, false);

		tabLayout = view.findViewById(R.id.tablayout_id);
		appBarLayout = view.findViewById(R.id.appBarId);
		viewPager = view.findViewById(R.id.viewpager);


		ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
		//adapter = new ViewPagerAdapter(getChildFragmentManager(), MisCursosDetalleFragment.this);
		adapter.AddFragment(new ClasesFragment(), "Listado de Clases");
		adapter.AddFragment(new AcercaCursoFragment(), "Acerca de este curso");
		//adapter.AddFragment(new TerceroFragment(), "TerceroFragment");
		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);

		return view;
	}
	}